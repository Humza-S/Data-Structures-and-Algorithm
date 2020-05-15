/*
    Name: Humza Salman
    NET ID: MHS180007
*/

public class IDedLinkedList <AnyType extends IDedObject> {
    private Node<AnyType> head;

    // constructor
    public IDedLinkedList() {
        head = null;
    }

    // returns if the list is empty or not
    private boolean empty() {
        return head == null; // if there is no head, then there is no list, therefore the list is empty
    }

    // empties the linked list
    public void makeEmpty() {
        Node<AnyType> current = head.next;

        // going through the list and making each node null
        while (current.next != null) {
            Node<AnyType> nextNode = current;
            current.next = null;
            current.data = null;
            current = nextNode;
        }
        head.next = null;
        head = null;
    }

    // wrapper function to find ID
    public AnyType findID(int ID) {
        return findID(head, ID);
    }

    // Get the generic type to get the particular id and
    // returns AnyType. Returns null if the list is empty
    // or ID not found
    private AnyType findID(Node<AnyType> node, int ID) {
        if(node != null) {
            if(node.data.getID() == ID) { // compares ID of existing node to the given ID
                return node.data; // returns the node if the ID is found
            }
            return findID(node.next, ID); // recursively call the function using the node's next pointer
        }
        return null;
    }

    // insert at front of list or return false if that ID
    // already exists
    public boolean insertAtFront(AnyType value) {
        if (findID(value.getID()) != null) // if the ID already exists, then it can not be inserted again
            return false;

        if (empty()) { // if the list is empty, then create a new Node to insert the item
            head = new Node<AnyType>(value);
        } else { // insert a new item and make it the head of the linked list
            Node<AnyType> newNode = new Node<>(value);
            newNode.next = head;
            head = newNode;
        }
        return true;
    }

    // delete and return the record at the front of the list
    // or return null if the list is empty
    public AnyType deleteFromFront() {
        if(empty()) { // if the list is empty then there is nothing to delete
            return null;
        }

        // reassigning the pointers of the deleted head
        Node<AnyType> temp = head; // storing the deleted node
        head = head.next;

        return temp.data;
    }

    // finds and deleted the record with the given ID, returns the deleted type
    // returns null if it isn't found
    public AnyType delete(int ID) {
        if (empty()) // if the linked list is empty then there is nothing to delete
            return null;
        
        Node<AnyType> temp = head; // holds the value of head since the head of the list will be removed
        if(head.data.getID() == ID){
            head = temp.next;
            return temp.data;
        }

        Node<AnyType> current = head.next; // holds the current node, used in traversing the linked list
        while(current != null) { // traversing through linkeded list to find the preceeding element of the element with the ID
            if(current.data.getID() == ID) { // if the node is found, then the loop exits
                break;
            }
            temp = current; // stores node preceeding current
            current = current.next;
        }

        if(temp == null || temp.next == null) // returns null if the previous node is null or the next is null
            return null;
        
        // reassigning the pointers of the preceeding and succeeding node of the deleted node.
        Node<AnyType> next = temp.next.next;
        temp.next = next;

        return current.data;
    }

    // return the sum of IDs of all elements currently in the list.
    // if the list is empty returns -1
    public int printTotal() {
        if(empty()) // returns -1 if the list is empty
            return -1;

        int sum = 0;
        Node<AnyType> current = head;
        
        // looping through the list and adding the ID of the element
        while (current != null) { 
            sum += current.data.getID();
            current = current.next;
        }
        return sum;
    }

    // node class to hold data and next pointers for the linked list
    public class Node <T extends IDedObject> {
        private T data; // holds AnyType object extending the IDedObject interface
        Node<T> next; // holds the next value in the linked list
        
        // constructor for making a node with data
        Node(T data) {
            this.data = data;
        }
    }
}