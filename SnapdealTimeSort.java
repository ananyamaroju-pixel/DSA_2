import java.util.Arrays;

public class SnapdealTimeSort {

    // Merge Function
    static void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++)
            left[i] = arr[l + i];

        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {

            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else
                arr[k++] = right[j++];
        }

        while (i < n1)
            arr[k++] = left[i++];

        while (j < n2)
            arr[k++] = right[j++];
    }

    public static void main(String[] args) {

        int arr[] = {
            3, 7, 11, 5, 4, 6, 9,
            12, 1, 2, 8, 10, 13, 14
        };

        System.out.println("Input:");
        System.out.println(Arrays.toString(arr));

        // Run 2 is descending → reverse
        int temp = arr[3];
        arr[3] = arr[4];
        arr[4] = temp;

        System.out.println("\nNatural Runs Identified:");
        System.out.println("Run 1: [3, 7, 11]");
        System.out.println("Run 2: [4, 5]");
        System.out.println("Run 3: [6, 9, 12]");
        System.out.println("Run 4: [1, 2, 8, 10, 13, 14]");

        // Merge Run1 + Run2
        merge(arr, 0, 2, 4);

        // Merge Run3 + Run4
        merge(arr, 5, 7, 13);

        // Merge Final Runs
        merge(arr, 0, 4, 13);

        System.out.println("\nSorted Output:");
        System.out.println(Arrays.toString(arr));
    }
}