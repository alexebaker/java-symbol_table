package Parser.Nodes;

import Tokenizer.NumberToken;
import Tokenizer.Token;

public class NumberNode extends ParseNode {
    private String num;

    public NumberNode() {
        this("");
    }

    public NumberNode(Token token) {
        this(token.getValue());
    }

    public NumberNode(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString() {
        return getNum();
    }

    public static boolean isNum(Token token) {
        return NumberToken.isToken(token);
    }

    public static boolean isNum(String num) {
        return NumberToken.isToken(num);
    }
}
