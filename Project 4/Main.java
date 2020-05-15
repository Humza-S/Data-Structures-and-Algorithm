/**
* <h1>Main Class/Driver Class</h1>
*
* The main class simply tests the method of the
* Red Black Tree Library by providing input through
* input files and outputting them to the output files.
*
* @author  Humza Salman
* @version 1.0
* @since   03/31/2020
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /** This is the main method which makes use of the Red Black Tree library
     *  by testing the given test files.
     * @param args arg[0] is the input file. arg[1] is the output file.
     * @return Nothing
     */
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
                String type = in.nextLine();

                if (!(type.equals("Integer")) && !(type.equals("String"))) { // only Integer and String objects allowed for Red Black Tree
                    out.println("Only works for objects Integers and Strings");
                    
                    // close input and output files
                    in.close();
                    out.close();

                    System.exit(0);
                }
                
                RedBlackTree<Integer> integerTree = new RedBlackTree<>();
                RedBlackTree<String> stringTree = new RedBlackTree<>();

                String regex = "(\\w+)(?: ?:? ?)(-?\\w*)"; // regex pattern that matches first word, and then the second word. ignores ":" or " : "

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
                                boolean result = false;
                                if (type.equals("Integer"))
                                    result = integerTree.insert(Integer.valueOf(op[1]));
                                else
                                    result = stringTree.insert(op[1]);
                                
                                out.println(result ? "True" : "False"); // outputs if the given value was inserted or not  
                            }
                            catch(Exception e) {
                                out.println("Error in insert: " + e.getMessage()); // throws an error if unexpected value
                            }   
                            break;   

                        case "PrintTree":
                            if (type.equals("Integer"))
                                out.println(integerTree.toString()); // prints the tree in preorder traversal, including deleted nodes
                            else
                                out.println(stringTree.toString()); // prints the tree in preorder traversal, including deleted nodes
                            break;
                        case "Contains":
                            if(op[1].isEmpty()) { // if the command is given, but value is not, then it is an invalid contains command
                                out.println("Error in Line: " + op[0]);
                                break;
                            }   
                            try {
                                //int k = Integer.parseInt(op[1]);
                                boolean result = false;
                                if (type.equals("Integer"))
                                    result = integerTree.contains(Integer.valueOf(op[1]));
                                else 
                                    result = stringTree.contains(op[1]); // checking to see if the given value is contained in the tree
                                out.println(result ? "True" : "False"); // outputs if the given value was contained or not     
                            }
                            catch (Exception e) {
                                out.println("Error in contains: " + e.getMessage()); // throws an error if unexpected value
                            }
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
