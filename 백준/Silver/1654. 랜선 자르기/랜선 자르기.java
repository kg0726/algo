import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int N;

    public static long binarySearch(long rightNum, int[] arr) {
        // 이분탑색
        long leftNum = 1;
        long centerNum;
        long result = 0;
        while (leftNum <= rightNum) {
            // 1 ~ maxNum까지 이분탐색
            centerNum = (leftNum + rightNum) / 2;
            // 중간 값으로 N 이상을 만들 수 있는지 확인

            long verify = 0;
            for (int i : arr) {
                verify += i/centerNum;
            }
            // 만약 verify 값이 N을 넘기지 못했다면 다음 반복 진행
            if (verify < N) {
                rightNum = centerNum - 1;
                continue;
            }

            // verify 값이 N을 넘겼다면 최댓값 갱신 후 다음 반복 진행
            result = Math.max(result, centerNum);
            leftNum = centerNum + 1;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        long maxNum = 0;
        for (int i = 0; i < K; i++) {
            int num;
            num = Integer.parseInt(br.readLine());
            arr[i] = num;
            maxNum = Math.max(maxNum, num);
        } // 입력 완료

        System.out.println(binarySearch(maxNum, arr));


    }
}