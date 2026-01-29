import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, PriorityQueue<Integer>> priorityQueueMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                // 키를 가지고 있는지 확인
                if (priorityQueueMap.containsKey(Math.abs(num))) {
                    // 키가 있다면 우선순위큐에 하나 추가
                    priorityQueueMap.get(Math.abs(num)).add(num);
                    pq.add(Math.abs(num));


                } else {  // 키가 없다면 키값쌍 생성
                    PriorityQueue<Integer> tmp = new PriorityQueue<>();
                    tmp.add(num);
                    priorityQueueMap.put(Math.abs(num), tmp);
                    pq.add(Math.abs(num));
                }
            } else {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    // 만약 절댓값 최소가 중복이면
                    if (priorityQueueMap.get(pq.peek()).size() > 1) {
                        // 맵에서 가장 작은걸 출력하고 pq에서도 그 값 삭제
                        Integer poll = priorityQueueMap.get(pq.poll()).poll();
                        System.out.println(poll);
                    } else {
                        // 중복이 아니면 그냥 출력
                        Integer poll = pq.poll();
                        Integer poll1 = priorityQueueMap.get(poll).poll();
                        System.out.println(poll1);
                    }
                }
            }
        }
    }
}