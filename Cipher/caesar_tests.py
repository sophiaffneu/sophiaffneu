'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''

import caesar


def main():
    passed = True
    original = 0
    key = 1
    expected = 2

    provided = [
        ("DEFENDTHEEASTWALLOFTHECASTLE", 1,
         "EFGFOEUIFFBTUXBMMPGUIFDBTUMF"),
        ("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1,
         "BCDEFGHIJKLMNOPQRSTUVWXYZA"),
        ("PROFJUMPISCRAZYTOTHINKWECANDOTHIS", 3,
         "SURIMXPSLVFUDCBWRWKLQNZHFDQGRWKLV"),
            ]
    # Run the provided tests cases
    for t in provided:
        e_actual = caesar.encrypt(t[original], t[key])
        if e_actual != t[expected]:
            print("Error encrypting:", t[original])
            print("   Expected:", t[expected])
            print("   Actual:  ", e_actual)
            print()
            passed = False
        d_actual = caesar.decrypt(t[expected], t[key])
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
        caesar.encrypt(456, 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "non string parameters passed":
            print("Plaintext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if plaintext contains only letters
    try:
        caesar.encrypt('abc456', 5)
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("Plaintext string letter only test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if ciphertext is string
    try:
        caesar.decrypt(456, 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "non string parameters passed":
            print("Ciphertext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if ciphertext only contains letters
    try:
        caesar.decrypt('abc45', 5)
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("Ciphertext letter only test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key is integer for encrypt function
    try:
        caesar.encrypt('abc', '5')
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
        caesar.decrypt('abc', '5')
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
