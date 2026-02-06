import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node> {
        int adjNode;
        int weight;

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        public Node(int adjNode, int weight) {
            this.adjNode = adjNode;
            this.weight = weight;
        }

    }

    static int[] bfs(int K, int V) {
        // 빈 큐 생성
        Queue<Node> q = new PriorityQueue<>();
        // 큐에 시작값 삽입
        q.add(new Node(K, 0));

        int[] visited = new int[V + 1];
        // 최댓값으로 채움
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[K] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int nowNode = node.adjNode; // 과거의 타겟 노드
            int weight = node.weight;

            // 가지치기(현재 최솟값보다 weight가 크다면 볼 필요 없음)
            if (visited[nowNode] < weight) continue;
            // 인접 노드 탐색
            for (Node nextNode : adjList[nowNode]) {
                // 다음 노드에 이전에 도달했던 최솟값보다 지금 도달하기 위한 최솟값이 더 작다면 갱신 후 큐에 삽입
                if (visited[nowNode] + nextNode.weight < visited[nextNode.adjNode]) {
                    visited[nextNode.adjNode] = visited[nowNode] + nextNode.weight;
                    q.add(new Node(nextNode.adjNode, visited[nextNode.adjNode]));
                }
            }

        }
        return visited;
    }

    static List<Node>[] adjList;
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V, E;
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        adjList = new ArrayList[V + 1];

        for (int i = 1; i < V + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < E + 1; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int s, e, w;
            s = Integer.parseInt(st2.nextToken());
            e = Integer.parseInt(st2.nextToken());
            w = Integer.parseInt(st2.nextToken());
            adjList[s].add(new Node(e, w));
        }

        int[] bfs = bfs(K, V);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V + 1; i++) {
            if (bfs[i] == Integer.MAX_VALUE) {
                sb.append("INF");
                if (i != V) sb.append("\n");
                continue;
            }
            sb.append(bfs[i]);
            if (i != V) sb.append("\n");
        }
        System.out.println(sb);
    }
}
