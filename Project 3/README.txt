Name: Humza Salman
NET ID: MHS180007
Class: CS 3345.004

Project 3: Binary Search Trees with Lazy Deletion

Files Included:
    README.txt
    LazyBinarySearchTree.java
    Main.java

Devloped:
    This project was developed in Visual Studio Code.

Compilation:
    JDK 12.0.2 
    No unique compiler options are being used.

Sample Commands:
    LazyBinarySearchTree:
        insert(k) - returns true or false based on if the value was logically inserted in the tree
        delete(k) - if the element exists in the tree, marks it as deleted, and returns true or false
        contains(k) - returns true if the element exists and is not marked as deleted
        height() - returns the height of the tree
        size() - returns the size of the tree
        findMin() - returns the minimum value in the lazy bst
        findMax() - returns the maximum value in the lazy bst
        toString() - returns a String containing a pre-order traversal of the tree

Extra Information:
    The program expects the following format for valid commands
        Insert:96
        Delete :96
        Delete: 96
        Contains : 96
        PrintTree
        FindMin
        FindMax
        Height
        Size

    Invalid Examples of commands:
        hih
        Insert
        Contains
        Delete
