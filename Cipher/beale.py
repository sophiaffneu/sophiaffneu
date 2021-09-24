'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 9 Ciphers
'''


import utils
import random
import os

letter_dict = {1: 'A', 2: 'B', 3: 'C', 4: 'D', 5: 'E',
               6: 'F', 7: 'G', 8: 'H', 9: 'I', 10: 'J',
               11: 'K', 12: 'L', 13: 'M', 14: 'N', 15: 'O',
               16: 'P', 17: 'Q', 18: 'R', 19: 'S', 20: 'T',
               21: 'U', 22: 'V', 23: 'W', 24: 'X', 25: 'Y', 26: 'Z'}


def get_filedata(file_name):
    '''
    Function -- get_filedata, to read in the text to be used as
    the key.

    Parameters: file_name

    Returns: a list of contents from file
    '''
    if os.path.exists(file_name) is False:
        raise FileNotFoundError("File does not exist!")
    try:
        input_file = open(file_name, 'r')
    except PermissionError:
        print('You do not have permission to use that file')
    except OSError:
        print("Something happened while opening the file")
    filedata = input_file.readlines()
    input_file.close()
    return filedata


def process(filedata):
    '''
    Function -- process, to convert the text data into a two
    dimentional list, first dimention represents letter A - Z, second
    dimention of the list shows the position of all of the words
    in the book that begin with that letter.

    Parameters: filedata

    Returns: a two dimentional list
    '''
    word_list = []
    for i in range(0, len(filedata)):
        word_list += filedata[i].split()
    alp_list = [[] for i in range(0, 26)]
    i = 0
    while i < len(word_list):
        word_list[i] = word_list[i].upper()
        if (ord(word_list[i][0]) - ord('A') > 26 or
                ord(word_list[i][0]) - ord('A') < 0):
            print(f"word_list[i] is excluded from encoding because it\
 doesn't begin with an alphabetic letter")
            i += 1

        else:
            alp_list[ord(word_list[i][0]) - ord('A')].append(i)
            i += 1
    return alp_list


def encrypt(plaintext, file_name, seed):
    '''
    Function -- encrypt, the sender and the receiver have
    agreed to a text to be used as key, letters are encoded
    one-by-one as an integer value by randomly selecting a word that
    begins with the first letter in the text.

    Parameters: plaintext, file_name, seed, file_name is the name
    of the file that contains the text of the book, seed is
    and an integer that is used to seed the random number generator.

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("plaintext should be a string")
    if plaintext == '' or file_name == '':
        raise ValueError('Parameter can not be a empty string')
    if not isinstance(file_name, str):
        raise TypeError('file_name should be a string')
    if not isinstance(seed, int):
        raise TypeError('seed should be an integer')
    filedata = get_filedata(file_name)
    alp_list = process(filedata)
    plaintext = utils.strip(plaintext)
    result = ''
    j = 0
    random.seed(seed)
    for i in plaintext:
        j = ord(i) - ord('A')
        if alp_list[j] == []:
            raise ValueError("book doesn't contain all the \
letters required for encryption.")
        else:
            ind_pick = random.randint(0, 100000) % len(alp_list[j])

            num = alp_list[j][ind_pick]
            result += str(num + 1) + ' '
    return result.rstrip()


def decrypt(ciphertext, file_name):
    '''
    Function -- decrypt, the function convert ciphertext to plaintext
    by using the text list generated from file.

    Parameters: ciphertext, file_name

    Returns: the decrypted text
    '''
    if not isinstance(ciphertext, str):
        raise TypeError("ciphertext should be a string")
    if ciphertext == '' or file_name == '':
        raise ValueError('Parameter can not be a empty string')
    if not isinstance(file_name, str):
        raise TypeError('file_name should be an string')
    filedata = get_filedata(file_name)
    alp_list = process(filedata)
    ciphertext = ciphertext.split()
    result = ''
    count = 0
    for i in ciphertext:
        i = int(i)
        for j in range(len(alp_list)):
            if (i-1) in alp_list[j]:
                result += letter_dict[j + 1]
                count += 1
    if count != len(ciphertext):
        raise ValueError("book doesn't contain all the \
numbers required for encryption.")
    return result
