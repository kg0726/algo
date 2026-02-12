import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int C;
    static int[] arr;
    static boolean validate(int min) {
        int count = 0;
        int prevRouter = arr[0];
        // arr을 순회하며 이전 값과 현재 값이 min 이상 차이나는 값이 몇개인지 탐색
        for (int i = 1; i < N; i++) {
            if (arr[i] - prevRouter >= min) {
                count += 1;
                prevRouter = arr[i];
                if (count >= C - 1) return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        } // 입력 끝
        Arrays.sort(arr);

        // 탐색 범위는 집의 좌표가 아니라 집의 간격임
        // 최소 간격은 1(집이 바로 붙어 있는 경우, 최대 간격은 arr[0]과 arr[N - 1]의 차이
        int left = 1;
        int right = arr[N - 1] - arr[0];
        int result = 0;
        // 이분탐색 시작
        while (left <= right) {
            int mid = (left + right) / 2;
            // 중간 값의 차이 이상으로 C개만큼 공유기를 놓을 수 있는지 검증해야 함
            if (validate(mid)) {
                result = Math.max(mid, result);
                left = mid + 1;
                continue;
            }
            // 검증에 실패했다면 해당 값 부터는 절대로 정답이 될 수 없음
            right = mid - 1;
        }

        System.out.println(result);

    }
}