package parentheses_validator;

import java.util.HashMap;
import java.util.Stack;

public class ParenthesesValidator {
    private static final HashMap<Character, Character> PARENTHESES_MAP = new HashMap<>();
    // I used Hashmap to store all opening and closing symbols so we can dynamically add symbols for validation

    static {
        PARENTHESES_MAP.put('(', ')');
        PARENTHESES_MAP.put('[', ']');
        PARENTHESES_MAP.put('{', '}');
    }

    public static boolean validateParentheses(String code) {
        Stack<Character> stack = new Stack<>();

        for (char c : code.toCharArray()) {
            if (PARENTHESES_MAP.containsKey(c)) {
                stack.push(c);
            } else if (PARENTHESES_MAP.containsValue(c)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return PARENTHESES_MAP.get(opening) == closing;
    }

    public static void main(String[] args) {
        String code = "(sdf(sdfsdf(sdfsdf[asdfasd])))";
        System.out.println(validateParentheses(code));
    }
}