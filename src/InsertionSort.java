public class InsertionSort {

    public static class Result {
        public int comparisons;
        public int shifts;

        public Result(int comparisons, int shifts) {
            this.comparisons = comparisons;
            this.shifts = shifts;
        }
    }

    public static Result sort(int[] array) {

        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                if (array[j] > key) {

                    array[j + 1] = array[j];
                    shifts++;

                    j--;

                } else {
                    break;
                }
            }

            array[j + 1] = key;
        }

        return new Result(comparisons, shifts);
    }

    public static void demonstrate() {

        int[] array = {
            17, 5, 23, 8, 14,
            3, 11, 20, 6, 9
        };

        System.out.println("\n--- Insertion Sort ---");

        System.out.print("Original array: ");
        printArray(array);

        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                if (array[j] > key) {

                    array[j + 1] = array[j];
                    shifts++;

                    j--;

                } else {
                    break;
                }
            }

            array[j + 1] = key;

            if (i <= 3) {

                System.out.print(
                    "Pass " + i + ": "
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
            "Total shifts: " + shifts
        );
    }

    public static void printArray(int[] array) {

        for (int value : array) {
            System.out.print(value + " ");
        }

        System.out.println();
    }
}
