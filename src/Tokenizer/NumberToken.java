package Tokenizer;

import Compiler.CompilerState;

public class NumberToken extends Token {
    NumberToken(String value, CompilerState cs) {
        super("$num", value, cs);
    }

    /**
     * Checks if ch is a valid Number Token
     *
     * @param str string to check if it is an Number Token
     * @return true if ch is a Number Token, false otherwise
     */
    public static boolean isToken(String str) {
        return str.matches("^[0-9]+\\.?[0-9]*");
    }

    /**
     * Checks if token is a valid Number Token
     *
     * @param token token to check if it is a Number Token
     * @return true if token is a Number Token, false otherwise
     */
    public static boolean isToken(Token token) {
        return token instanceof NumberToken && NumberToken.isToken(token.getValue());
    }
}
