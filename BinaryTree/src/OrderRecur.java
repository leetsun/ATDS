public class OrderRecur implements TraversalTreeMethod{
    public void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + "  ");
        preOrder(head.left);
        preOrder(head.right);
    }

    public void inOrder(Node head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.value + "  ");
        inOrder(head.right);
    }

    public void posOrder(Node head) {
        if (head == null) {
            return;
        }
        posOrder(head.left);
        posOrder(head.right);
        System.out.print(head.value + "  ");
    }
}
