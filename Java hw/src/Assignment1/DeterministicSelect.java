package Assignment1;

import java.util.Arrays;

public class DeterministicSelect {

    // Public entry point
    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return deterministicSelect(arr, 0, arr.length - 1, k);
    }

    private static int deterministicSelect(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            // Median-of-Medians pivot
            int pivot = medianOfMedians(arr, left, right);

            // Partition around pivot
            int pivotIndex = partition(arr, left, right, pivot);

            // k-th order statistic
            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1; // recurse into left
            } else {
                left = pivotIndex + 1;  // recurse into right
            }
        }
    }

    // Partition array around pivot
    private static int partition(int[] arr, int left, int right, int pivotValue) {
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivotValue) i++;
            while (arr[j] > pivotValue) j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i - 1; // final pivot index
    }

    // Median-of-Medians
    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        // Collect medians of groups of 5
        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, left + numMedians, medianIndex);
            numMedians++;
        }

        // Recurse on medians
        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Test
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};

        int k = 3;
        int result = select(arr, k);

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println(k + "-th smallest element = " + result);
    }

}
