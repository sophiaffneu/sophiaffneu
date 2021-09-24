'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 9 Ciphers
'''


import utils

alp_list = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z']

alp_dic = {'A': 1, 'B': 2, 'C': 3, 'D': 4, 'E': 5, 'F': 6, 'G': 7, 'H': 8,
           'I': 9, 'J': 10, 'K': 11, 'L': 12, 'M': 13, 'N': 14, 'O': 15,
           'P': 16, 'Q': 17, 'R': 18, 'S': 19, 'T': 20, 'U': 21, 'V': 22,
           'W': 23, 'X': 24, 'Y': 25, 'Z': 26}


def encrypt(plaintext, key):
    '''
    Function -- encrypt, it takes a 26-letter alphabetic key that
    contains all of the letters of the alphabet that are
    completely jumbled. It replaces each letter of the secret message
    with a different letter of the alphabet which is in the same positions
    as in a regular alphabet.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("plaintext should be a string")
    if not isinstance(key, str):
        raise TypeError('Key should be a string')
    key = utils.strip(key)
    count = 0
    for i in key:
        if i in alp_list and key.count(i) == 1:
            count += 1
    if count != 26:
        raise ValueError('Key should contain all 26 letter in the alphabet')
    plaintext = utils.strip(plaintext)
    result = ''
    for i in plaintext:
        indx = alp_dic[i] - 1
        result += key[indx]
    return result


def decrypt(ciphertext, key):
    '''
    Function -- decrypt, it takes a 26-letter alphabetic key that
    contains all of the letters of the alphabet that are
    completely jumbled. It replaces each letter of the cipher message
    with a different letter of the alphabet which is in the same positions
    as in a regular alphabet.

    Parameters: ciphertext, key

    Returns: the decrypted text
    '''
    if not isinstance(ciphertext, str):
        raise TypeError("ciphertext should be a string")
    if not isinstance(key, str):
        raise TypeError('Key should be an string')
    ciphertext = utils.strip(ciphertext)
    key = utils.strip(key)
    count = 0
    for i in key:
        if i in alp_list and key.count(i) == 1:
            count += 1
    if count != 26:
        raise ValueError('Key should contain all 26 letters in the alphabet')
    result = ''
    new_dict = {v: k for k, v in alp_dic.items()}
    for i in ciphertext:
        indx = key.index(i)
        result += new_dict[indx + 1]
    return result
