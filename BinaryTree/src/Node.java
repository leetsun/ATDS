import org.jetbrains.annotations.NotNull;

public class Node<head> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node insertBinarySortTree(Node head, @NotNull Node node) {
        if (head == null) {
            head = node;
            head.left = null;
            head.right = null;
        }
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            cur = (node.value < cur.value) ? cur.left : cur.right;
        }
        if (pre.value > node.value) {
            pre.left = node;
        } else {
            pre.right = node;
        }
        node.left = null;
        node.right = null;

        return head;
    }

    public Node genBinarySortTree(@NotNull int[] arr) {
        Node head = null;
        head = new Node(arr[0]);
        Node node = null;
        for (int i = 1; i < arr.length; i++) {
            node = new Node(arr[i]);
            insertBinarySortTree(head, node);
        }
        return head;
    }

}
