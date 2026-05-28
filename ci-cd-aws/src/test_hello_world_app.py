"""
Unit tests for hello_world_app.py
Demonstrates testing in CI/CD pipelines
"""

import unittest
from hello_world_app import greet, add, multiply, Calculator


class TestBasicFunctions(unittest.TestCase):
    """Test basic functions"""

    def test_greet(self):
        """Test greet function"""
        self.assertEqual(greet("Alice"), "Hello, Alice!")
        self.assertEqual(greet("World"), "Hello, World!")

    def test_add(self):
        """Test add function"""
        self.assertEqual(add(2, 3), 5)
        self.assertEqual(add(-1, 1), 0)
        self.assertEqual(add(0, 0), 0)

    def test_multiply(self):
        """Test multiply function"""
        self.assertEqual(multiply(3, 4), 12)
        self.assertEqual(multiply(-2, 3), -6)
        self.assertEqual(multiply(0, 100), 0)


class TestCalculator(unittest.TestCase):
    """Test Calculator class"""

    def setUp(self):
        """Set up test fixtures"""
        self.calc = Calculator()

    def test_calculator_add(self):
        """Test calculator addition"""
        result = self.calc.calculate("add", 10, 20)
        self.assertEqual(result, 30)
        self.assertEqual(self.calc.last_result, 30)

    def test_calculator_multiply(self):
        """Test calculator multiplication"""
        result = self.calc.calculate("multiply", 5, 6)
        self.assertEqual(result, 30)
        self.assertEqual(self.calc.last_result, 30)

    def test_calculator_invalid_operation(self):
        """Test calculator with invalid operation"""
        with self.assertRaises(ValueError):
            self.calc.calculate("divide", 10, 2)

    def test_calculator_state(self):
        """Test calculator maintains state"""
        self.calc.calculate("add", 5, 5)
        self.assertEqual(self.calc.last_result, 10)
        self.calc.calculate("multiply", 2, 3)
        self.assertEqual(self.calc.last_result, 6)


if __name__ == "__main__":
    unittest.main(verbosity=2)
