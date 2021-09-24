'''
Fang(Sophia) Fang
CS 5001 - Online Fall 2020
hw 9 Ciphers
Test
'''


import straddle
import unittest


class EvaluateTest(unittest.TestCase):
    def test_evaluate(self):
        answer1 = straddle.encrypt('ProfJumpiscrazyto think we can do this!',
                                   'NORTHEASBCDFGIJKLMPQUVWXYZ', 26,
                                   38574)
        answer2 = straddle.encrypt('defend the east wall of the castle!',
                                   'FKMCPDYEHBIGQROSAZLUTJNWVX', 37,
                                   83729)
        answer3 = straddle.decrypt('SAAAYNORXEOE0EEAEOTFNESSJAAETNEHEAFNEAAA\
NN',
                                   'NORTHEASBCDFGIJKLMPQUVWXYZ', 26,
                                   38574)
        answer4 = straddle.decrypt('CMUDMECCMYMDPUFCCDOPEEPHYEPPFMYMDPPDPPCMY',
                                   'FKMCPDYEHBIGQROSAZLUTJNWVX', 37,
                                   83729)

        self.assertEqual(answer1, 'SAAAYNORXEOE0EEAEOTFNESSJAAETNEHEAFNEAAANN')
        self.assertEqual(answer2, 'CMUDMECCMYMDPUFCCDOPEEPHYEPPFMYMDPPDPPCMY')
        self.assertEqual(answer3, 'PROFJUMPISCRAZYTOTHINKWECANDOTHI')
        self.assertEqual(answer4, 'DEFENDTHEEASTWALLOFTHECASTLE')

    def test_for_error(self):
        # alphabetic key contains numbers
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          '4ac', 56, 1234)
        # alphabetic key contains less than 26 letters
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          'fac', 56, 1234)
        # plaintext is not string
        self.assertRaises(TypeError, straddle.encrypt, 125,
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 56, 1234)
        # alphabetic key is not string
        self.assertRaises(TypeError, straddle.encrypt, 'abc', 56,
                          56, 1234)
        # numeric key is not integer
        self.assertRaises(TypeError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', '56', 1234)
        # numeric key contains more than 2 numbers
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 567, 1234)
        # numeric key contains two same numbers
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 55, 1234)
        # numeric key contains zero or negative value
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 60, 1234)
        # adder key is not integer
        self.assertRaises(TypeError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', '56', "1234")
        # adder key is not a positive number
        self.assertRaises(ValueError, straddle.encrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', '56', -1234)
        # decrypt test
        # alphabetic key contains numbers
        self.assertRaises(ValueError, straddle.decrypt, 'abc', '4ac',
                          '56', 1234)
        # alphabetic key contains less than 26 letters
        self.assertRaises(ValueError, straddle.decrypt, 'abc', 'fac',
                          56, 1234)
        # ciphertext is not string
        self.assertRaises(TypeError, straddle.decrypt, 123,
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 56, 1234)
        # alphabetic key is not string
        self.assertRaises(TypeError, straddle.decrypt, 'abc', 56,
                          56, '1234')
        # numeric key is not integer
        self.assertRaises(TypeError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', '56', 1234)
        # numeric key contains more than 2 numbers
        self.assertRaises(ValueError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 567, 1234)
        # numeric key contains two same numbers
        self.assertRaises(ValueError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 55, 1234)
        # numeric key contains zero or negative value
        self.assertRaises(ValueError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 60, 1234)
        # adder key is not integer
        self.assertRaises(TypeError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 56, '1234')
        # adder key is not a positive number
        self.assertRaises(ValueError, straddle.decrypt, 'abc',
                          'NORTHEASBCDFGIJKLMPQUVWXYZ', 56, -1234)


def main():
    unittest.main(verbosity=3)


main()
