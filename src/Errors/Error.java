package Errors;

import Tokenizer.Tokens.Token;

public class Error extends Exception {
    Token token;
    String expected;

    public Error(Token token) {
        this(token, "");
    }

    public Error(Token token, String expected) {
        super();
        this.token = token;
        this.expected = expected;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append("Line: ");
        str.append(token.getLoc().getLineCount());
        str.append(" Character: ");
        str.append(token.getLoc().getLineCount());
        str.append(" Reason: Found '");
        str.append(token);

        if (expected.length() > 0) {
            str.append("' but expected '");
            str.append(expected);
            str.append("'");
        }
        return str.toString();
    }
}
