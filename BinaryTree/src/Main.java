import javax.naming.BinaryRefAddr;

public class Main {
    public static void main(String[] args) {
        int arr[] = {5, 2, 3, 1, 4, 7, 9, 6};
        Node head = new Node(arr[0]);
        head = head.genBinarySortTree(arr);
        //OrderRecur track = new OrderRecur();
        OrderUnRecur track = new OrderUnRecur();
        //OrderMorris track = new OrderMorris();

        //System.out.println("preorder:");
        //track.preOrder(head);
        //System.out.println("\ninorder:");
        //track.inOrder(head);
        //System.out.println("\nposorder:");
        //track.posOrder(head);

        // for OrderUnRecur
        //System.out.println("\nprorder2:");
        //track.preOrder2(head);
        //System.out.println("\ninorder2:");
        //track.inOrder2(head);
        //System.out.println("\nposorder2:");
        //track.posOrder2(head);

        // for morris
        //System.out.println("\nMorris order:");
        //track.morris(head);
        //System.out.println("\nMorris preOrder:");
        //track.preOrder(head);
        //System.out.println("\nMorris inOrder:");
        //track.inOrder(head);
        //System.out.println("\nMorris posOrder:");
        //track.posOrder(head);

        // get depth
       // var getDepth = new GetDepth();
       // var minDepth = getDepth.getMinDepth(head);
       // System.out.println("\nThe min depth of tree:");
       // System.out.print(minDepth);
       // var maxDepth = getDepth.getMaxDepth(head);
       // System.out.println("\nThe max depth of tree:");
       // System.out.print(maxDepth);

        // Serialization and reconstruct
       // Serial ser = new Serial();
       // String serStr = ser.serialByLevel(head);
       // System.out.println("\nSerilized tree:");
       // System.out.print(serStr);
       // System.out.println("\nReconstuction from string:");
       // head = ser.reconByLevelString(serStr);
       // track.inOrder(head);

        // compute the max length summit to a value
        BinaryTreeOperations opts = new BinaryTreeOperations();
       // int sum = 9;
       // int maxLength = opts.getMaxLength(head, sum);
       // System.out.println("\nCompute the max length:");
       // System.out.print(maxLength);

        // find maxBST
          // setup
        Node lbst = head;
        System.out.println("\nThe left BST tree:");
        track.inOrder(lbst);
        head = new Node(10); // 10
        System.out.print("\nThe head node: " + head.value);
        int arr1[] = {12, 15, 16, 20, 21, 22, 23, 24, 25};
        Node rbst = new Node(arr1[0]);
        rbst = rbst.genBinarySortTree(arr1);
        System.out.println("\nThe right BST tree:");
        track.inOrder(rbst);
        head.left = lbst;
        head.right = rbst;

        BinaryTreeOperations.MaxBST maxBST = opts.getMaxBST(head);
        System.out.println("\nThe max BST tree:");
        track.inOrder(maxBST.head);
        System.out.print("\nThe max BST tree size: " + maxBST.treeSize);
        System.out.print("\nThe max value: " + maxBST.maxValue + "\n The min value: " + maxBST.minValue);

        // find max topology BST
        int maxTopoSize = opts.maxTopoTreeSize(head);
        System.out.print("\nThe max topology BST size: " + maxTopoSize);

        // isBalancedTree
          //setup
        int[] arr2 = {5, 3, 6, 8, 9};
        Node tree = new Node(arr2[0]);
        tree = tree.genBinarySortTree(arr2);
        TreeOperations treeOpts = new TreeOperations();
        boolean res = treeOpts.isBalancedTree(tree);
        System.out.print("\nThe tree is balanced tree: " + res);

    }
}
