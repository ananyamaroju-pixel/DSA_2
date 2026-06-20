import java.util.*;

public class IncomeTaxSubsetSum {

    public static void main(String[] args) {

        int[] deductions = {
            50000, 30000, 25000,
            15000, 12000, 8000, 5000
        };

        int target = 100000;
        int n = deductions.length;

        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base Case
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Build DP Table
        for (int i = 1; i <= n; i++) {

            for (int sum = 1; sum <= target; sum++) {

                dp[i][sum] = dp[i - 1][sum];

                if (sum >= deductions[i - 1]) {
                    dp[i][sum] =
                        dp[i][sum] ||
                        dp[i - 1][sum - deductions[i - 1]];
                }
            }
        }

        if (!dp[n][target]) {
            System.out.println(
                "No subset exists with sum = ₹" + target
            );
            return;
        }

        System.out.println(
            "Subset exists with sum = ₹" + target
        );

        // Backtracking
        List<Integer> subset = new ArrayList<>();

        int i = n;
        int sum = target;

        while (i > 0 && sum > 0) {

            if (!dp[i - 1][sum]) {

                subset.add(deductions[i - 1]);
                sum -= deductions[i - 1];
            }

            i--;
        }

        System.out.println("\nSelected Deductions:");

        for (int value : subset)
            System.out.println("Rs." + value);
    }
}