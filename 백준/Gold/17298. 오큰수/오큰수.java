import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        dp[N - 1] = -1;

        // 뒤에서부터 순회 (arr[N-1]은 이미 -1로 초기화됨)
        for (int i = N - 2; i >= 0; i--) {
            int next = i + 1;

            // 1. 나(arr[i])보다 큰 놈을 만날 때까지 혹은 끝(-1)에 도달할 때까지 점프
            while (next != -1 && arr[i] >= arr[next]) {
                next = dp[next]; // 이미 계산된 오큰수 인덱스로 점프!
            }

            // 2. 루프를 빠져나왔다는 건 오큰수를 찾았거나, 없어서 -1이 됐다는 뜻
            dp[i] = next;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (dp[i] != -1) {
                sb.append(arr[dp[i]]);
            } else {
                sb.append(-1);
            }
            if (i == N - 1) break;
            sb.append(" ");
        }
        System.out.println(sb);
    }
}