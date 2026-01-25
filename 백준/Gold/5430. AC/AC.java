import java.io.*;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        // 입력 시작
        for (int tc = 0; tc < T; tc++) {
            // 실행할 함수 모음
            String func = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            int start = 0;
            int RCnt= 0; // R 함수가 실행된 횟수
            int DCnt = 0; // D 함수가 실행된 횟수
            // 앞뒤 제거 후 split으로 숫자만 뽑음
            Deque<String> q = new ArrayDeque<>();
            String[] split = arr.substring(1, arr.length() - 1).split(",");
            int end = q.size();
            for (String s : split) {
                if (!s.isEmpty()) q.add(s);
            }
            // 함수 실행
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            boolean flag = true;
            for (char c : func.toCharArray()) {
                if (c == 'R') {
                    RCnt += 1;
                } else if (c == 'D') {
                    // 종료(에러)
                    if (DCnt >= N) {
                        System.out.println("error");
                        flag = false;
                        break;
                    }
                    // R이 홀수번 실행되었을 때
                    if (RCnt % 2 == 1) {
                        // 쉼표까지 진행
                        end -= 1;
                        q.pollLast();
                    } else if (RCnt % 2 == 0) {
                        start += 1;
                        q.pollFirst();
                    }
                    DCnt += 1;
                }
            }
            if (flag) {
                if (RCnt % 2 == 1) {
                    while (!q.isEmpty()) {
                        sb.append(q.pollLast());
                        if (!q.isEmpty()) sb.append(",");
                    }
                } else {
                    while (!q.isEmpty()) {
                        sb.append(q.pollFirst());
                        if (!q.isEmpty()) sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }

        }
        
    }
}
