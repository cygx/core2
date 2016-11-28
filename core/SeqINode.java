package core;

public class SeqINode implements INode {
    public static final Symbol type = new Symbol();

    private final INode[] nodes;

    public SeqINode(INode... nodes) {
        this.nodes = nodes;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        int i = 0;
        while(i < nodes.length - 1)
            nodes[i++].eval(frame);

        return nodes[i].eval(frame);
    }
}
