import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
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

        Arrays.sort(arr);

        int result = 0;
        // 투포인터 알고리즘 적용
        for (int i = 0; i < N; i++) {
            int target = arr[i];

            int left = 0;
            int right = N - 1;

            // 해당 수가 좋거나, 오른쪽이 왼쪽을 지나가면 종료
            while (right > left) {
                // 해당 수와 같은 자리의 수는 덧셈에 포함시키면 안됨
                if (left == i) {
                    left += 1;
                    continue;
                }
                if (right == i) {
                    right -= 1;
                    continue;
                }


                int sumNum = arr[left] + arr[right];
                // 찾았으면 종료
                if (target == sumNum) {
                    result += 1;
                    break;
                }
                if (target > sumNum) {
                    left += 1;
                    continue;
                }
                if (target < sumNum) {
                    right -= 1;
                }
            }
        }

        System.out.println(result);
    }
}