public class Main {
    public static void main(String[] args) {
        int arr[] = {5, 2, 3, 1, 4, 7, 9, 6, 8, 10, 11, 12};
        //int arr[] = {5, 6, 7, 8, 9};
        Node head = new Node(arr[0]);
        head = head.genBinarySortTree(arr);
        //OrderRecur track = new OrderRecur();
        //OrderUnRecur track = new OrderUnRecur();
        OrderMorris track = new OrderMorris();

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
        System.out.println("\nMorris order:");
        track.morris(head);
        //System.out.println("\nMorris preOrder:");
        //track.preOrder(head);
        //System.out.println("\nMorris inOrder:");
        //track.inOrder(head);
        //System.out.println("\nMorris posOrder:");
        //track.posOrder(head);
        TreeOperations opts = new TreeOperations();
        int minHeight = opts.getMinDepth(head);
        System.out.println("\nMin height of tree:");
        System.out.print(minHeight);
        int maxHeight = opts.getMaxDepth(head);
        System.out.println("\nMax height of tree:");
        System.out.print(maxHeight);
    }
}
