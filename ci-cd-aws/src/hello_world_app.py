"""
Simple Hello World App - Learn CI/CD with Python
This is a basic app to demonstrate GitHub Actions workflows
"""

def greet(name: str) -> str:
    """Greet a person by name"""
    return f"Hello, {name}!"


def add(a: int, b: int) -> int:
    """Add two numbers"""
    return a + b


def multiply(a: int, b: int) -> int:
    """Multiply two numbers"""
    return a * b


class Calculator:
    """Simple calculator class for CI/CD demo"""

    def __init__(self):
        self.last_result = 0

    def calculate(self, operation: str, a: int, b: int) -> int:
        """Perform calculation based on operation"""
        if operation == "add":
            self.last_result = add(a, b)
        elif operation == "multiply":
            self.last_result = multiply(a, b)
        else:
            raise ValueError(f"Unknown operation: {operation}")
        return self.last_result


def main():
    """Main function - entry point"""
    print("=" * 50)
    print("Welcome to Hello World App")
    print("=" * 50)

    # Test greet function
    print("\n1. Testing greet function:")
    print(greet("World"))
    print(greet("CI/CD"))

    # Test calculator
    print("\n2. Testing Calculator:")
    calc = Calculator()
    print(f"2 + 3 = {calc.calculate('add', 2, 3)}")
    print(f"4 * 5 = {calc.calculate('multiply', 4, 5)}")

    print("\n" + "=" * 50)
    print("All tests passed!")
    print("=" * 50)


if __name__ == "__main__":
    main()
