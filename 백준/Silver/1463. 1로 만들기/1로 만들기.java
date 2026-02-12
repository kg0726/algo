import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        // 시작처리
        dp[1] = 0;
        for (int i = 2; i < N + 1; i++) {
            // 최소 공배수인 경우
            if (i >= 6 && i % 6 == 0){
                dp[i] = Math.min(dp[i / 3] + 1, Math.min(dp[i / 2] + 1, dp[i - 1] + 1));
                continue;
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);

            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);

            } else {
                dp[i] = dp[i - 1] + 1;
            }

        }
        System.out.println(dp[N]);
    }
}