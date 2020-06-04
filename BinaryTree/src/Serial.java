import java.util.LinkedList;
import java.util.Queue;

public class Serial {
    public String serialByLevel(Node head) {
        StringBuilder treeStr = new StringBuilder();
        Queue<Node> queue = new LinkedList<Node>();
        if (head == null) {
            return "#!";
        }
        // initial
        queue.offer(head);
        treeStr.append(head.value);
        treeStr.append("!");
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.offer(head.left);
                treeStr.append(head.left.value);
                treeStr.append("!");
            } else {
                treeStr.append("#!");
            }
            if (head.right != null) {
                queue.add(head.right);
                treeStr.append(head.right.value);
                treeStr.append("!");
            } else {
                treeStr.append("#!");
            }
        }

        return treeStr.toString();
    }

    public Node reconByLevelString(String serStr) {
        String[] strArr = serStr.split("!");
        Queue<Node> queue = new LinkedList<Node>();
        int index = 0;
        // initial
        Node head = genNodeByString(strArr[index++]);
        if (head != null) {
            queue.offer(head);
        }

        Node newNode = null;
        while (!queue.isEmpty()) {
            newNode = queue.poll();
            newNode.left = genNodeByString(strArr[index++]);
            newNode.right = genNodeByString(strArr[index++]);
            if (newNode.left != null) {
                queue.offer(newNode.left);
            }
            if (newNode.right != null) {
                queue.offer(newNode.right);
            }
        }
        return head;
    }

    private Node genNodeByString(String str) {
        if (str.equals("#")) {
            return null;
        }
        return (new Node(Integer.valueOf(str)));
    }
}
