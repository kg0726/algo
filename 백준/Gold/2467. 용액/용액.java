import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 알고리즘 활용
        int left = 0;
        int right = N - 1;
        int result = Integer.MAX_VALUE;
        String answer = null;

        while (left < right) {
            int leftInt = arr[left];
            int rightInt = arr[right];

            // left + right의 절댓값
            int sum = leftInt + rightInt;
            int tmp = Math.abs(sum);
            if (tmp < result) {
                result = tmp;
                answer = leftInt + " " + rightInt;
            }

            if (sum == 0) break;
            else if (sum > 0) {
                right -= 1;
            } else if (sum < 0) {
                left += 1;
            }
        }
        System.out.println(answer);
    }
}
