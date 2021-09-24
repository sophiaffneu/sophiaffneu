'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''

import string


def Select_E_D():
    '''
    Function -- Select_E_D, it prompt user to select encryption
    or decryption
    Parameters: none
    Returns: user's choice
    '''

    cmd = input('Encryption or decryption? E for Encryption,\
 D for decryption: ')
    if cmd.upper() == 'E':
        choice = 'E'
    elif cmd.upper() == 'D':
        choice = 'D'
    else:
        raise ValueError('Answer should be only "E" or "D"')
    return choice


def Get_Info():
    '''
    Function -- Get_Info, it prompt user to enter text and key
    for encoding or decoding.
    Parameters: none
    Returns: text and key
    '''
    text = input('Enter text: ')
    key = input('Enter key: ')
    return text, key


def strip(text):
    '''
    Function -- Strip, it strip out all of the spaces and
    punctuation from the message(text) to be processed. It
    also convert all letters to uppercase.
    Parameters: text
    Returns: a processed text
    '''
    for c in string.punctuation:
        text = text.replace(c, "")
    text = text.replace(" ", "")
    text = text.upper()
    if not str.isalpha(text):
        raise ValueError('Text should only contain letters')
    return text
