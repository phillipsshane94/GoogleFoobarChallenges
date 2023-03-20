package foobarChallenge3;

/*
FoobarQuestion3

Find the parent node of any given number in a balanced, post-order sorted tree.
"Tree" is assembled based on a given height, h.
Return -1 if the node doesn't have a parent.

NOTE: the word "tree" is used to talk about the mental model for ease of visualization;
no actual structures or trees are used.  Everything is done mathematically which makes 
this solution incredibly fast, but a little more difficult to reason about. One of the criteria 
for the Foobar challenges is speed, so readability and maintainability aren't as important for 
these.  In a professional setting, I likely wouldn't work so hard to find a "graceful"
or novel solution and would make sure that the code was primarily readable and maintainable. 

*/


public class JavaChallenge3 {

    //Function to get the current root of a tree.
    public static int GetCurrentRoot(int currentHeight){
        return (int) Math.pow(2, (currentHeight + 1)) - 1;
    }

    //Function to get the current height of a tree.  Used to determine
    //root node value of tree and of reduced trees (explained below).  
    public static int GetCurrentHeight(int value){
        return (int) (Math.log(value+1)/Math.log(2));
    }


    /*
    Calculate root of tree, "reduce" the tree into the left most sub-tree
    and accrue an offset, delta, used to calculate the parent value of a 
    given node.  
    Sub tree is the determined by the next lowest maximum root node
    Root nodes can be found by (2^h) - 1. 

    Ex.  Find parent of 10
    
                            15
              7                             14
        3            6            -10-              13 
    1       2    4       5     8        9       11      12
    
    reduces to the left-most subtree.  The position of the node whose parent we are 
    searching for is important, as the position of that node in the subtree becomes the
    new current node.
                7
       -3-              6
    1       2       4       5
    
    Node 3 becomes the current node and we accrue an offset of 7 from the previous
    tree (all values in the left subtree are 7 less that the corresponding position
    in the right subtree, 10 - 3 for example).  Tree reduction continues until the root's 
    left or right child is the value whose parent we are searching for.  In this case,
    node 3's parent is of value 7.  To calculate the original node's parent value, add the
    new root value + delta
    
    */

    public static int FindParent(int maxHeight, int value) {
        
        if (value >= Math.pow(2,maxHeight) - 1){
            return -1;
        }

        int currentHeight = GetCurrentHeight(value);
        int currentRoot = GetCurrentRoot(currentHeight);
        int currentRootChildRight = currentRoot - 1;
        int currentRootChildLeft = currentRootChildRight / 2;
        int delta = 0;

        //reduce current tree to the left subtree and
        //accrue delta to compensate for tree reduction
        while (value != currentRootChildRight && value != currentRootChildLeft) {
            delta = delta + currentRootChildLeft;
            value = value - currentRootChildLeft;
            currentHeight = GetCurrentHeight(value);
            currentRoot = GetCurrentRoot(currentHeight);
            currentRootChildRight = currentRoot - 1;
            currentRootChildLeft = currentRootChildRight / 2;
        }
        return currentRoot + delta;
    }
    
    //Function used to fit the criteria for the Foobar prompt.
    public static int[] solution(int h, int[] q){
        int [] p = new int[q.length];

        for(int i = 0; i < p.length; i++){
            p[i] = FindParent(h, q[i]);  
        }
        return p;
    }

    //Simple main function to show the functionality.
    public static void main(String[] args) {

        int[] test = new int[] {5, 14, 7, 15};
        int h = 4;        
        int[] parentArray = solution(h, test);
        
        for(int i = 0; i < parentArray.length; i++){
            System.out.println(parentArray[i]);
        }
        
        
    }


}
