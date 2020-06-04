public class GetDepth {
    public int getMinDepth(Node head) {
        int depth = 0;
        if (head != null) {
            depth = Math.min(getMinDepth(head.left), getMinDepth(head.right));
            depth++;
        }
        return depth;
    }

    public int getMaxDepth(Node head) {
        int depth = 0;
        if (head != null) {
            depth = Math.max(getMaxDepth(head.left), getMaxDepth(head.right));
            depth++;
        }
        return depth;
    }
}
