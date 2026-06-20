import java.util.Arrays;

public class AadhaarRadixSort {

    static void countingSort(int arr[], int exp) {

        int n = arr.length;
        int output[] = new int[n];
        int count[] = new int[10];

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);

        System.out.println("After Pass (exp = " + exp + "): "
                + Arrays.toString(arr));
    }

    static void radixSort(int arr[]) {

        int max = arr[0];

        for (int num : arr)
            if (num > max)
                max = num;

        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSort(arr, exp);
    }

    public static void main(String[] args) {

        int arr[] = {
            473, 152, 681, 247,
            539, 826, 715, 304
        };

        System.out.println("Input:");
        System.out.println(Arrays.toString(arr));

        System.out.println("\nApplying LSD Radix Sort...\n");

        radixSort(arr);

        System.out.println("\nFinal Sorted UIDs:");
        System.out.println(Arrays.toString(arr));
    }
}