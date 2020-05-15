import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
            if (args.length != 4) { // checking for correct number of arguments
                System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
                System.exit(0);
            } 

            try {
                int size = Integer.parseInt(args[0]); // first argument is size?? not mentioned
                File report_txt = new File(args[1]); // second argument is output file
                File unsorted_array = new File(args[2]); // third argument is file name with unsorted array
                File sorted_array = new File(args[3]); // fourth argument is file name with sorted array
                
                PrintWriter  out_report, out_unsorted, out_sorted;
                
                out_unsorted = new PrintWriter(unsorted_array);
                out_report = new PrintWriter(report_txt);
                out_sorted = new PrintWriter(sorted_array);
                
                
                // generate a random array list and assign same list to all lists
                ArrayList<Integer> list1, list2, list3, list4;
                list1 = QuickSorter.generateRandomList(size);
                list2 = list1;
                list3 = list1;
                list4 = list1;

                // output unsorted array to unsorte file
                for (Integer i : list1)
                    out_unsorted.println(i);
                
                // use quick sort and different pivot strategies then output to report.txt
                out_report.println("Array Size = " + size);

                QuickSorter.PivotStrategy ps;

                // first element
                ps = QuickSorter.PivotStrategy.FIRST_ELEMENT;
                Duration duration = QuickSorter.timedQuickSort(list1, ps);
                out_report.println("FIRST_ELEMENT : PT0." + duration.getNano() + "S");
                out_sorted.println("FIRST_ELEMENT_SORTED: ");
                printArrayList(out_sorted, list1);
                
                // random element
                ps = QuickSorter.PivotStrategy.RANDOM_ELEMENT;
                duration = QuickSorter.timedQuickSort(list2, ps);
                out_report.println("RANDOM_ELEMENT : PT0." + duration.getNano() + "S");
                out_sorted.println("RANDOM_ELEMENT_SORTED: ");
                printArrayList(out_sorted, list2);
                
                // median of three random elements
                ps = QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS;
                duration = QuickSorter.timedQuickSort(list3, ps);
                out_report.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0." + duration.getNano() + "S");
                out_sorted.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS_SORTED: ");
                printArrayList(out_sorted, list3);
                
                // median of three elemnts
                ps = QuickSorter.PivotStrategy.MEDIAN_OF_THREE;
                duration = QuickSorter.timedQuickSort(list4, ps);
                out_report.println("MEDIAN_OF_THREE_ELEMENTS : PT0." + duration.getNano() + "S");
                out_sorted.println("MEDIAN_OF_THREE_ELEMENTS_SORTED: ");
                printArrayList(out_sorted, list4);
                

                // debugging purposes only
                // System.out.println("list 1 sorted? " + isSorted(list1));
                // System.out.println("list 2 sorted? " + isSorted(list2));
                // System.out.println("list 3 sorted? " + isSorted(list3));
                // System.out.println("list 4 sorted? " + isSorted(list4));

                
                
                // close output file
                out_unsorted.close();
                out_report.close();
                out_sorted.close();
            }
            catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }            
    }

    /**
     * outputs an array list to the file
     * @param out PrintWriter to output to file
     * @param list ArrayList of type Integer to output
     */
    public static void printArrayList(PrintWriter out, ArrayList<Integer> list) {
        // output array list to the file
        for (Integer i: list)
            out.println(i);
    }

    /**
     * Function to check if arraylist is sorted
     * @param list ArrayList to check if sorted
     * @return true if sorted and false if unsorted
     */
    public static boolean isSorted(ArrayList<Integer> list) {
        ArrayList<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy.equals(list);
    }

}
