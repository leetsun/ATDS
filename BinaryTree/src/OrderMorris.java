public class OrderMorris implements TraversalTreeMethod{
    /*
    idea: let child tree's null right point to tree head, so as to enable traversing back
    The track order is named Morris order.
    1. If cur has left tree, seed the rightest node of left tree, naming it mostright
        1.1 if mostright.right == null, then make mostright.right point to cur, then turn to cur's left.
        imply that the left tree hasn't been tracked, so make it point to tree's head.
        1.2 if mostright.right == cur, then turn to cur's right tree, and recover mostright'right point to null.
        imply that cur's left tree has been tracked, so turn to cur's right tree.
    2. Else cur hasn't left tree, turn to its right tree.
    3. cur == null ,gg.
     */
    public void morris(Node head) {
        // setup
        Node mostRight = null;
        Node cur = head;

        while (cur != null) {
            // print
            System.out.print(cur.value + "  ");
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // setup back point
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                }
                // recover
                else if (mostRight.right == cur) {
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
            // step 2
            else {
               cur = cur.right;
            }
        }

    }

    /*
    1. Fistly, seed the leftest node in tree(when cur.left ==null),then print it.
    2. After, when return to mid node from leftest node, print it.
     */
    @Override
    public void preOrder(Node head) {
        // setup
        Node mostRight = null;
        Node cur = head;

        while (cur != null) {
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    // print
                    System.out.print(cur.value + "  ");
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
            // step 2
            else {
                // print
                System.out.print(cur.value + "  ");
                cur = cur.right;
            }
        }


    }

    /*
    1. First print mid node. When setup mostRight node, it is time to print the cur.
    2. Then process will continue to the cur's left, when it reaches the leftest(cur.left == null),print it.
     */
    @Override
    public void inOrder(Node head) {
        // setup
        Node mostRight = null;
        Node cur = head;

        while (cur != null) {
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    // print
                    System.out.print(cur.value + "  ");
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
            // step 2
            else {
                // print
                System.out.print(cur.value + "  ");
                cur = cur.right;
            }
        }
    }

    private Node reverseRightEdge(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private void printRightEdge(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + "  ");
            cur = cur.right;
        }
    }

    /*
    1. For nodes without left child, pass it directly.
    2. For nodes with left child, When reach it at second time(namely through mostRight), print right edge of left child tree reversely(note recover it after the process).
    3. Print right edge of the whole tree.
     */
    @Override
    public void posOrder(Node head) {
        // setup
        Node mostRight = null;
        Node cur = head;
        Node edgeHead = null;

        while (cur != null) {
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    mostRight.right = null;
                    // step2:print cur's left tree's right edge reversely
                    edgeHead = reverseRightEdge(cur.left);
                    printRightEdge(edgeHead);
                    reverseRightEdge(edgeHead);
                    cur = cur.right;
                }
            }
            // step 2
            else {
                cur = cur.right;
            }
        }
        // step 3
        edgeHead = reverseRightEdge(head);
        printRightEdge(edgeHead);
        reverseRightEdge(edgeHead);
    }
}
