/**
* <h1>Red Black Tree Library</h1>
* The Red Black Tree Library contains functions to
* perform insertion, search, toString, left rotations,
* right rotations, and tree correction/balancing. All 
* of the function are based on maintaining the propterties of
* a Red Black Tree.
*
* @author  Humza Salman
* @version 1.0
* @since   03/31/2020
*/
public class RedBlackTree<E extends Comparable<E>> {

    private Node<E> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    /**
     * Default constructor for Red Black Tree.
     * Intiliaizes root to null.
     */
    public RedBlackTree() {
        root = null;
    }      

    /**
     * This method is used to add an element iteratively to the
     * red black tree.
     * @param element This is the element to add to the tree.
     * @return <i>True</i> if the element is unique and insertion was successful, <i>False</i> otherwise.
     * @exception NullPointerException On a null parameter.
    */
    public boolean insert(E element) {
        if (element == null) // a null element being inserted throws a null pointer exception
            throw new NullPointerException ("NullPointerException raised");

        if (root == null) { // if the root is null
            root = new Node<E>(element);
            root.color = BLACK; // root is always black
            root.parent = root.leftChild = root.rightChild = null; // initially all are null for root of tree
            return true; // return true since insertion successful
        }

        Node <E> newNode = new Node<E>(element);
        Node <E> node = root;
        boolean result = false;

        while (node != null) {
            if (newNode.element.compareTo(node.element) == 0) // element already exist
                return false; // return false since no new element is added

            else if (newNode.element.compareTo(node.element) < 0) { // element in left sub-tree
                if (node.leftChild == null){
                    node.leftChild = newNode;
                    newNode.color = RED; // all new nodes are inserted as RED
                    newNode.parent = node;
                    newNode.leftChild = newNode.rightChild = null;
                    result = true;
                    break;
                } else 
                    node = node.leftChild;
            }

            else if (newNode. element.compareTo(node.element) > 0) { // element in right sub-tree
                if (node.rightChild == null){
                    node.rightChild = newNode;
                    newNode.color = RED; // all new nods are inserted as RED
                    newNode.parent = node;
                    newNode.leftChild = newNode.rightChild = null;
                    result = true;
                    break;
                } else 
                    node = node.rightChild;
            }
        }

        correctTree(newNode); // correct the tree
        return result; // return if the value was inserted
    }

    /**
     * This method is used to correct the red black tree.
     * @param node This is the node you want to check.
     * @return Nothing.
    */
    private void correctTree(Node<E> node) {
        Node<E> uncle;

        while (node.parent.color == RED) {
            if (node.parent == node.parent.parent.rightChild)
                uncle = node.parent.parent.leftChild; // uncle is left child of grand-parent
            else
                uncle = node.parent.parent.rightChild; // uncle is right child of grand-parent

            if (uncle != null && uncle.color == RED) { // recoloring case
                uncle.color = BLACK; // color uncle black
                node.parent.color = BLACK; // color parent black
                node.parent.parent.color = RED; // color grand-parent red
                node = node.parent.parent; // node is now grand-parent (done so that we can iterate up the tree to correct it)

            } else {
                if (uncle == node.parent.parent.leftChild) { // left rotate required
                    if (node == node.parent.leftChild) { // if the node is the left child of the parent
                        node = node.parent;
                        rotateRight(node); // rotate right on the node
                    }
                    node.parent.color = BLACK; // color parent black
                    node.parent.parent.color = RED; // color grand-parent red
                    rotateLeft(node.parent.parent); // rotate left on the grand-parent

                } else if (uncle == node.parent.parent.rightChild) { // right rotate required
                    if (node == node.parent.rightChild) { // if the node is the right child of the parent
                        node = node.parent;
                        rotateLeft(node); // rotate left on the node
                    }
                    node.parent.color = BLACK; // color parent black
                    node.parent.parent.color = RED; // color grand-parent red
                    rotateRight(node.parent.parent); // rotate right on the grand-parent
                }
            }
        
            if (node == root) { // exit if the root is reached
                break;
            }
        }
        root.color = BLACK; // root is always black
    }

    /**
     * This method is used to perform a left rotation
     * on the given node.
     * @param node This is the node to rotate left.
     * @return Nothing.
    */
    private void rotateLeft(Node<E> node ) {
        Node<E> temp = node.rightChild; // temp is right sub-tree of node
        
        node.rightChild = temp.leftChild; // turn the left sub-tree of temp to node's right sub-tree
        if ( temp.leftChild != null )
            temp.leftChild.parent = node;

        temp.parent = node.parent; // make temp's new parent equal to node's parent

        // make node's parent point to temp
        if (node.parent == null) // check if node is root
            root = temp;
        else if (node == node.parent.leftChild) // node on left side of parent
            node.parent.leftChild = temp;
        else // node on right side of parent
            node.parent.rightChild = temp;

        temp.leftChild = node; // temp's left child is node
        node.parent = temp; // node's parent is therefore temp
    }

    /**
     * This method is used to perform a right rotation
     * on the given node.
     * @param element This is the node to rotate right.
     * @return Nothing.
    */
    private void rotateRight(Node<E> node) {
        Node<E> temp = node.leftChild; // temp is left sub-tree of node

        node.leftChild = temp.rightChild; // turn the right sub-tree of temp to node's left sub-tree
        if (temp.rightChild != null) 
            temp.rightChild.parent = node;

        temp.parent = node.parent; // make temp's new parent equal to node's parent

        // make node's parent point to temp
        if (node.parent == null) // check if node is root
            root = temp;
        else if (node == node.parent.rightChild) // node on right side of parent
            node.parent.rightChild = temp;
        else // node on left side of parent
            node.parent.leftChild = temp;

        temp.rightChild = node; // temp's right child is node
        node.parent = temp; // node's parent is therefore temp
    }

    /**
     * This method is used to search the red black tree
     * to see if the given element exists
     * @param object This is the obect to search in the tree
     * @return <i>True</i> if the element is found, <i>False</i> otherwise.
     * @exception NullPointerException On a null parameter.
    */
    public boolean contains(E object) {
        if (object == null) // if the given element is null, returns false
            return false;

        Node<E> temp = root; // start searching at the root

        while(temp != null) {
            if (temp.element.compareTo(object) == 0) // object == temp.element
                return true;
            else if (temp.element.compareTo(object) < 0) // object > temp.element | right subtree
                temp = temp.rightChild;
            else if (temp.element.compareTo(object) > 0) // object < temp.element | left subtree
                temp = temp.leftChild;
        }
        return false;
    }

    /**
     * This method is a wrapper function to form a string 
     * containing the pre-order traversal of the red black tree.
     * @return String Contains the pre-order traversal of the red black tree. '*' indicates a red node.
    */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(""); // contains the String meant 
        toString(root, result); // call the privte function
        return result.toString();
    }

    /**
     * This method recursively appends to the resulting string the
     * node in pre-order traversal of the red black tree.
     * @param node This is the node to add to the string.
     * @param result This is the resulting string of the pre-order traversal.
     * @return String Contains the pre-order traversal of the red black tree. '*' indicates a red node.
    */
    private void toString(Node<E> node, StringBuilder result) {
        if(node == null) // base case
            return;

        if (node.color == RED) { // if the node is red, then it prepends an asterik
            result.append("*"); // asteriks indicates the node is deleted
        }
        result.append(node.element + " "); // appending the node element to the string

        toString(node.leftChild, result); // traverse down the left subtree
        toString(node.rightChild, result); // traverse down the right subtree
    }

    /**
     * <h1>Node Class</h1>
     * The Node class is a static class that exists within the
     * Red Black Tree Library and is used to keep track of
     * the element's being inserted and their parent, children,
     * and color.
     * 
     * @author Humza Salman
     * @version 1.0
     * @since 03/31/2020
    */
    private static class Node <T extends Comparable<T>>  {
        private T element;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private Node<T> parent;
        private boolean color; // TRUE: black | FALSE: red

        /**
         * Default Node constructor
         * Initialize the variables.
         */
        private Node() {
            element = null;
            leftChild = rightChild = parent = null;
            color = BLACK;
        }

        /**
         * Node constructor which sets the element.
         * @param e The element of the Node is equal to e.
         */
        private Node(T e) {
            this(); // calling default Node constructor
            element = e;
        }
    }
}