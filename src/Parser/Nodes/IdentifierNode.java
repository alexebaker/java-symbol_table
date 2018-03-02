package Parser.Nodes;

import Tokenizer.IdentifierToken;
import Tokenizer.Token;

public class IdentifierNode extends ParseNode {
    private String id;

    public IdentifierNode() {
        this("");
    }

    public IdentifierNode(Token token) {
        this(token.getValue());
    }

    public IdentifierNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId();
    }

    public static boolean isID(Token token) {
        return IdentifierToken.isToken(token);
    }

    public static boolean isID(String id) {
        return IdentifierToken.isToken(id);
    }
}
