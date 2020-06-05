public class TreeOperations {
    public int getMinDepth(Node head) {
        if (head == null) {
            return 0;
        }
        // setup
        int minHeight = Integer.MAX_VALUE;
        int curLevel = 0;
        int rightEdgeNum = 0;

        Node mostRight = null;
        Node cur = head;

        while (cur != null) {
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                rightEdgeNum = 1;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                    // record number of nodes within right edge of cur node
                    rightEdgeNum++;
                }
                // This is the first time reaching cur node
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    curLevel ++;
                }
                // This is the second time to reach cur node
                else if (mostRight.right == cur) {
                    cur = cur.right;
                    mostRight.right = null;
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightEdgeNum;
                }
            }
            // step 2
            else {
                cur = cur.right;
                curLevel++;
            }
        }
        // compare with the rightest node's height finally if it is a leaf
        //int finalHight = 1;
        cur = head;
        while (cur.right != null) {
            cur = cur.right;
            //finalHight++;
        }
        if (cur.left == null) {
            minHeight = Math.min(minHeight, curLevel);
        }
        return minHeight;
    }

    public int getMaxDepth(Node head) {
        if (head == null) {
            return 0;
        }
        // setup
        int maxHeight = Integer.MIN_VALUE;
        int curLevel = 0;
        int rightEdgeNum = 0;

        Node mostRight = null;
        Node cur = head;

        while (cur != null) {
            // step 1
            if (cur.left != null) {
                mostRight = cur.left;
                rightEdgeNum = 1;
                // seed rightest node of left tree
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                    // record number of nodes within right edge of cur node
                    rightEdgeNum++;
                }
                // This is the first time reaching cur node
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    curLevel ++;
                }
                // This is the second time to reach cur node
                else if (mostRight.right == cur) {
                    cur = cur.right;
                    mostRight.right = null;
                    if (mostRight.left == null) {
                        maxHeight = Math.max(maxHeight, curLevel);
                    }
                    curLevel -= rightEdgeNum;
                }
            }
            // step 2
            else {
                cur = cur.right;
                curLevel++;
            }
        }
        // compare with the rightest node's height finally if it is a leaf
        //int finalHight = 0;
        cur = head;
        while (cur != null) {
            cur = cur.right;
           // finalHight++;
        }
        maxHeight = Math.max(maxHeight, curLevel);
        return maxHeight;

    }

    public boolean isBalancedTree(Node head) {
        if (head == null) {
            return true;
        }
        // posOrder
        boolean lret = isBalancedTree(head.left);
        boolean rret = isBalancedTree(head.right);

        // base case
        boolean ret = false;
        int lh = getMaxDepth(head.left);
        int rh = getMaxDepth(head.right);
        if (Math.abs(lh - rh) <= 1) {
            ret = true;
        }
        return (ret && lret && rret);
    }
}
