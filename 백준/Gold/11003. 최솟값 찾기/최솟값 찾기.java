import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int[] arr;

    static class Node {
        int value;
        int idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        Deque<Node> q = new ArrayDeque<>();

        // 시작 처리
        q.add(new Node(arr[1], 1));
        sb.append(arr[1]);
        sb.append(" ");
        for (int i = 2; i < N + 1; i++) {
            int right = arr[i];

            // 만약 큐의 가장 뒷 값이 더 크다면 삭제
            if (q.isEmpty()) {
                q.add(new Node(right, i));
            } else {
                while (true) {
                    if (q.getLast().value > right) {
                        q.pollLast();
                    }

                    if (q.isEmpty() || q.getLast().value <= right) {
                        q.add(new Node(right, i));
                        break;
                    }
                }
            }

            if (i - L + 1 < 1) {
                sb.append(q.getFirst().value);
                sb.append(" ");
                continue;
            }


            sb.append(q.getFirst().value);
            sb.append(" ");
            // 다음 반복 부터는 볼 필요가 없음
            if (q.getFirst().idx <= i - L + 1) {
                q.pollFirst();
            }
        }
        System.out.println(sb);
    }
}