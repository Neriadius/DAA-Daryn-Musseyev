package Assignment1;

public class MergeSortClass {

    // Cutoff value for switching to insertion sort
    private static final int CUTOFF = 13;

    // Public entry point
    public static void mergeSort(int[] arr) {
        int[] buffer = new int[arr.length]; // reusable buffer
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    // Recursive merge sort with buffer
    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);

        // Merge the two halves
        merge(arr, buffer, left, mid, right);
    }

    // Merge step using reusable buffer
    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        // Copy to buffer
        for (int i = left; i <= right; i++) {
            buffer[i] = arr[i];
        }

        int i = left;     // pointer for left half
        int j = mid + 1;  // pointer for right half
        int k = left;     // pointer for main array

        while (i <= mid && j <= right) {
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        // Copy any remaining elements from left half
        while (i <= mid) {
            arr[k++] = buffer[i++];
        }

        // Right half elements are already in place
    }

    // Insertion sort for small arrays
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Example test
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10, 14, 34, 29, 55};

        System.out.println("Before sorting:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        mergeSort(arr);

        System.out.println("After sorting:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
}


