import javax.swing.*;
import java.util.HashMap;

public class BinaryTreeOperations {
    /*
    func: get the max length of one trace which sum to a value
     */
    public int getMaxLength(Node head, int sum) {
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        maxLength  = preorder(head, sum,0, 1, maxLength, map);

        return maxLength;
    }

    /*
    para:
    head: current node
    sum: the pointed value
    preSum: parent node's culmulative sum from head
    level: node's level
    maxLength: the current updated maxLength
    map<sum,level>: record the sum values within the track from head to cur

    return:
    the max Length in current tree
     */
    private int preorder(Node head, int sum, int preSum, int level, int maxLength, HashMap<Integer, Integer> map) {
        if (head == null) {
            return maxLength;
        }
        // record new sum values into map
        int curSum = preSum + head.value;
        if (!map.containsKey(curSum)) {
            map.put(curSum, level);
        }
        // compute maxLength
        if (map.containsKey(curSum - sum)) {
            maxLength = Math.max(maxLength, level - map.get(curSum - sum));
        }
        // update the maxLength
        maxLength = Math.max(maxLength, preorder(head.left, sum, curSum, level + 1, maxLength, map));
        maxLength = Math.max(maxLength, preorder(head.right, sum, curSum, level + 1, maxLength, map));

        // remove the value when come back to parent
        if (map.get(curSum) == level) {
            map.remove(curSum);
        }

        return maxLength;
    }


    /*
    func: find the max BST child tree within a tree
    method: collect info recursively(posOrder) including node, tree size, maxvalue and minvalue in tree, as below
    steps:
    1.

     */

    public class MaxBST {
        public Node head;
        public int treeSize;
        public int maxValue;
        public int minValue;

        public MaxBST(Node head, int treeSize,int max, int min) {
            this.head = head;
            this.treeSize = treeSize;
            this.maxValue = max;
            this.minValue = min;
        }
    }

    public MaxBST getMaxBST(Node head) {
        // intial leftMaxBST and rightMaxBST for judge conveniently
        MaxBST leftMaxBST = new MaxBST(null,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
        MaxBST rightMaxBST = new MaxBST(null,0,Integer.MIN_VALUE,Integer.MAX_VALUE);

        MaxBST maxBST = new MaxBST(null,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
        if (head == null) {
            return maxBST;
        }

        if (head.left != null) {
            leftMaxBST = getMaxBST(head.left);
        }
        if (head.right != null) {
            rightMaxBST = getMaxBST(head.right);
        }
        if (leftMaxBST.head == head.left && rightMaxBST.head == head.right
                && head.value >= leftMaxBST.maxValue && head.value <= rightMaxBST.minValue) {
            maxBST = new MaxBST(
                    head,
                    Math.max(leftMaxBST.treeSize, rightMaxBST.treeSize) + 1,
                    // update minValue and maxValue in left&rightBST
                    Math.max(head.value, rightMaxBST.maxValue),
                    Math.min(head.value, leftMaxBST.minValue)
            );
        } else {
            maxBST = (leftMaxBST.treeSize > rightMaxBST.treeSize) ? leftMaxBST : rightMaxBST;
        }

        return maxBST;
    }
}
