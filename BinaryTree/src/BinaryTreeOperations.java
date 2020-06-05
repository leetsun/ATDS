import javax.swing.*;
import java.util.HashMap;
import java.util.Stack;

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
        // base case
        if (leftMaxBST.head == head.left && rightMaxBST.head == head.right
                && head.value >= leftMaxBST.maxValue && head.value <= rightMaxBST.minValue) {
            maxBST = new MaxBST(
                    head,
                    leftMaxBST.treeSize + rightMaxBST.treeSize + 1,
                    // update minValue and maxValue in left&rightBST
                    Math.max(head.value, rightMaxBST.maxValue),
                    Math.min(head.value, leftMaxBST.minValue)
            );
        } else {
            maxBST = (leftMaxBST.treeSize > rightMaxBST.treeSize) ? leftMaxBST : rightMaxBST;
        }

        return maxBST;
    }

    /*
    func: get max topology BST's size
    condition: Nodes have no same value.
    method: compute and record topology contribution of every node recursively
    steps: posOrder
    1. collect left and right tree's topo contribution
    2. compute and record current node's left and right tree topo contribution(This is base case for recursion)
    3. update and return max topo contribution
     */
    public class Record {
        public int l;  // left child tree's topo contribution (number in child tree)
        public int r;  // right tree's topo contribution

        public Record(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    public int maxTopoTreeSize(Node head) {
        // setup
        HashMap<Node, Record> map = new HashMap<>();
        // get
        return process(head, map);
    }

    // recursive function
    /*
    func: record each node's topo contribution in tree including head
    return max topo contribution size
     */
    public int process(Node head, HashMap<Node, Record> map) {
       // setup and initialize
        if (head == null) {
            return 0;
        }
        // collect and save in var temperarily
        int lr = process(head.left, map);
        int rr = process(head.right, map);

        // base case: compute current node and update
        modify(head.left, head.value, map, true);
        modify(head.right, head.value, map, false);
        Record lbst = map.get(head.left);
        Record rbst = map.get(head.right);
        int lTopoSize = (lbst == null)? 0 : lbst.l + lbst.r + 1;
        int rTopoSize = (rbst == null) ? 0 : rbst.l + rbst.r + 1;
        Record topoCon = new Record(lTopoSize, rTopoSize);
        map.put(head, topoCon);
        // update
        return Math.max(topoCon.l + topoCon.r + 1, Math.max(lr, rr));
    }

    /*
    func: update map record according to parent's value
    param:
    head: current node
    pValue: parent node's value
    direction: true--the head is the left tree of its parent, otherwise is the right
    steps:
    if direction == left
    1. track through head's right edge, to find which node's value is bigger than pValue.
    Noting that the BST is broken.
    2. Update topo contribution on the trace(right).
    direction: right
    1. track thouth head's left edge, to find which node's value is smaller than pValue.
    2. Update topo contribution on the trace(left).
     */
    public void modify(Node head, int pValue, HashMap<Node, Record> map, boolean direction) {
        // setup
        Stack<Node> stack = new Stack<>();
        if (head == null) {
            return;
        }

        Node cur = head;
        Record rec = null;
        int minus = 0;
        // step 1
        while ( cur != null && cur.value < pValue) {
            stack.push(cur);
            if (direction) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (cur != null) {
            rec = map.get(cur);
            minus = rec.l + rec.r + 1;
            // step 2
        } else {
            minus = 0;
        }
        while (!stack.empty()) {
            cur = stack.pop();
            rec = map.get(cur);
            if (direction) {
                rec.r -= minus;
            } else {
                rec.l -= minus;
            }
            map.put(cur, rec);
        }
    }



}
