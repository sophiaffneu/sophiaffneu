'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''

import utils
import caesar
import playfair
import railfence
import substitution
import beale
import straddle


def main():
    flag = False
    while not flag:
        cmd1 = input('''Enter your choice of algorithms:
    C = Caesar Cipher
    R = Rail Fence Cipher
    P = Playfair Cipher
    SU = Substitution
    B = Beale
    ST = Straddle
    Q = Quit


    ''')
        result = ''
        if cmd1.upper() == 'Q':
            print("End program")
            flag = True
        elif cmd1.upper() == 'C':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    e_text, e_key = utils.Get_Info()
                    e_key = int(e_key)
                    plaintext = utils.strip(e_text)
                    result = caesar.encrypt(plaintext, e_key)
                elif choice == 'D':
                    d_text, d_key = utils.Get_Info()
                    d_key = int(d_key)
                    result = caesar.decrypt(d_text, d_key)
            except Exception as ex:
                print(ex, type(ex))

        elif cmd1.upper() == 'R':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    e_text, e_key = utils.Get_Info()
                    e_key = int(e_key)
                    plaintext = utils.strip(e_text)
                    result = railfence.encrypt(plaintext, e_key)
                elif choice == 'D':
                    d_text, d_key = utils.Get_Info()
                    d_key = int(d_key)
                    result = railfence.decrypt(d_text, d_key)
            except Exception as ex:
                print(ex, type(ex))

        elif cmd1.upper() == 'P':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    e_text, e_key = utils.Get_Info()
                    plaintext = utils.strip(e_text)
                    result = playfair.encrypt(plaintext, e_key)
                elif choice == 'D':
                    d_text, d_key = utils.Get_Info()
                    result = playfair.decrypt(d_text, d_key)
            except Exception as ex:
                print(ex, type(ex))

        elif cmd1.upper() == 'SU':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    e_text, e_key = utils.Get_Info()
                    plaintext = utils.strip(e_text)
                    print(plaintext)
                    result = substitution.encrypt(plaintext, e_key)
                elif choice == 'D':
                    d_text, d_key = utils.Get_Info()
                    result = substitution.decrypt(d_text, d_key)
            except Exception as ex:
                print(ex, type(ex))

        elif cmd1.upper() == 'B':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    plaintext = input('Enter message: ')
                    file_name = input('Enter file name: ')
                    seed = int(input('Enter seed for random function: '))
                    plaintext = utils.strip(plaintext)
                    result = beale.encrypt(plaintext, file_name, seed)
                elif choice == 'D':
                    ciphertext = input('Enter message: ')
                    file_name = input('Enter file name: ')
                    result = beale.decrypt(ciphertext, file_name)
            except Exception as ex:
                print(ex, type(ex))

        elif cmd1.upper() == 'ST':
            try:
                choice = utils.Select_E_D()
                if choice == 'E':
                    plaintext = input('Enter message: ')
                    alp_key = input('Enter alphabetic key: ')
                    num_key = input('Enter numeric key: ')
                    adder = input('Enter adder key: ')
                    plaintext = utils.strip(plaintext)
                    result = straddle.encrypt(plaintext,
                                              alp_key, num_key, adder)
                elif choice == 'D':
                    ciphertext = input('Enter message: ')
                    alp_key = input('Enter alphabetic key: ')
                    num_key = input('Enter numeric key: ')
                    adder = input('Enter adder key: ')
                    result = straddle.decrypt(ciphertext,
                                              alp_key, num_key, adder)
            except Exception as ex:
                print(ex, type(ex))
        else:
            raise ValueError('Answer should be "C", "R", "P", "SU", \
 "B", "ST" or "Q"')

        print(result)
        print()


if __name__ == '__main__':
    main()
