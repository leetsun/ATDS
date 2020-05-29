import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class OrderUnRecur implements TraversalTreeMethod {
/*
idea: Use stack to record track
1. setup 3 variables:local,left and right to trace, 1 stack to save local for recurring
2. traverse tree through left until null, at the same time print local and push it
3. reverse back namely poping local,when loacl's right is not null, update local equaling with right,and goto step2
4. gg when stack is empty
analysis:N is number of Nodes,and H is height of tree
time complexity--O(N),space complexity--O(H)
Pushing order is preOrder.
 */
    @Override
    public void preOrder(@NotNull Node head) {
        // step 1
        Node left = null, right = null;
        var trace = new Stack<Node>();

        // push head into stack as the flag to judge gg
        var local = head;
        left = local.left;
        trace.push(local);
        System.out.print(local.value + "  ");

        while (!trace.isEmpty()) {
            // step 2
            while( left != null) {
                local = left;
                trace.push(local);
                System.out.print(local.value + "  ");
                left = local.left;
            }
            //step 3
            local = trace.pop();
            right = local.right;
            if (right != null) {
                local = right;
                trace.push(local);
                System.out.print(local.value + "  ");
                left = local.left;
                //goto step 2
                continue;
            }
        }

    }
/*
travering method is same as pre, only change order of print
1. setup 3 vars and 1 trace stack
2. traverse tree through left until null, at the same time push it
3. reverse back namely poping local and print it. When local's right is not null, update local equaling with right,and goto step2
4. gg when stack is empty

Poping order is inOrder.
 */
    @Override
    public void inOrder(Node head) {
        // step 1
        Node left = null, right = null;
        var trace = new Stack<Node>();

        // push head into stack as the flag to judge gg
        var local = head;
        left = local.left;
        trace.push(local);

        while (!trace.isEmpty()) {
            // step 2
            while( left != null) {
                local = left;
                trace.push(local);
                left = local.left;
            }
            //step 3
            //System.out.print(local.value + "  ");
            local = trace.pop();
            System.out.print(local.value + "  ");
            right = local.right;
            if (right != null) {
                local = right;
                trace.push(local);
                left = local.left;
                //goto step 2
                continue;
            }
        }
    }

/*
idea:travering method is different from the above
pos order using unrecurive method is more complex.
1. setup 2 stacks, one for traversing,the other for collecting
2. stack-trace pushing order:left,right;
3. gg:trace is empty
4. pop collect, and print
 */
    @Override
    public void posOrder(Node head) {
        // step 1
        var trace = new Stack<Node>();
        var collect = new Stack<Node>();

        // push head into stack as the flag to judge gg
        var cur = head;
        trace.push(cur);

        while (!trace.isEmpty()) {
            cur = trace.pop();
            collect.push(cur);
            if (cur.left != null) {
                trace.push(cur.left);
            }
            if (cur.right != null) {
                trace.push(cur.right);
            }
        }

        while (!collect.isEmpty()) {
            System.out.print(collect.pop().value + "  ");
        }
    }

/*
Using new method to rewrite preOrder and inOrder
*/
    public void preOrder2(Node head) {
        // setup stack for tracing
        var trace = new Stack<Node>();

        var cur = head;
        trace.push(cur);

        while (!trace.isEmpty()) {
            cur = trace.pop();
            System.out.print(cur.value + "  ");
            if (cur.right != null) {
                trace.push(cur.right);
            }
            if (cur.left != null) {
                trace.push(cur.left);
            }
        }
    }
/*
1. traverse tree on left until null, at the same time record track.
2. When left is null, it imply reach the left end, pop the node and  print it ,the turn to deal with its right child.
3. stack empty:gg.
 */
    public void inOrder2(Node head) {
        // setup stack
        var trace = new Stack<Node>();

        var cur = head;
        trace.push(cur);

        while (!trace.isEmpty()) {
            // track until left equal with null
            if (cur.left != null) {
                trace.push(cur.left);
                cur = cur.left;
                continue;
            }
            //  print, maybe leaf node or middle node
            cur = trace.pop();
            System.out.print(cur.value + "  ");
            // turn to right
            if (cur.right != null) {
                cur = cur.right;
                trace.push(cur);
                continue;
            }
        }
    }

/*
Using just only one stack to realize
steps:
1. compare sp of stack with last poped,there are 3 cases:
1.1  sp.left != last && sp.right != last
    && (sp.left != null || sp.right != null)
    imply that none of sp's two child tree have been tracked, should be pushed into stack;
    right first pushed,next is left.
1.2 sp.left == last
    imply that right child tree should be tracked, then push it into stack
1.3 sp.right == last
    imply that sp's two child trees have just been tracked, sp should be poped and printed, at the same time update last.
1.4 sp.left == null && sp.right == null
    imply that sp is a leaf and not conjured with last,should be poped and printed


2. loop step 1 until stack is empty
 */
    public void posOrder2(Node head) {
        // setup
        var trace = new Stack<Node>();
        Node sp = null;
        Node last = null;

        // push head for gg
        trace.push(head);
        sp = trace.peek();

        while (!trace.isEmpty()) {
            if (sp.right == last) {
               last = trace.pop();
               System.out.print(last.value + "  ");
               if (!trace.isEmpty()) {
                   sp = trace.peek();
               } else {
                   sp = null;
               }
            } else if (sp.left == last) {
                if (sp.right != null) {
                    trace.push(sp.right);
                }
                sp = trace.peek();
            } else if (sp.left != null || sp.right != null) {
                if (sp.right != null) {
                    trace.push(sp.right);
                }
                if (sp.left != null) {
                    trace.push(sp.left);
                }
                sp = trace.peek();
            } else {
                last = trace.pop();
                System.out.print(last.value + "  ");
                if (!trace.isEmpty()) {
                    sp = trace.peek();
                } else {
                    sp = null;
                }
            }
        }
    }

}
