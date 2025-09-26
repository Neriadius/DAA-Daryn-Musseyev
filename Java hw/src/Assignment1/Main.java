package Assignment1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Input value: ");
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();

        int[] sample_arr = new int[len];
        Random random = new Random();

        for (int i = 0; i < len; i++) {
            sample_arr[i] = random.nextInt(1, 999);
        }

        System.out.println("Sample arr: " + Arrays.toString(sample_arr));

        MergeSortClass.mergeSort(sample_arr);

        System.out.println("Sorted arr: " + Arrays.toString(sample_arr));

    }

}
