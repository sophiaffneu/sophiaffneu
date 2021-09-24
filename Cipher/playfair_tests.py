'''
Fang(Sophia) Fang
CS5001 Fall 2020
HW 8 Ciphers
'''

import playfair


def main():
    passed = True
    original = 0
    key = 1
    expected = 2

    e_provided = [
        ("PTBOATONEOWENINELOSTINACT", "ROYAL NEW ZEALAND NAVY",
         "KXIEYUREBEZWEHEWRYTUHEYFUQ"),
        ("WEAREDISCOVEREDSAVEYOURSELFX", "MONARCHY",
         "UGRMKCSXHMUFMKBTOXGCMVATLUIV"),
        ("PROFJUMPISCRAZYTOTHINKWECANDOTHIS", "NORTHEASTERN",
         "XSAMKQPQGBSHCWTBRHTKHDVAESELRHTKGR"),
            ]
    d_provided = [
        ("PTBOATONEOWENINELOSTINACTX", "ROYAL NEW ZEALAND NAVY",
         "KXIEYUREBEZWEHEWRYTUHEYFUQ"),
        ("WEAREDISCOVEREDSAVEYOURSELFX", "MONARCHY",
         "UGRMKCSXHMUFMKBTOXGCMVATLUIV"),
        ("PROFIUMPISCRAZYTOTHINKWECANDOTHISX", "NORTHEASTERN",
         "XSAMKQPQGBSHCWTBRHTKHDVAESELRHTKGR"),
            ]
    # Run the provided tests cases
    for t in e_provided:
        e_actual = playfair.encrypt(t[original], t[key])
        if e_actual != t[expected]:
            print("Error encrypting:", t[original])
            print("   Expected:", t[expected])
            print("   Actual:  ", e_actual)
            print()
            passed = False
    for t in d_provided:
        d_actual = playfair.decrypt(t[expected], t[key])
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
        playfair.encrypt(456, 'northeastern')
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "Plaintext is not string type":
            print("Plaintext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if plaintext contains only letters
    try:
        playfair.encrypt('abc456', 'northeastern')
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
        playfair.decrypt('abc456', 'northeaste56')
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
        playfair.decrypt(456, 'northeastern')
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "non string parameter passed":
            print("Ciphertext string TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key is string for encrypt function
    try:
        playfair.encrypt('abc', 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "Key is not string type":
            print("Key integer TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key contains only letters for encrypt function
    try:
        playfair.encrypt('abc456', 'northeaste56')
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("key contains only letters test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key is string for decrypt function
    try:
        playfair.decrypt('abc', 5)
        print("failed because it did not raise the TypeError")
    except TypeError as ex:
        if str(ex) == "non string parameter passed":
            print("Key integer TypeError test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")

    # test if key contains only letters for decrypt function
    try:
        playfair.decrypt('abc456', 'northeaste56')
        print("failed because it did not raise the TypeError")
    except ValueError as ex:
        if str(ex) == "Text should only contain letters":
            print("key contains only letters test passed")
        else:
            print("failed with wrong error message")
    except Exception:
        print("failed with wrong error raised")


if __name__ == '__main__':
    main()
