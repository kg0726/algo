import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N;
    static int M;
    static List<Node>[] adjList;


    static class Node implements Comparable<Node>{
        int adjNode;
        int weight;

        public Node(int adjNode, int weight) {
            this.adjNode = adjNode;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }


    static int[] bfs(int start) {
        // 방문 배열 생성
        int[] visited = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);

        // 빈 큐 생성
        Queue<Node> q = new PriorityQueue<>();
        // 시작 처리
        q.add(new Node(start, 0));
        visited[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            // 만약 현재 가중치가 더 크다면 볼 필요 없음
            if (node.weight > visited[node.adjNode]) continue;

            // 인접 리스트 순회
            for (Node candidate : adjList[node.adjNode]) {
                // 해당 위치까지의 비용 최솟값 확인 및 갱신
                if (visited[node.adjNode] + candidate.weight < visited[candidate.adjNode]) {
                    visited[candidate.adjNode] = visited[node.adjNode] + candidate.weight;
                    q.add(new Node(candidate.adjNode, visited[candidate.adjNode]));
                }
            }
        }
        return visited;
    }
    public static void main(String[] args) throws IOException {

//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new List[N + 1];

        for (int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < M + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            adjList[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 1; i < N + 1; i++) {
            int[] bfs = bfs(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < 1 + N; j++) {
                if (bfs[j] == Integer.MAX_VALUE) {
                    sb.append(0);
                    sb.append(" ");
                    continue;
                }
                sb.append(bfs[j]);
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }
}
