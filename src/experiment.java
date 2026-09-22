import java.util.Random;

public class Experiment {

    private static final int[] SIZES = {20, 50, 100, 500};

    // Fixed seed => same "random" numbers every run, so your report's
    // results table is reproducible and so you can re-check your numbers.
    private static final Random RNG = new Random(42);

    // Functional interface so runAndPrint() can accept any of the four
    // sort methods interchangeably.

    private interface SortAlgorithm {
        long sort(int[] arr); // sorts arr in place, returns comparisons made
    }

    // Simple holder for one row of results (used for the auto-summary).

    private static class ResultRow {
        String name;
        int size;
        long comparisons;
        long timeNs;

        ResultRow(String name, int size, long comparisons, long timeNs) {
            this.name = name;
            this.size = size;
            this.comparisons = comparisons;
            this.timeNs = timeNs;
        }
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println(" DSA521S PART C - ALGORITHM EXPERIMENT");
        System.out.println("=================================================================");
        printHeader();

        // We will keep every result row so we can auto-summarise at the end.

        ResultRow[] mainResults = new ResultRow[SIZES.length * 4];
        int idx = 0;

        int[] hundredElementOriginal = null;

        for (int n : SIZES) {
            int[] original = generateRandomArray(n);
            if (n == 100) {
                hundredElementOriginal = copyArray(original);
            }

            mainResults[idx++] = runAndPrint("Selection Sort", n, copyArray(original), Experiment::selectionSort);
            mainResults[idx++] = runAndPrint("Insertion Sort", n, copyArray(original), Experiment::insertionSort);
            mainResults[idx++] = runAndPrint("Merge Sort",     n, copyArray(original), Experiment::mergeSort);
            mainResults[idx++] = runAndPrint("Quick Sort",     n, copyArray(original), Experiment::quickSort);
            System.out.println("-----------------------------------------------------------------");
        }

        System.out.println();
        System.out.println("=================================================================");
        System.out.println(" ALMOST-SORTED 100-ELEMENT TEST");
        System.out.println(" (100-element array sorted, then 5 neighbouring pairs swapped)");
        System.out.println("=================================================================");

        int[] almostSorted = createAlmostSorted(hundredElementOriginal, 5);
        System.out.println("Preview (first 20 values): " + preview(almostSorted, 20));
        System.out.println();
        printHeader();

        ResultRow as1 = runAndPrint("Selection Sort", 100, copyArray(almostSorted), Experiment::selectionSort);
        ResultRow as2 = runAndPrint("Insertion Sort", 100, copyArray(almostSorted), Experiment::insertionSort);
        ResultRow as3 = runAndPrint("Merge Sort",     100, copyArray(almostSorted), Experiment::mergeSort);
        ResultRow as4 = runAndPrint("Quick Sort",     100, copyArray(almostSorted), Experiment::quickSort);

        System.out.println();
        printAutoSummary(mainResults, as1, as2, as3, as4);
    }

    // =================================================================
    // EXPERIMENT HELPERS
    // =================================================================

    private static void printHeader() {
        System.out.printf("%-16s | %-11s | %-14s | %-14s%n",
                "Algorithm", "Input Size", "Comparisons", "Time (ns)");
        System.out.println("-----------------------------------------------------------------");
    }

    private static ResultRow runAndPrint(String name, int size, int[] arr, SortAlgorithm algo) {
        long start = System.nanoTime();
        long comparisons = algo.sort(arr);   // sort() runs here -- this is the ONLY thing timed
        long end = System.nanoTime();
        long timeNs = end - start;

        System.out.printf("%-16s | %-11d | %-14d | %-14d%n", name, size, comparisons, timeNs);
        return new ResultRow(name, size, comparisons, timeNs);
    }

    /** Generates an array of n random integers between 1 and 1000 (inclusive). */

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = RNG.nextInt(1000) + 1;
        }
        return arr;
    }

    /** Manual array copy (no Arrays.copyOf) so every algorithm gets an identical, independent array. */

    private static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    /**
     * Builds the almost-sorted test array required by the spec:
     * 1) sort a copy of the given array in ascending order
     * 2) swap `pairsToSwap` neighbouring pairs, spread evenly across the array
     */

    private static int[] createAlmostSorted(int[] original, int pairsToSwap) {
        int[] sorted = copyArray(original);
        insertionSort(sorted); // use our own verified sort (comparison count discarded here)

        int n = sorted.length;
        int step = n / (pairsToSwap + 1);
        for (int k = 1; k <= pairsToSwap; k++) {
            int i = k * step;
            if (i + 1 < n) {
                int temp = sorted[i];
                sorted[i] = sorted[i + 1];
                sorted[i + 1] = temp;
            }
        }
        return sorted;
    }

    private static String preview(int[] arr, int k) {
        StringBuilder sb = new StringBuilder("[");
        int limit = Math.min(k, arr.length);
        for (int i = 0; i < limit; i++) {
            sb.append(arr[i]);
            if (i < limit - 1) sb.append(", ");
        }
        if (arr.length > limit) sb.append(", ...");
        sb.append("]");
        return sb.toString();
    }

    /** Prints a short, data-driven summary answering Q1-3 from the actual numbers produced above. */

    private static void printAutoSummary(ResultRow[] mainResults, ResultRow as1, ResultRow as2,
                                          ResultRow as3, ResultRow as4) {
        ResultRow fewest = mainResults[0];
        ResultRow most = mainResults[0];
        for (ResultRow r : mainResults) {
            if (r.comparisons < fewest.comparisons) fewest = r;
            if (r.comparisons > most.comparisons) most = r;
        }

        ResultRow[] almost = {as1, as2, as3, as4};
        ResultRow bestAlmost = almost[0];
        for (ResultRow r : almost) {
            if (r.comparisons < bestAlmost.comparisons) bestAlmost = r;
        }

        System.out.println("=================================================================");
        System.out.println(" AUTO-GENERATED SUMMARY (based on the actual run above)");
        System.out.println("=================================================================");
        System.out.println("1. Fewest comparisons (any single run): " + fewest.name
                + " -> " + fewest.comparisons + " comparisons at n=" + fewest.size);
        System.out.println("2. Most comparisons (any single run):   " + most.name
                + " -> " + most.comparisons + " comparisons at n=" + most.size);
        System.out.println("3. Best on almost-sorted array:         " + bestAlmost.name
                + " -> " + bestAlmost.comparisons + " comparisons");
        System.out.println();
        System.out.println("(Use these figures, plus the full table above, to write your");
        System.out.println(" answers to all six analysis questions in the project report.)");
    }

    // =================================================================
    // SORTING ALGORITHMS
    // Each method sorts `arr` in place and returns the number of
    // DATA-VALUE comparisons made (not loop-index/boundary checks).
    // =================================================================

    /** Selection Sort: repeatedly selects the minimum of the unsorted part. */

    private static long selectionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;                    // data comparison: arr[j] vs arr[minIndex]
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return comparisons;
    }

    /** Insertion Sort: builds the sorted array one element at a time. */

    private static long insertionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {                      // j >= 0 is a boundary check, NOT counted
                comparisons++;                    // data comparison: arr[j] vs key
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }

    /** Merge Sort: divide-and-conquer, merges two sorted halves. */

    private static long mergeSort(int[] arr) {
        long[] counter = {0};
        mergeSortHelper(arr, 0, arr.length - 1, counter);
        return counter[0];
    }

    private static void mergeSortHelper(int[] arr, int left, int right, long[] counter) {
        if (left < right) {                       // base case: left >= right (0 or 1 element)
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid, counter);
            mergeSortHelper(arr, mid + 1, right, counter);
            merge(arr, left, mid, right, counter);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, long[] counter) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            counter[0]++;                         // data comparison: L[i] vs R[j]
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];         // leftover copy, no comparison needed
        while (j < n2) arr[k++] = R[j++];
    }

    /** Quick Sort: Lomuto partition scheme, pivot = last element of the sub-array. */
    private static long quickSort(int[] arr) {
        long[] counter = {0};
        quickSortHelper(arr, 0, arr.length - 1, counter);
        return counter[0];
    }

    private static void quickSortHelper(int[] arr, int low, int high, long[] counter) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, counter);
            quickSortHelper(arr, low, pivotIndex - 1, counter);
            quickSortHelper(arr, pivotIndex + 1, high, counter);
        }
    }

    private static int partition(int[] arr, int low, int high, long[] counter) {
        int pivot = arr[high];    // pivot-selection rule: last element of the sub-array
        int i = low - 1;
        for (int j = low; j < high; j++) {
            counter[0]++;                         // data comparison: arr[j] vs pivot
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}