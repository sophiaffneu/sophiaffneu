'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 9 Ciphers
'''


import utils
alp_list = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z']


def Matrix(alp_key, num_key):
    '''
    Function -- Matrix, the letters of the alphabetic key is encoded
    into a 10 x 3 grid using the numeric key.

    Parameters: alp_key, num_key

    Returns: a key square encoded with alphabetic key and numeric key.
    '''
    if not isinstance(alp_key, str):
        raise TypeError("Alphabetic key has to be a string")
    alp_key = utils.strip(alp_key)
    if not str.isalpha(alp_key):
        raise ValueError('Alphabetic key should only contain letters')
    if not isinstance(num_key, int):
        raise TypeError("Numeric key has to be an integer")
    num_key = str(num_key)
    if len(num_key) != 2:
        raise ValueError('Numeric key should only contain 2 numbers')
    if num_key[0] == num_key[1]:
        raise ValueError('Numeric key should only contain 2 distinct numbers')
    if int(num_key[0]) <= 0 or int(num_key[1]) <= 0:
        raise ValueError('Numeric key should only contain\
    numbers greater than 0')
    matrix = [[0 for i in range(10)] for j in range(3)]
    alp_key = list(alp_key.upper())
    count = 0
    for i in alp_key:
        if i in alp_list:
            count += 1
    if count != 26:
        raise ValueError('alphabetic key should contain all\
    26 letter in the alphabet')
    matrix[0][int(num_key[0])] = ''
    matrix[0][int(num_key[1])] = ''
    matrix[2][9] = ''
    matrix[2][8] = ''
    n = 0
    for i in range(0, len(matrix)):
        for j in range(0, len(matrix[i])):
            if matrix[i][j] != '':
                matrix[i][j] = alp_key[n]
                n += 1
    matrix[2][9] = "1"
    matrix[2][8] = "0"
    return matrix


def GetIndex(matrix, letter):
    '''
    Function -- GetIndex, it locates the position of plaintext letters
    on the key square.
    Parameters: letter
    Returns: the index of letter on the matrix.
    '''
    for i in range(0, len(matrix)):
        for j in range(0, len(matrix[i])):
            if letter == matrix[i][j]:
                return i, j


def encrypt(plaintext, alp_key, num_key, adder):
    '''
    Function -- encrypt, following specific rule, we can encrypt
    the plaintext with the key square. Learn more about
    this step of the algorithm by visiting Practical Cryptography's
    Straddle Checkerboard site


    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("Plaintext is not string type")
    if not isinstance(adder, int):
        raise TypeError("adder is not integer type")
    if int(adder) <= 0:
        raise ValueError('adder have to be a positive number')
    adder = str(adder)
    matrix = Matrix(alp_key, num_key)
    num_key = str(num_key)
    plaintext = utils.strip(plaintext)
    plaintext = list(plaintext)
    result = ''
    row, col = 0, 0
    for i in range(0, len(plaintext)):
        row, col = GetIndex(matrix, plaintext[i])
        row = str(row)
        col = str(col)
        if row == '0':
            result += col
        elif row == '1':
            result += num_key[0] + col
        elif row == '2':
            result += num_key[1] + col
    anw = ''
    i = 0
    while i < len(result):
        j = 0
        while j < len(adder):
            anw += str((int(result[i]) + int(adder[j])) % 10)
            i += 1
            j += 1
            if i == len(result):
                break
    k = 0
    final = ''
    while k < len(anw):
        if anw[k] == num_key[0] and k < (len(anw) - 1):
            final += matrix[1][int(anw[k + 1])]
            k = k + 2
        elif anw[k] == num_key[1] and k < (len(anw) - 1):
            final += matrix[2][int(anw[k + 1])]
            k = k + 2
        else:
            final += matrix[0][int(anw[k])]
            k = k + 1

    return final


def decrypt(ciphertext, alp_key, num_key, adder):
    '''
    Function -- decrypt, the cipher works by reversing the process of
    encrypting.

    Parameters: ciphertext, key

    Returns: the ciphertext
    '''
    if not isinstance(ciphertext, str):
        raise TypeError("ciphertext is not string type")
    if not isinstance(adder, int):
        raise TypeError("adder is not integer type")
    if int(adder) <= 0:
        raise ValueError('adder have to be a positive number')
    adder = str(adder)
    matrix = Matrix(alp_key, num_key)
    num_key = str(num_key)
    cipherlist = list(ciphertext)
    result = ''
    row, col = 0, 0
    for i in range(0, len(cipherlist)):
        row, col = GetIndex(matrix, cipherlist[i])
        row = str(row)
        col = str(col)
        if row == '0':
            result += col
        elif row == '1':
            result += num_key[0] + col
        elif row == '2':
            result += num_key[1] + col
    anw = ''
    i = 0
    while i < len(result):
        j = 0
        while j < len(adder):
            anw += str((int(result[i]) + 10 - int(adder[j])) % 10)
            i += 1
            j += 1
            if i == len(result):
                break
    k = 0
    final = ''
    while k < len(anw):
        if anw[k] == num_key[0] and k < (len(anw) - 1):
            final += matrix[1][int(anw[k + 1])]
            k = k + 2
        elif anw[k] == num_key[1] and k < (len(anw) - 1):
            final += matrix[2][int(anw[k + 1])]
            k = k + 2
        else:
            final += matrix[0][int(anw[k])]
            k = k + 1
    return final
