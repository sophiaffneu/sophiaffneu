'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''

import railfence


def main():
    passed = True
    original = 0
    key = 1
    expected = 2

    provided = [
        ("DEFENDTHEEASTWALLOFTHECASTLE", 3,
         "DNETLHSEEDHESWLOTEATEFTAAFCL"),
        ("DEFENDTHEEASTWALLOFTHECASTLE", 4,
         "DTTFSEDHSWOTATFNEAALHCLEELEE"),
        ("PROFJUMPISCRAZYTOTHINKWECANDOTHIS", 4,
         "PMAHCHRUPRZTIEATIOJICYONWNOSFSTKD"),
            ]
    # Run the provided tests cases
    for t in provided:
        e_actual = railfence.encrypt(t[original], t[key])
        if e_actual != t[expected]:
            print("Error encrypting:", t[original])
            print("   Expected:", t[expected])
            print("   Actual:  ", e_actual)
            print()
            passed = False
        d_actual = railfence.decrypt(t[expected], t[key])
        if d_actual != t[original]:
            print("Error decrypting:", t[expected])
            print("   Expected:", t[original])
            print("   Actual:  ", d_actual)
            print()
            passed = False
    if passed is True:
        print('Test passed!')

    # test if plaintext is string
    try:
        railfence.encrypt(456, 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "plaintext is not string":
            print("Plaintext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if plaintext contains only letters
    try:
        railfence.encrypt('abc456', 5)
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("Plaintext letter only test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if ciphertext contains only letters
    try:
        railfence.encrypt('abc456', 5)
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("ciphertext letter only test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if ciphertext is string
    try:
        railfence.decrypt(456, 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "ciphertext is not string":
            print("Ciphertext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key is integer for encrypt function
    try:
        railfence.encrypt('abc', '5')
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "Key should be an integer":
            print("Key integer TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key is integer for decrypt function
    try:
        railfence.decrypt('abc', '5')
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "Key should be an integer":
            print("Key integer TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")


if __name__ == '__main__':
    main()
