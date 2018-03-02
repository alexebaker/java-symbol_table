package Errors;

import Tokenizer.Tokens.Token;

public class SyntaxError extends Error {
    public SyntaxError(Token token, String expected) {
        super(token, expected);
    }

    @Override
    public String toString() {
        return "Syntax Error! " + super.toString();
    }
}
