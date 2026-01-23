import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Gem implements Comparable<Gem> {
        int m;
        int v;

        public Gem(int m, int v) {
            this.m = m;
            this.v = v;
        }
        @Override
        public int compareTo(Gem o) {
            return this.m - o.m; // 무게 기준 오름차순 정렬
        }
    }
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Gem[] gems = new Gem[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i] = new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // 보석 정보 입력이 끝나면 무게순 정렬
        Arrays.sort(gems);

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 가방 용량 순 정렬

        // 입력 끝 메인 로직--------------------------------------------------
        // 보석의 가치를 담을 우선순위 큐 생성(내림차순 정렬)
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        // 보석이 허용하는 가장 작은 무게부터 시작함
        int gemPointer = 0;
        long result = 0;
        // 가방을 순회
        for (int i = 0; i < K; i++) {
            int nowWeight = bags[i];
            // 만약 현재 가방이 허용하는 무게가 gemPointer가 가리키고 있는 무게보다 작다면 다음으로
            while (gemPointer < N && gems[gemPointer].m <= nowWeight)  {
                // gemPointer가 가리키고 있는 보석의 가치를 우선순위 큐에 삽입
                q.add(gems[gemPointer].v);
                // 다음으로
                gemPointer += 1;
            }
            // while 반복이 끝나면 현재 큐에서 가장 큰 값을 하나 빼옴
            if (q.isEmpty()) continue;
            result += q.poll();
        }
        System.out.println(result);
    }
}