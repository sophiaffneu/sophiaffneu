import boto3
import json
import urllib.request
import gzip
import shutil
import pandas as pd
import io
import mysql.connector as msql
from mysql.connector import Error

#Download and save files from a lists of urls.

def download(url, file_name, unzip_name):
    html = urllib.request.urlopen(url).read()
    try:
        with open("reviews/" + file_name, 'wb') as f:
            f.write(html)
            print('==========' + file_name + ' downloaded===========')
            f.close()
    except FileNotFoundError:
        print("No file found from downloading url")
    else:
        print(file_name + " is downloaded")

    #unzip file
    with gzip.open("reviews/" + file_name, 'rb') as f_in:
        with open('reviews/' + unzip_name, 'wb') as f_out:
            shutil.copyfileobj(f_in, f_out)

def get_url_strlist(url):
    str_list = url.split('/')
    if str_list[3] == '' or  str_list[4] == '' or str_list[5]== '' or str_list[6]=='':
            raise ValueError('Invalid string value in str_list')
    else: return str_list
    
def get_file_name(str_list):   
    file_name = str_list[3] + "." + str_list[4] + "." + str_list[5] + "." + str_list[6] + '.csv'
    return file_name

def get_database_name(str_list):
    database_name = str_list[3] + "_" + str_list[4] + "_" + str_list[5] + "_"
    return database_name

def get_table_name(str_list):
    database_name = str_list[3] + "_" + str_list[4] + "_" + str_list[5] + "_"
    return database_name


def main():

    url_list = ["http://data.insideairbnb.com/canada/bc/vancouver/2021-12-11/data/reviews.csv.gz",
                "http://data.insideairbnb.com/united-states/ma/boston/2021-12-17/data/reviews.csv.gz",
                "http://data.insideairbnb.com/united-states/hi/hawaii/2021-12-11/data/reviews.csv.gz",
                "http://data.insideairbnb.com/china/shanghai/shanghai/2021-12-24/data/reviews.csv.gz",
                "http://data.insideairbnb.com/singapore/sg/singapore/2021-12-26/data/reviews.csv.gz" ]

    s3 = boto3.resource('s3')
    bucketName = "lab6ff04082022"
    #create the bucket
    bucket = s3.create_bucket(Bucket = "lab6ff04082022")
    #list bucket
    result = s3.buckets.all()
    for bkt in result:
       print(bkt.name)
           
    #download file, unzip and load data to s3 bucket
    for i in url_list:
        str_list = get_url_strlist(i)
        file_name = str_list[3] + "." + str_list[4] + "." + str_list[5] + "." + str_list[6] + '.csv.gz'
        unzip_name = str_list[3] + "." + str_list[4] + "." + str_list[5] + "." + str_list[6] + ".csv"
        directory_name = str_list[3] + "/" + str_list[4] + "/" + str_list[5] + "/"
        s3_file_name = str_list[6] + ".csv"

        download(i, file_name, unzip_name)
        #load csv files to s3 bucket
        s3.Object(bucketName, directory_name + s3_file_name).put(Body = open("reviews/" + unzip_name, "rb"), ContentType = "csv")

    #change permission of the bucket
    bucket = s3.Bucket(bucketName)
    bucket.Acl().put(ACL = "public-read")
    #add bucket policy 
    policy = {"Version": "2012-10-17",
              "Statement": [
                            { "Sid": "PublicReadGetObject",
                              "Effect": "Allow",
                               "Principal": "*",
                              "Action": ["s3:GetObject" ],
                              "Resource": ["arn:aws:s3:::" + bucketName + "/*"]

                               }
                             ]
              }
    policyStr  = json.dumps(policy)
    bucketPolicy = s3.BucketPolicy(bucketName)
    bucketPolicy.put(ConfirmRemoveSelfBucketAccess=False,
    Policy= policyStr,
    ExpectedBucketOwner="259348559828")

    #Sentiment analysis of data from Boston
    url = "http://data.insideairbnb.com/united-states/ma/boston/2021-12-17/data/reviews.csv.gz"
    str_list = get_url_strlist(url)
    file_name = get_file_name(str_list)
    s3_client = boto3.client('s3')
    obj = s3_client.get_object(Bucket = bucketName, Key = file_name)
    df = pd.read_csv(io.BytesIO(obj['Body'].read()), encoding='utf8')

    #delete id column from dataframe
    df.drop(columns = ['id'], inplace = True)

    #add columns to dataframe
    df.insert(df.shape[1],'sentiment', 'p')
    df.insert(df.shape[1],'positive_score', '0')
    df.insert(df.shape[1],'neutral_score', '0')
    df.insert(df.shape[1], 'negative_score', '0')

    #Comprehend analysis for each comment. 
    comprehend = boto3.client('comprehend', region_name = 'us-east-1')
    index = 0
    for i in df.head(10000)['comments']:
        #if comment is null, set sentiment to n/a, scores to 0
        if pd.isna(i):
            df.at[index, 'sentiment'] = 'n/a'
            df.at[index, 'positive_score'] = '0'
            df.at[index, 'neutral_score'] = '0'
            df.at[index, 'negative_score'] = '0'
        else:
            response = comprehend.detect_sentiment(Text = i, LanguageCode = 'en' or 'es'or'fr'or 'de'
                                                   or'it'or 'pt'or 'ar'or 'hi'or'ja'or'ko'or 'zh'or'zh-TW')
            df.at[index, 'sentiment'] = response['Sentiment']
            df.at[index, 'positive_score'] = response['SentimentScore']['Positive']
            df.at[index, 'neutral_score'] = response['SentimentScore']['Neutral']
            df.at[index, 'negative_score'] = response['SentimentScore']['Negative']
            index = index + 1
    
    
    print(df.head(100))
    print(df.shape)
    
    #connect to mysql and creat database    
    database_name = get_database_name(str_list)
    table_name = 'review_' + get_table_name(str_list)
    
    try:
        conn = msql.connect(host = 'sentiment-analysis.ce7kg7emw8lf.us-east-1.rds.amazonaws.com', user='admin',  
                                        password='sentiment')
        if conn.is_connected():
            cursor = conn.cursor()
            cursor.execute('DROP DATABASE IF EXISTS ' + database_name)
            cursor.execute("CREATE DATABASE " + database_name)
            print("================Database is created================")
    except Error as e:
            print("Error while connecting to MySQL", e)
    
    #Create a table and import the panda dataframe data into the MySQL table
    try:
        conn = msql.connect(host = 'sentiment-analysis.ce7kg7emw8lf.us-east-1.rds.amazonaws.com',
                    database = database_name, user='admin',password='sentiment')
        if conn.is_connected():
            cursor = conn.cursor()
            cursor.execute("select database();")
            record = cursor.fetchone()
            print("Connected to database: ", record)
            cursor.execute('DROP TABLE IF EXISTS ' + table_name +';')
            print('================Creating table================')
            cursor.execute("CREATE TABLE " + table_name + " (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, listing_id int, date varchar(255), reviewer_id int, reviewer_name varchar(255),comments text,
                           sentiment varchar(255), positive_score float, neutral_score float, negative_score float);")
            print("================Table is created================")

    #loop through the data frame and insert data into database.
        for i,row in df.head(10000).iterrows():

            sql = "INSERT INTO " + database_name + '.' + table_name + "(listing_id, date, reviewer_id, reviewer_name, comments, sentiment, positive_score, neutral_score, negative_score) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            cursor.execute(sql, tuple(row))
            print("================Record inserted================")
            #commit to save the changes
            conn.commit()
    except Error as e:
            print("Error while connecting to MySQL", e)


if __name__ == '__main__':
    main()
