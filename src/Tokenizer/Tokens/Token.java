package Tokenizer.Tokens;

import Tokenizer.Location;

/**
 * Base Token class for the compiler
 */
public class Token {
    private String token;
    private String value;
    private Location loc;

    public Token(String token, Location loc) {
        this(token, "", loc);
    }

    public Token(String token, String value, Location loc) {
        this.token = token;
        this.value = value;
        this.loc = loc;
    }

    public String getValue() {
        if (value.length() > 0) {
            return value;
        }
        return token;
    }

    public Location getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
