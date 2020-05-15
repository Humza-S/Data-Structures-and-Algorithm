import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuickSorter {
    public static enum PivotStrategy {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE
    }

    /**
     * This function finds the elapsed time to perform
     * quick sort on an array list given the pivot strategy.
     * @param list the array list to time
     * @param pivotStrategy the pivot strategy to use to find the pivot
     * @return returns the elapsed duration to perform the quicksort
     *         given the pivot strategy
     * @throws NullPointerException the parameters should not be null
     */
    public static <E extends Comparable<E>> Duration 
    timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException {
        if (pivotStrategy == null) // if pivot strategy is null then throws null pointer exception
            throw new NullPointerException("Invalid pivot strategy provided. Please provide a valid pivot strategy.");
        
        if (list == null || list.isEmpty()) // if list is empty throws null pointer exception
            throw new NullPointerException("The given list is empty or null.");

        long time_start = java.lang.System.nanoTime(); // start time
        
        QuickSort(list, 0, list.size() - 1, pivotStrategy); // perform quick sort

        long time_end = java.lang.System.nanoTime(); // end time
        long duration = time_end - time_start; // time to sort

        Duration time = Duration.ofNanos(duration);
        return time;
    }

    /**
     * This function performs a quick sort recursively
     * for the given array list, low, high, and pivot strategy.
     * @param list The array list to perform the quick sort on
     * @param low The start of the array list
     * @param high The end of the array list
     * @param pivotStrategy The pivot strategy to use to find the pivot
     */
    public static <E extends Comparable<E>> void 
    QuickSort (ArrayList<E> list, int low, int high, PivotStrategy pivotStrategy) {
        if ((high - low) < 20) { // use insertion sort if size less than 20
            InsertionSort(list, low, high);
            return;
        }
        if (low < high + 1) { // conditions to perform quick sort
            int p = partition(list, low, high, pivotStrategy);
            QuickSort(list, low, p - 1, pivotStrategy);
            QuickSort(list, p + 1, high, pivotStrategy);
        }
    }

    /**
     * This function returns an index of partition
     * @param list the array list
     * @param low the lower bound of the array list
     * @param high the upper bound of the array list
     * @param pivotStrategy the pivot strategy to use to find the pivot
     * @return
     */
    private static <E extends Comparable<E>> int
    partition(ArrayList<E> list, int low, int high, PivotStrategy pivotStrategy) {
		Collections.swap(list, low, findPivot(low, high, pivotStrategy));
		int border = low + 1;
		for(int i = border; i <= high; i++) {
            if (list.get(i).compareTo(list.get(low)) < 0) {
                Collections.swap(list, i, border++);
			}
		}
        Collections.swap(list, low, border - 1);
        return border - 1;
	}

    /**
     * Sorts the original array list using insertion sort
     * @param list The array list to be sorted
     * @param low The starting element index
     * @param high The ending element index
     */
    public static <E extends Comparable<E>> void
    InsertionSort (ArrayList<E> list, int low, int high) {
        for (int i = low + 1; i < high + 1; i++) {
            E key = list.get(i);
            int j = i - 1; // traverse through array from i-1 to the front
            while (j >= 0 && (key.compareTo(list.get(j)) < 0)){ // keep looping while front of array is not reached and arr[i] < arr[j]
                Collections.swap(list, j, j + 1);
                j--; 
            }
        }
    }

    /**
     * This function finds the correct pivot for the
     * pivot strategy provided.
     * @param low the index to start on
     * @param high the index to end on
     * @param pivotStrategy the pivot strategy to use to find the pivot
     * @return an integer containing the pivot for the given 
     *         pivot strategy or -1 by default
     * @throws IllegalArgumentException must provide a valid pivot strategy
     */
    private static int findPivot(int low, int high, PivotStrategy pivotStrategy) throws IllegalArgumentException{
        int pivot = 0; // default pivot is -1.
        Random randy = new Random(); // meet randy

        switch (pivotStrategy) {
            case FIRST_ELEMENT:
                pivot = low; // pivot = first element
                break;
            case RANDOM_ELEMENT:
                pivot = randy.nextInt((high - low) + 1) + low; // pivot = random int
                break;
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                pivot = ( (randy.nextInt((high - low) + 1) + low) + 
                        (randy.nextInt((high - low) + 1) + low) +
                        (randy.nextInt((high - low) + 1) + low) ) / 3; // pivot = (random1 + random2 + random3) / 3
                break;
            case MEDIAN_OF_THREE:
                pivot = (low + high + ((low + high) / 2) ) / 3; // pivot = (low + high + middle) / 3
                break;
            default:
                throw new IllegalArgumentException("Invalid pivot strategy provided");
        }
        return pivot;
    }

    /**
     * This method generates an ArrayList<Integer> filled
     * with random numbers between 1 to 1,000,000.
     * @param size Size of the ArrayList to be generated
     * @return The ArrayList of type Integer
     * @throws IllegalArgumentException
     */
    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException {
        if (size < 0) // if size is negative, throw illegal argument exception
            throw new IllegalArgumentException("Given size should be nonnegative."); 

        ArrayList<Integer> list = new ArrayList<>(size); // array list to hold integers
        Random randy = new  Random(); // random object used to generate random integers

        for (int i = 0; i < size; i++) // loop through array
            list.add(i, randy.nextInt(1000000) + 1); // add numbers between 1-1,000,000 at index i

        return list;        
    }
}