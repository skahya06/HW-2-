package ds;

/**
 * QuickSort partition schemes.
 *
 * Lomuto:
 *  - Pivot is the last element (arr[high]).
 *  - Returns the final index of the pivot after partition.
 *
 * Hoare:
 *  - Pivot is the first element (arr[low]).
 *  - Returns an index j such that:
 *      arr[low..j] <= pivot  AND  arr[j+1..high] >= pivot (in general)
 *    (pivot itself is NOT guaranteed to end up at j).
 */
public class Partition {

    /**
     * Lomuto partition (pivot = arr[high]).
     *
     * @param arr  array to partition
     * @param low  starting index (inclusive)
     * @param high ending index (inclusive)
     * @return pivot's final index
     */
    public static int lomutoPartition(int[] arr, int low, int high) {
        validateInputs(arr, low, high);

        int pivot = arr[high];
        int i = low - 1; // i tracks the end of the "<= pivot" region

        // Move elements <= pivot to the left side
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Place pivot right after the <= region
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Hoare partition (pivot = arr[low]).
     *
     * @param arr  array to partition
     * @param low  starting index (inclusive)
     * @param high ending index (inclusive)
     * @return partition index j
     */
    public static int hoarePartition(int[] arr, int low, int high) {
        validateInputs(arr, low, high);

        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            // Move i right until arr[i] >= pivot
            do {
                i++;
            } while (arr[i] < pivot);

            // Move j left until arr[j] <= pivot
            do {
                j--;
            } while (arr[j] > pivot);

            // If pointers crossed, j is the partition index
            if (i >= j) {
                return j;
            }

            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void validateInputs(int[] arr, int low, int high) {
        if (arr == null) {
            throw new NullPointerException("arr is null");
        }
        if (arr.length == 0) {
            throw new IllegalArgumentException("arr is empty");
        }
        if (low < 0 || high < 0 || low >= arr.length || high >= arr.length) {
            throw new IllegalArgumentException("low/high out of bounds");
        }
        if (low > high) {
            throw new IllegalArgumentException("low cannot be > high");
        }
    }
}
