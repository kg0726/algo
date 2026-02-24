import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    static double getInclination (double x1, double x2, double y1, double y2) {
        return (y2 - y1) / (x2 - x1);
    }
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;
        arr = new int[1 + N];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (arr.length == 2) {
            System.out.println(result);
            return;
        }

        // 순회 시작
        for (int i = 1; i < N + 1; i++) {
            int tmp = 0;
            // 오른쪽으로
            for (int j = i + 2; j < N + 1; j++) {
                double inclination = getInclination(i, j, arr[i], arr[j]);
                double Y = arr[i] - inclination * i;
                boolean flag = true;
                // j까지 갈 수 있는지 확인
                for (int k = i + 1; k < j; k++) {
                    int target = arr[k];
                    if (inclination * k + Y <= target) {
                        flag = false;
                        break;
                    }
                }
                if (flag) tmp += 1;
            }

            // 왼쪽으로
            for (int j = i - 2; j > 0 ; j--) {
                double inclination = getInclination(i, j, arr[i], arr[j]);
                double Y = arr[i] - inclination * i;
                boolean flag = true;
                // j까지 갈 수 있는지 확인
                for (int k = i - 1; k > j; k--) {
                    int target = arr[k];
                    if (inclination * k + Y <= target) {
                        flag = false;
                        break;
                    }
                }
                if (flag) tmp += 1;
            }

            if (i == 1) tmp += 1;
            else if (i == N) {
                tmp += 1;
            } else tmp += 2;
            result = Math.max(result, tmp);
        }
        System.out.println(result);

    }
}