package Parser.Nodes;


public abstract class ASTNode {
    public String getASTR() {
        return "";
    }

    @Override
    public String toString() {
        return getASTR();
    }
}
