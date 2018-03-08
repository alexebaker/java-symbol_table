package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;

import java.util.Vector;

public class TypeSpec extends ASTNode {
    private ASTNode typeName;
    private Vector<ASTNode> arraySpecs;

    public TypeSpec() {
        typeName = null;
        arraySpecs = new Vector<>();
    }

    public void addArraySpec(ASTNode arraySpec) {
        arraySpecs.add(arraySpec);
    }

    public Vector<ASTNode> getArraySpecs() {
        return arraySpecs;
    }

    public void setTypeName(ASTNode typeName) {
        this.typeName = typeName;
    }

    public ASTNode getTypeName() {
        return typeName;
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        TypeSpec typeSpec = new TypeSpec();
        try {
            typeSpec.setTypeName(PrimType.parse(tr));

            while (tr.peek().equals("[")) {
                typeSpec.addArraySpec(ArraySpec.parse(tr));
            }
        }
        catch (SyntaxError ex) {
            throw ex;
        }
        return typeSpec;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        str.append(typeName.getFPIFStr());
        for (ASTNode arraySpec : getArraySpecs()) {
            str.append(arraySpec.getFPIFStr());
        }
        return str.toString();
    }
}
