package restOfCodes;

public class SortingVerifier {

    // Part C Framework: Isolates the sort algorithm runtime from outside overheads
    public static void profileMergeSort(int[] array) {
        if (array == null || array.length == 0) return;
        
        long startTime = System.nanoTime(); // Timer start
        mergeSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();   // Timer stop
        
        long durationNano = endTime - startTime;
        System.out.printf("Pure Sort Time Execution: %d ns (~%.4f ms)\n", 
            durationNano, (durationNano / 1_000_000.0));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Part B Verification Logic
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
            k++;
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

}
