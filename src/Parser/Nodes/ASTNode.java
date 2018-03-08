package Parser.Nodes;


public abstract class ASTNode {
    public String getFPIFStr() {
        return "";
    }

    @Override
    public String toString() {
        return getFPIFStr();
    }
}
