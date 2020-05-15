/*
    Name: Humza Salman
    NET ID: MHS180007
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        
        Scanner in;
            if (args.length != 2) { // checking for correct number of arguments
                System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
                System.exit(0);
            } 

            try {
                File input_file = new File(args[0]); // first argument is input file
                in = new Scanner(input_file);
                File output_file = new File(args[1]); // second argument is output file
                PrintWriter  out;
                out = new PrintWriter(output_file);
                
                LazyBinarySearchTree tree = new LazyBinarySearchTree(); // initializing the lazy bst

                String regex = "(\\w+)(?: ?:? ?)(\\w*)"; // regex pattern that matches first word, and then the second word. ignores ":" or " : "

                while (in.hasNextLine()) { // finding the next operation
                    
                    String line = in.next(); // retrieving input of the line

                    String[] op = new String[2]; // maximum of two arguments for every command possible (Command, Value)

                    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                    Matcher matcher = pattern.matcher(line); // find matches in the line

                    while (matcher.find()) { // while a match exists
                        for (int i = 1; i <= matcher.groupCount(); i++) {
                            op[i-1]= matcher.group(i); // breaking the match into groups. Group 1- Commands | Group 2- Values
                        }
                    }

                    switch (op[0]) { // determining what the operation is
                        case "Insert":
                            if(op[1].isEmpty()) { // if the command is given, but value is not, then it is an invalid insert command
                                out.println("Error in Line: " + op[0]);
                                break;
                            }   
                            try {
                                int k = Integer.parseInt(op[1]);
                                boolean result = tree.insert(k); // inserting the value into the tree
                                out.println(result ? "True" : "False"); // outputs if the given value was inserted or not  
                            }
                            catch(Exception e) {
                                out.println("Error in insert: " + e.getMessage()); // throws an error if unexpected value
                            }   
                            break;   

                        case "PrintTree":
                            out.println(tree.toString()); // prints the tree in preorder traversal, including deleted nodes
                            break;

                        case "Delete":
                            if(op[1].isEmpty()) { // if the command is given, but value is not, then it is an invalid delete command
                                out.println("Error in Line: " + op[0]);
                                break;
                            }   
                            try {
                                int k = Integer.parseInt(op[1]);
                                boolean result = tree.delete(k); // deleting the given value in the tree
                                out.println(result ? "True" : "False"); // outputs if the given value was deleted or not   
                            }
                            catch(Exception e) {
                                out.println("Error in delete: " + e.getMessage()); // throws an error if unexpected value
                            }
                            break;

                        case "Contains":
                            if(op[1].isEmpty()) { // if the command is given, but value is not, then it is an invalid contains command
                                out.println("Error in Line: " + op[0]);
                                break;
                            }   
                            try {
                                int k = Integer.parseInt(op[1]);
                                boolean result = tree.contains(k); // checking to see if the given value is contained in the tree
                                out.println(result ? "True" : "False"); // outputs if the given value was contained or not     
                            }
                            catch (Exception e) {
                                out.println("Error in contains: " + e.getMessage()); // throws an error if unexpected value
                            }
                            break;

                        case "FindMin":
                            out.println(tree.findMin()); // outputs the minimum in the tree, -1 if it does not exist
                            break;
                        
                        case "FindMax":
                            out.println(tree.findMax()); // outputs the maximum in the tree, -1 if it does not exist
                            break;

                        case "Height":
                            out.println(tree.height()); // outputs the height of the tree
                            break;

                        case "Size":
                            out.println(tree.size()); // outputs the number of elements in the tree
                            break;

                        default:
                            out.println("Error in Line: " + op[0]); // if invalid command, then it outputs an error
                            break;
                    }              
                }
                
                // close input and output file
                in.close(); 
                out.close();
            }
            catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }            
    }
}
