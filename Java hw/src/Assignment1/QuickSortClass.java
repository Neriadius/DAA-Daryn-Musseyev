package Assignment1;

import java.util.Arrays;
import java.util.Random;

public class QuickSortClass {

    private static final Random rand = new Random();

    // Public entry point
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // Robust QuickSort implementation
    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            // Randomized pivot
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];

            // Partition
            int i = left, j = right;
            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }

            // Now [left..j] <= pivot <= [i..right]

            // Recurse on smaller part, iterate on larger
            if (j - left < right - i) {
                // Left part smaller
                if (left < j) {
                    quickSort(arr, left, j);
                }
                left = i; // Tail recursion elimination
            } else {
                // Right part smaller
                if (i < right) {
                    quickSort(arr, i, right);
                }
                right = j; // Tail recursion elimination
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Test
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        System.out.println("Before sorting: " + Arrays.toString(arr));

        quickSort(arr);

        System.out.println("After sorting: " + Arrays.toString(arr));
    }

}
