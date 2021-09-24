'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''


import utils


def encrypt(plaintext, key):
    '''
    Function -- encrypt, the key to the cipher is an integer
    that represents the number of "rails"
    that exist on an imaginary fence. A fence must have
    at least 2 rails.  The cipher works by writing the message
    diagonally up and down on successive rails, changing
    directions when the bottom or top of the fence is reached.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(plaintext, str):
        raise TypeError("plaintext is not string")
    if not isinstance(key, int):
        raise TypeError('Key should be an integer')
    if key < 2:
        raise ValueError('Key must be greater than 1')
    plaintext = utils.strip(plaintext)
    result, n = '', len(plaintext)
    size = 2*key-2
    groups = len(plaintext)//size+1
    for i in range(0, key):
        for j in range(0, groups):
            if j*size+i < n:
                result += plaintext[j*size+i]
            if i > 0 and i < key-1 and (j+1)*size-i < n:
                result += plaintext[(j+1)*size-i]
    return result


def decrypt(ciphertext, key):
    '''
    Function -- decrypt, the key to the cipher is an integer
    that represents the number of "rails"
    that exist on an imaginary fence. A fence must have
    at least 2 rails.  The cipher works by reversing the process of
    encrypting.

    Parameters: plaintext, key

    Returns: the ciphertext
    '''
    if not isinstance(ciphertext, str):
        raise TypeError("ciphertext is not string")
    if not isinstance(key, int):
        raise TypeError('Key should be an integer')
    if key < 2:
        raise ValueError('Key must be greater than 1')
    ciphertext = utils.strip(ciphertext)
    n = len(ciphertext)
    result = [' ']*n
    size = 2*key-2
    groups = n//size+1
    k = 0
    for i in range(0, key):
        for j in range(0, groups):
            if j*size+i < n:
                result[j*size+i] = ciphertext[k]
                k += 1
            if i > 0 and i < key-1 and (j+1)*size-i < n:
                result[(j+1)*size-i] = ciphertext[k]
                k += 1
    res = ''
    for i in result:
        res += i
    return res
