'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''


import utils
alp_list = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z']


def encrypt(plaintext, key):
    '''
    Function -- encrypt, It takes any integer value as a key
    and replaces each letter of the secret message with a
    different letter of the alphabet which is key positions
    further away in the alphabet.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("non string parameters passed")
    if not isinstance(key, int):
        raise TypeError('Key should be an integer')
    plaintext = utils.strip(plaintext)
    result = ''
    for i in plaintext:
        indx = alp_list.index(i)
        result += alp_list[(indx + key) % 26]
    return result


def decrypt(ciphertext, key):
    '''
    Function -- decrypt, It takes any integer value as a key
    and replaces each letter of the ciphertext with a
    different letter of the alphabet which is key positions
    further back in the alphabet.

    Parameters: ciphertext, key

    Returns: the decrypted text
    '''
    if not isinstance(ciphertext, str):
        raise TypeError("non string parameters passed")
    if not isinstance(key, int):
        raise TypeError('Key should be an integer')
    ciphertext = utils.strip(ciphertext)
    result = ''
    for i in ciphertext:
        indx = alp_list.index(i)
        result += alp_list[(indx + (26 - key)) % 26]
    return result
