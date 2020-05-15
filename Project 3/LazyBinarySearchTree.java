/*
    Name: Humza Salman
    NET ID: MHS180007
*/

public class LazyBinarySearchTree {
    private TreeNode root;
    private int size;
 
    public LazyBinarySearchTree() {
        root = null;
        size = 0;
    }

    /* 
        function to insert the key into the tree
       no duplicates allowed
       if a duplicate entry, and element is previously deleted, then undeleted
       value must be [1,99] 
    */
    public boolean insert(int k) { 
        if ( k < 1 || k > 99)
            throw new IllegalArgumentException("IllegalArgumentException raised");
        
        if( root == null) { // if empty tree, creates the root
            root = new TreeNode(k);
            size++; // increase size of tree
            return true;
        }

        TreeNode node = root;
        while (node != null) { // make sure node is not null
            if( k < node.key)  {
                if(node.leftChild == null) {
                    node.leftChild = new TreeNode(k);
                    size++; // increase size of tree
                    return true;
                }else {
                    node = node.leftChild; // traverse down left subtree
                }
            }else if ( k > node.key) { // key is greater than node
                if(node.rightChild == null) {
                    node.rightChild = new TreeNode(k);
                    size++; // increase size of tree
                    return true;
                }else {
                    node = node.rightChild; // traverse down right subtree
                }
            }else if ( k == node.key && node.deleted == true) { // key is the node and the node is deleted
                node.deleted = false; // deleted = false since the same key was being inserted
                return true; // logicaly insertion is marked as true
            }else 
                break; // exit while loop in any other case
        }

        return false;
    }


    /*
        function to mark an element as deleted (uses lazy deletion)
        value must be [1,99]
    */
    public boolean delete(int k) {
        if ( k < 1 || k > 99)
            throw new IllegalArgumentException("IllegalArgumentException raised");
        
        TreeNode n = root;

        while(n != null) { // make sure the node is not null
            if( n.key == k) { // found the key, and marking it as deleted
                n.deleted = true;
                return true;
            }
            else if (k < n.key) // key is in left subtree
                n = n.leftChild;
            else if ( k > n.key) // key is in right subtree
                n = n.rightChild;
        }

        return false;
    }

    // function to find the minimum value that is not deleted in the tree
    public int findMin() {
        if (size == 0)  // returns -1 if tree is empty as there is no minimum
            return -1;
        
        int result = -1;
        TreeNode min = root; // start at root

        if(min != null) { // checks to see if root exists
            result = root.key;
            while(min.leftChild != null) { // traversing to the very left of the tree
                if(min.deleted == false) // setting result to the node key if the node is not deleted
                    result = min.key;
                min = min.leftChild;

            }
        }
        return result;
    }
    
    // function to find the maximum value that is not deleted in the tree
    public int findMax() {
        if (size == 0) // returns -1 if tree is empty as there is no minimum
            return -1;
        
        int result = -1; // start at root
        TreeNode max = root;    

        if(max != null) { // checks to see if root exists
            result = root.key;
            while(max.rightChild!= null) { // traversing to the very right of the tree
                if(max.deleted == false) // setting result to the node key if the node is not deleted
                    result = max.key;
                max = max.rightChild;

            }
        }
        return result;
    }

    /* 
        wrapper function to find if the value is in the tree and is not deleted
        value must be [1,99] 
    */
    public boolean contains(int k) {
        if ( k < 1 || k > 99)
            throw new IllegalArgumentException("IllegalArgumentException raised");
        
        return contains(root, k);
    }

    /*
        function which finds if the given key exists in the tree 
        conditions for existing: must exist and must not be deleted
    */
    private boolean contains(TreeNode node, int k) {
        if(node.key == k && node.deleted == false) { // value is found and is not deleted
            return true;
        }else if (k < node.key) { // key is in left subtree
            contains(node.leftChild, k);
        }else if (k > node.key) { // key is in right subtree
            contains(node.rightChild, k);
        }
        return false;
    }

    // wrapper function to return a String
    public String toString() {
        StringBuilder result = new StringBuilder(""); // contains the String meant 
        toString(root, result);
        return result.toString();
    }

    // recursive function to build the String for the preorder traversal of the tree
    private void toString(TreeNode node, StringBuilder result) {
        if(node == null)
            return;
        
        if (node.deleted == true) { // if the node is deleted, then it prepends an asterik before it is added
            result.append("*"); // asteriks indicates the node is deleted
        }
        result.append(node.key + " "); // appending the node key to the string

        toString(node.leftChild, result); // traverse down the left subtree
        toString(node.rightChild, result); // traverse down the right subtree
    }

    // function that returns the height of the tree
    public int height() {
        return height(root);
    }

    // recursive function to calculate and return the height
    private int height(TreeNode node) {
        if (node != null)
            return (node == root ? 0 : 1) + Math.max(height(node.leftChild), height(node.rightChild)); // returns (0 or 1) + maximum of (left subtree height or right subtree height)
        return 0;

    }

    // returns the number of elements in the tree, including deleted ones
    public int size() {
        return size;
    }

    // TreeNode class stores the data, children, and deleted information
    private class TreeNode {
        private int key;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private boolean deleted;

        public TreeNode(int k) { // TreeNode constructor requires a key
            key = k;
            deleted = false; // initially every node is not deleted
        }
    }
}