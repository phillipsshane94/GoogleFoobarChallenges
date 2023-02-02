package foobarChallenge3;

/*
FoobarQuestion3

Find the parent node of any given number in a balanced, post-order sorted tree
Tree is assembled based on a given height, h
Return -1 if the node doesn't have a parent

*/


public class JavaChallenge3 {

    //find current root node
    public static int GetCurrentRoot(int currentHeight){
        return (int) Math.pow(2, (currentHeight + 1)) - 1;
    }

    //find height of our current node
    public static int GetCurrentHeight(int value){
        return (int) (Math.log(value+1)/Math.log(2));
    }


    /*
    Finds parent node that is a root
    reduces tree into equivalent left-most sub-tree with delta off-set
    Sub tree is the determined by the next lowest maximum root node
    Root nodes can be found by (2^h) - 1

    Ex.  Find parent of 10
    
                            15
              7                             14
        3            6            -10-              13 
    1       2    4       5     8        9       11      12
    
    reduces to 
                7
       -3-              6
    1       2       4       5
    
    With 3 as the current node and an offset of 7 from the original tree
    the parent, 7, is one of the possible top root nodes
    returns that root + delta to find original node's parent
    
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
        //returns original parent
        return currentRoot + delta;
    }

    public static int[] solution(int h, int[] q){
        int [] p = new int[q.length];

        for(int i = 0; i < p.length; i++){
            p[i] = FindParent(h, q[i]);  
        }
        return p;
    }

    public static void main(String[] args) {

        int[] test = new int[] {5, 14, 7, 15, };
        int h = 4;        
        int[] parentArray = solution(h, test);
        
        for(int i = 0; i < parentArray.length; i++){
            System.out.println(parentArray[i]);
        }
        
        
}


}
