'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''


import utils
alp_list = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z']

matrix = [[0 for i in range(5)] for j in range(5)]


def Matrix(key):
    '''
    Function -- Matrix, the letters of the key is encoded
    into a 5 x 5 grid such that the letters of the key
    come first (with duplicates removed), the
    letter "j" is replaced with "i", and
    then the rest of the alphabet is filled in.

    Parameters: key

    Returns: a matrix with the letters of the key come
    first.
    '''
    key = utils.strip(key)
    if not str.isalpha(key):
        raise ValueError('Key should only contain letters')
    list_key = list(key.upper())
    mtr_key = []
    for i in range(0, len(list_key)):
        if list_key[i] not in mtr_key:
            mtr_key.append(list_key[i])
    for j in range(0, len(mtr_key)):
        if mtr_key[j] == 'J':
            mtr_key[j] = 'I'

    for k in alp_list:
        if k not in mtr_key:
            mtr_key.append(k)
    n = 0
    for i in range(0, len(matrix)):
        for j in range(0, len(matrix[i])):
            matrix[i][j] = mtr_key[n]
            n += 1
    return matrix


def GetIndex(letter):
    '''
    Function -- GetIndex, it locates the plaintext letters
    by two at a time in the key square.
    Parameters: letter
    Returns: the index of letter on the matrix.
    '''
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            if letter == matrix[i][j]:
                return i, j


def encrypt(plaintext, key):
    '''
    Function -- encrypt, it encode multiple letters at a time,
    the key to the cipher is a word. It is a complex algorithm,
    you can learn more and see concrete examples of the
    encoding on Practical Cryptography's Playfair site.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("Plaintext is not string type")
    if not isinstance(key, str):
        raise TypeError("Key is not string type")
    key = utils.strip(key)
    Matrix(key)
    plaintext = utils.strip(plaintext)
    plaintext = list(plaintext)
    if len(plaintext) % 2 != 0:
        plaintext.append('X')
    for j in range(0, len(plaintext)):
        if plaintext[j] == 'J':
            plaintext[j] = 'I'
    result = ''
    for h in range(0, len(plaintext) - 1, 2):
        if plaintext[h] == plaintext[h + 1]:
            plaintext[h + 1] = 'X'
        ind_row1, ind_col1 = GetIndex(plaintext[h])
        ind_row2, ind_col2 = GetIndex(plaintext[h+1])
        if ind_row1 == ind_row2:
            result += (matrix[ind_row1][(ind_col1 + 1) % 5] +
                       matrix[ind_row2][(ind_col2 + 1) % 5])
        elif ind_col1 == ind_col2:
            result += (matrix[(ind_row1 + 1) % 5][ind_col1] +
                       matrix[(ind_row2 + 1) % 5][ind_col2])
        else:
            result += matrix[ind_row1][ind_col2] + matrix[ind_row2][ind_col1]

    return result


def decrypt(ciphertext, key):
    '''
    Function -- decrypt, the cipher works by reversing the process of
    encrypting.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(ciphertext, str) or not isinstance(key, str):
        raise TypeError("non string parameter passed")
    key = utils.strip(key)
    if not str.isalpha(key):
        raise ValueError('Key should only contain letters')
    Matrix(key)
    ciphertext = utils.strip(ciphertext)
    cipherlist = list(ciphertext)
    result = ''
    for h in range(0, len(ciphertext) - 1, 2):
        ind_row1, ind_col1 = GetIndex(ciphertext[h])
        ind_row2, ind_col2 = GetIndex(ciphertext[h+1])
        if ind_row1 == ind_row2:
            result += (matrix[ind_row1][(ind_col1 + 4) % 5] +
                       matrix[ind_row2][(ind_col2 + 4) % 5])
        elif ind_col1 == ind_col2:
            result += (matrix[(ind_row1 + 4) % 5][ind_col1] +
                       matrix[(ind_row2 + 4) % 5][ind_col2])
        else:
            result += matrix[ind_row1][ind_col2] + matrix[ind_row2][ind_col1]

    return result
