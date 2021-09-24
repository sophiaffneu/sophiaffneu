'''
Fang(Sophia) Fang
CS 5001 - Online Fall 2020
hw 9 Ciphers
Test
'''


import beale
import unittest


class EvaluateTest(unittest.TestCase):
    def test_evaluate(self):
        answer1 = beale.encrypt('Abcd efghijklmnopq rstuvwxy!',
                                'declaration.txt', 10)
        answer2 = beale.encrypt('attack', 'declaration.txt', 10)
        answer3 = beale.encrypt('Yes!', 'declaration.txt', 10)
        answer4 = beale.decrypt('1101 1249 933 591 1088 548 827 1080 1286\
 756 1112 693 708 1097 12 17 695 312 167 791 842 818 92 1323 1324',
                                'declaration.txt')
        answer5 = beale.decrypt('1101 970 521 701 931 304', 'declaration.txt')
        self.assertEqual(answer1, '''1101 1249 933 591 1088 548 827 1080\
 1286 756 1112 693 708 1097 12 17 695 312 167 791 842 818 92 1323 1324''')
        self.assertEqual(answer2, '1101 970 521 701 931 304')
        self.assertEqual(answer3, '1324 7 279')
        self.assertEqual(answer4, 'ABCDEFGHIJKLMNOPQRSTUVWXY')
        self.assertEqual(answer5, 'ATTACK')

    def test_for_error(self):
        # file not found for encrypt
        self.assertRaises(FileNotFoundError, beale.encrypt, 'abc',
                          'colleg.txt', 10)

        # the text file doesn't have all the letters needed.
        self.assertRaises(ValueError, beale.encrypt, 'abc', 'college.txt', 10)

        # plaintext and file_name are empty string
        self.assertRaises(ValueError, beale.encrypt, '', '', 10)

        # plaintext is not string
        self.assertRaises(TypeError, beale.encrypt, 567, 'declaration.txt', 10)

        # file_name is not string
        self.assertRaises(TypeError, beale.encrypt, 'abc', 567, 10)

        # seed is not integer
        self.assertRaises(TypeError, beale.encrypt, 'abc', 'declaration.txt',
                          '10')
        # plaintext contains numbers
        self.assertRaises(ValueError, beale.encrypt, 'attack96',
                          'declaration.txt', 10)

        # file not found for decrypt
        self.assertRaises(FileNotFoundError, beale.decrypt, 'abc',
                          'colleg.txt')

        # ciphertext is not string
        self.assertRaises(TypeError, beale.decrypt, 567, 'declaration.txt')

        # ciphtertext is not numeric
        self.assertRaises(ValueError, beale.decrypt, "abc4", 'declaration.txt')

        # ciphertext is a empty string
        self.assertRaises(ValueError, beale.decrypt, '', 'declaration.txt')

        # file_name is not string
        self.assertRaises(TypeError, beale.decrypt, 'abc', 567)

        # the text file doesn't have all the numbers needed
        self.assertRaises(ValueError, beale.decrypt, '15 970 521 701 931 304',
                          'college.txt')


def main():
    unittest.main(verbosity=3)


main()
