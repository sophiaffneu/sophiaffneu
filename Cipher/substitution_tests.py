'''
Fang(Sophia) Fang
CS 5001 - Online Fall 2020
hw 9 Ciphers
Test
'''


import substitution
import unittest


class EvaluateTest(unittest.TestCase):
    def test_evaluate(self):
        answer1 = substitution.encrypt('DEfend the - east wall of the castle',
                                       'PHQGIUMEAYLNOFDXJKRCVSTZWB')
        answer2 = substitution.encrypt('ProfJumpiscrazytothinkwecando this!',
                                       'ZECTPMSHAXFVRQBWILUGKJODYN')
        answer3 = substitution.encrypt('a a a aaaaaaaaaaaaz z z zzzzzzzz',
                                       'PHQGIUMEAYLNOFDXJKRCVSTZWB')
        answer4 = substitution.decrypt('GIUIFGCEIIPRCTPNNDUCEIQPRCNI',
                                       'PHQGIUMEAYLNOFDXJKRCVSTZWB')
        answer5 = substitution.decrypt('WLBMXKRWAUCLZNYGBGHAQFOPCZQTBGHAU',
                                       'ZECTPMSHAXFVRQBWILUGKJODYN')
        answer6 = substitution.decrypt('PPPPPPPPPPPPPPPPPPBBBBBBBBBBBBB',
                                       'PHQGIUMEAYLNOFDXJKRCVSTZWB')
        self.assertEqual(answer1, 'GIUIFGCEIIPRCTPNNDUCEIQPRCNI')
        self.assertEqual(answer2, 'WLBMXKRWAUCLZNYGBGHAQFOPCZQTBGHAU')
        self.assertEqual(answer3, 'PPPPPPPPPPPPPPPBBBBBBBBBBB')
        self.assertEqual(answer4, 'DEFENDTHEEASTWALLOFTHECASTLE')
        self.assertEqual(answer5, 'PROFJUMPISCRAZYTOTHINKWECANDOTHIS')
        self.assertEqual(answer6, 'AAAAAAAAAAAAAAAAAAZZZZZZZZZZZZZ')

    def test_for_error(self):
        # plaintext is not string
        self.assertRaises(TypeError, substitution.encrypt, 567,
                          'PHQGIUMEAYLNOFDXJKRCVSTZWB')

        # plaintext contains numbers
        self.assertRaises(ValueError, substitution.encrypt, 'ab56',
                          'PHQGIUMEAYLNOFDXJKRCVSTZWB')

        # key doesn't have all 26 letters
        self.assertRaises(ValueError, substitution.encrypt, 'efg',
                          'PHQGIUMEAYLNOFDXJKRCVSTZBBWT')

        # key contains numbers
        self.assertRaises(ValueError, substitution.encrypt, 'abc',
                          'PHQGIUMEAYLNOFDXJKRCVSTZWB567')

        # key is not string
        self.assertRaises(TypeError, substitution.encrypt, 'abc', 567)

        # ciphertext is not string
        self.assertRaises(TypeError, substitution.decrypt, 567,
                          'PHQGIUMEAYLNOFDXJKRCVSTZWB')

        # ciphertext contains numbers
        self.assertRaises(ValueError, substitution.decrypt, "567",
                          'PHQGIUMEAYLNOFDXJKRCVSTZWB')


def main():
    unittest.main(verbosity=3)


main()
