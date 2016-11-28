package core;

public class BlockINode implements INode {
    public static final Symbol type = new Symbol();

    private final INode[] nodes;

    public BlockINode(INode... nodes) {
        this.nodes = nodes;
    }

    public Symbol type() {
        return type;
    }

    public Value eval(Frame frame) {
        Block block = frame.enterBlock();
        LOOP: for(int i = 0;;) {
            block.action = BlockAction.CONTINUE;

            for(; i < nodes.length; ++i) {
                nodes[i].eval(frame);
                switch(block.action) {
                    case CONTINUE:  continue;
                    case BREAK:     break LOOP;
                    case JUMP:      i += block.offset;
                    case REDO:      continue LOOP;
                }
            }

            break;
        }

        frame.leaveBlock();
        return block.value;
    }
}
