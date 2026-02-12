import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long N;
    static long M;
    static long[] arr;

    static boolean validate(long mid) {
        long cnt = 0;
        // 심사대 순회
        for (int i = 0; i < N; i++) {
            // 검증 대상 시간이 가능할지 카운팅
            cnt += mid / arr[i];
            if (cnt >= M) return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        N = tmp;
        M = Integer.parseInt(st.nextToken());
        arr = new long[tmp];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        // 탐색범위 1 ~ 최악의 경우 가장 오래걸리는 심사대에만 친구들이 몰렸을 경우
        long left = 1;
        long right = arr[tmp - 1] * M;
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            // 검증 함수 호출
            if (validate(mid)) {
                result = mid;
                right = mid - 1;
                continue;
            }
            // 검증 실패 시 더 큰 숫자를 찾아야됨
            left = mid + 1;
        }
        System.out.println(result);
    }
}