import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int[] arr;
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        long sum = 0;
        int result = 0;
        int currentLength = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            int right = Integer.parseInt(st2.nextToken());

            // 일단 큐에 삽입
            deque.add(right);
            sum += right;
            currentLength += 1;
            // 맨 앞의 수를 빼도 S 이상인 동안 반복
            while (sum - deque.getFirst() >= S) {
                sum -= deque.pollFirst();
                currentLength -= 1;
            }

            // 현재 합이 S 이상일 때만 갱신 진행
            if (sum >= S) {
                if (result == 0) {
                    result = currentLength;
                } else {
                    result = Math.min(result, currentLength);
                }
            }
        }
        System.out.println(result);
    }
}