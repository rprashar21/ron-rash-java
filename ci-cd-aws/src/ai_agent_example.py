"""
Claude AI Agent Example - For CI/CD Learning
This demonstrates how to deploy AI agents with GitHub Actions
"""

import os
import json
from typing import Optional


class SimpleAIAgent:
    """Simple AI Agent using Claude API"""

    def __init__(self, api_key: Optional[str] = None):
        """
        Initialize the AI agent

        Args:
            api_key: Anthropic API key (uses ANTHROPIC_API_KEY env var if not provided)
        """
        self.api_key = api_key or os.getenv("ANTHROPIC_API_KEY")

        if not self.api_key:
            raise ValueError("ANTHROPIC_API_KEY not set. Set it via environment variable.")

        # Import here to avoid import error if anthropic not installed
        try:
            from anthropic import Anthropic
            self.client = Anthropic(api_key=self.api_key)
        except ImportError:
            raise ImportError("anthropic package not installed. Run: pip install anthropic")

    def analyze_code(self, code_snippet: str) -> str:
        """
        Analyze Python code using Claude

        Args:
            code_snippet: Python code to analyze

        Returns:
            Analysis from Claude
        """
        prompt = f"""Analyze the following Python code and provide:
1. What it does
2. Potential issues
3. Improvement suggestions

Code:
```python
{code_snippet}
```"""

        response = self.client.messages.create(
            model="claude-3-5-sonnet-20241022",
            max_tokens=1024,
            messages=[
                {"role": "user", "content": prompt}
            ]
        )

        return response.content[0].text

    def generate_test_cases(self, function_description: str) -> str:
        """
        Generate test cases for a function

        Args:
            function_description: Description of function to test

        Returns:
            Generated test cases
        """
        prompt = f"""Generate 3-5 unit test cases for a Python function:
Function: {function_description}

Provide the test cases as Python unittest code."""

        response = self.client.messages.create(
            model="claude-3-5-sonnet-20241022",
            max_tokens=1024,
            messages=[
                {"role": "user", "content": prompt}
            ]
        )

        return response.content[0].text

    def explain_devops_concept(self, concept: str) -> str:
        """
        Explain DevOps concepts

        Args:
            concept: DevOps concept to explain

        Returns:
            Explanation from Claude
        """
        prompt = f"""Explain the following DevOps concept in simple terms:
Concept: {concept}

Include:
1. What it is
2. Why it's important
3. Real-world example"""

        response = self.client.messages.create(
            model="claude-3-5-sonnet-20241022",
            max_tokens=1024,
            messages=[
                {"role": "user", "content": prompt}
            ]
        )

        return response.content[0].text

    def batch_process(self, tasks: list) -> dict:
        """
        Process multiple tasks (batch operation)

        Args:
            tasks: List of tasks to process

        Returns:
            Dictionary with results
        """
        results = {}

        for i, task in enumerate(tasks):
            task_type = task.get("type")
            content = task.get("content")

            if task_type == "analyze":
                results[f"task_{i}"] = self.analyze_code(content)
            elif task_type == "test":
                results[f"task_{i}"] = self.generate_test_cases(content)
            elif task_type == "explain":
                results[f"task_{i}"] = self.explain_devops_concept(content)

        return results


def main():
    """Main function - demonstrates agent usage"""
    print("=" * 60)
    print("Claude AI Agent - CI/CD Demo")
    print("=" * 60)

    # Check if API key is available
    if not os.getenv("ANTHROPIC_API_KEY"):
        print("\n⚠️  ANTHROPIC_API_KEY environment variable not set")
        print("To use this agent, run:")
        print("  export ANTHROPIC_API_KEY='your-api-key'")
        print("  python ai_agent_example.py")
        return

    try:
        # Initialize agent
        agent = SimpleAIAgent()
        print("\n✅ Agent initialized successfully")

        # Example 1: Analyze code
        print("\n1. Analyzing sample code...")
        sample_code = """
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)
"""
        analysis = agent.analyze_code(sample_code)
        print("\nAnalysis:")
        print(analysis)

    except ValueError as e:
        print(f"\n❌ Configuration Error: {e}")
    except Exception as e:
        print(f"\n❌ Error: {e}")
        print("\nMake sure:")
        print("  1. ANTHROPIC_API_KEY is set")
        print("  2. anthropic package is installed: pip install anthropic")


if __name__ == "__main__":
    main()
