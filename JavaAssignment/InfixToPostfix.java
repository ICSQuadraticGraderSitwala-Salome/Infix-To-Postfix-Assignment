import java.util.Stack;

public class InfixToPostfix {

    // Function to define operator precedence
    static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Check if character is operand (letter or digit)
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Convert infix to postfix
    static String convertToPostfix(String infix) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            // If operand → add to result
            if (isOperand(ch)) {
                result += ch;
            }

            // If '(' → push to stack
            else if (ch == '(') {
                stack.push(ch);
            }

            // If ')' → pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop(); // remove '('
            }

            // If operator
            else {
                while (!stack.isEmpty() &&
                        precedence(ch) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        String infix = "(A+B)*(C-D)";

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + convertToPostfix(infix));
    }
}