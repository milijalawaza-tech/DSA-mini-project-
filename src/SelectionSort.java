public class SelectionSort {

    public static class Result {
        public int comparisons;
        public int swaps;

        public Result(int comparisons, int swaps) {
            this.comparisons = comparisons;
            this.swaps = swaps;
        }
    }

    public static Result sort(int[] array) {

        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                comparisons++;

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {

                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                swaps++;
            }
        }

        return new Result(comparisons, swaps);
    }

    public static void demonstrate() {

        int[] array = {
            17, 5, 23, 8, 14,
            3, 11, 20, 6, 9
        };

        System.out.println("\n--- Selection Sort ---");

        System.out.print("Original array: ");
        printArray(array);

        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                comparisons++;

                System.out.println(
                    "Compare " + array[j] +
                    " with " + array[minIndex]
                );

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {

                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                swaps++;
            }

            if (i < 3) {
                System.out.print(
                    "Pass " + (i + 1) + ": "
                );
                printArray(array);
            }
        }

        System.out.print("Final sorted array: ");
        printArray(array);

        System.out.println(
            "Total comparisons: " + comparisons
        );

        System.out.println(
            "Total swaps: " + swaps
        );
    }

    public static void printArray(int[] array) {

        for (int value : array) {
            System.out.print(value + " ");
        }

        System.out.println();
    }
}