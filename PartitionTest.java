package ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartitionTest {

    // Arrays from the assignment prompt
    private static final int[] SORTED = {10, 17, 19, 21, 44, 55, 57, 63, 65, 67};
    private static final int[] EMPTY = {};
    private static final int[] UNSORTED = {84, 3, 7, 1, 9, 6, 2, 5};

    // ---------- Lomuto Tests ----------

    @Test
    void lomuto_onSortedArray_shouldReturnLastIndex_andArrayUnchanged() {
        int[] arr = SORTED.clone();

        int pivotIndex = Partition.lomutoPartition(arr, 0, arr.length - 1);

        assertEquals(arr.length - 1, pivotIndex);
        assertArrayEquals(SORTED, arr);

        // Invariant check: everything left of pivot <= pivot
        int pivot = arr[pivotIndex];
        for (int i = 0; i < pivotIndex; i++) {
            assertTrue(arr[i] <= pivot);
        }
    }

    @Test
    void lomuto_onUnsortedArray_shouldPartitionCorrectly_withKnownResult() {
        int[] arr = UNSORTED.clone();

        int pivotIndex = Partition.lomutoPartition(arr, 0, arr.length - 1);

        // With pivot = last element (5), this is the deterministic final arrangement
        assertEquals(3, pivotIndex);
        assertArrayEquals(new int[]{3, 1, 2, 5, 9, 6, 7, 84}, arr);

        // Invariant check
        int pivot = arr[pivotIndex];
        for (int i = 0; i < pivotIndex; i++) assertTrue(arr[i] <= pivot);
        for (int i = pivotIndex + 1; i < arr.length; i++) assertTrue(arr[i] > pivot);
    }

    @Test
    void lomuto_onEmptyArray_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> Partition.lomutoPartition(EMPTY, 0, 0));
    }

    // ---------- Hoare Tests ----------

    @Test
    void hoare_onSortedArray_shouldReturn0_andArrayUnchanged() {
        int[] arr = SORTED.clone();

        int p = Partition.hoarePartition(arr, 0, arr.length - 1);

        assertEquals(0, p);
        assertArrayEquals(SORTED, arr);

        // Invariant check (pivot is original arr[low] = 10)
        int pivot = 10;
        for (int i = 0; i <= p; i++) assertTrue(arr[i] <= pivot);
        for (int i = p + 1; i < arr.length; i++) assertTrue(arr[i] >= pivot);
    }

    @Test
    void hoare_onUnsortedArray_shouldPartitionCorrectly_withKnownResult() {
        int[] arr = UNSORTED.clone();

        int p = Partition.hoarePartition(arr, 0, arr.length - 1);

        // With pivot = first element (84), deterministic outcome for this implementation:
        assertEquals(6, p);
        assertArrayEquals(new int[]{5, 3, 7, 1, 9, 6, 2, 84}, arr);

        // Invariant check using pivot = 84
        int pivot = 84;
        for (int i = 0; i <= p; i++) assertTrue(arr[i] <= pivot);
        for (int i = p + 1; i < arr.length; i++) assertTrue(arr[i] >= pivot);
    }

    @Test
    void hoare_onEmptyArray_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> Partition.hoarePartition(EMPTY, 0, 0));
    }
}
