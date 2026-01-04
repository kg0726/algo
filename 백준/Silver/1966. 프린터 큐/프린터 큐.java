import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 문서의 개수
            int N = Integer.parseInt(st.nextToken());
            // 타겟 문서
            int M = Integer.parseInt(st.nextToken());

            StringTokenizer st2 = new StringTokenizer(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            int maxNum = 0;
            // N만큼 순회하며 큐에 값 채우기
            for (int j=0; j<N; j++) {
                int nowNum = Integer.parseInt(st2.nextToken());
                q.add(nowNum);
                // 최댓값 갱신
                if (nowNum > maxNum) {
                    maxNum = nowNum;
                }
            } // 큐 채우기
            // 큐를 다 채웠다면 하나씩 꺼내서 maxNum인지 확인하기
            int result = 0;
            while (true) {
                // 큐에서 값을 하나 꺼냄
                int nowNum = q.poll();
                // 가장 큰 우선순위가 아닌경우
                if (nowNum != maxNum) {
                    q.add(nowNum);
                    if (M == 0) {
                        M = q.size() - 1;
                        continue;
                    } 
                    M -= 1;
                    // 가장 큰 우선순위를 꺼낸 경우
                } else if (nowNum == maxNum) {
                    // 타겟값과 일치하는지 확인
                    if (M == 0) {
                        result += 1;
                        break;
                } else {
                        M -= 1;
                        maxNum = Collections.max(q);
                        result += 1;
                    }

            }
            }
            sb.append(result).append("\n");
        } // 문제 for
        System.out.println(sb);
    }
}