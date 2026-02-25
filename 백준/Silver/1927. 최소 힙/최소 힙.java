import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new PriorityQueue<>();

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                if (q.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(q.poll());
                }
                if (!(i == N - 1)) {
                    sb.append("\n");
                }
            } else {
                q.add(num);
            }
        }
        System.out.println(sb);
    }
}