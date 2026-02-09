import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
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

    static int primAlgo() {
        // 빈 큐 생성
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[1 + V];

        // 1번에서 시작한다고 가정하고 시작 처리(1번과 인접한 노드를 모두 삽입)
        pq.addAll(adjList[1]);
        // 1번 방문처리
        visited[1] = true;

        int eCnt = 0;
        int result = 0;

        // 큐가 비거나 간선이 모두 생성되기 전까지 실행
        while (!pq.isEmpty() && eCnt < V - 1) {
            // 큐에서 값을 하나 꺼냄
            Node node = pq.poll();
            // 해당 노드와 인접한 노드가 이미 이어졌는지 확인
            if (visited[node.adjNode]) continue;

            // 여기까지 왔다면 노드가 이어져있지 않음 -> 우선순위 큐에서 뽑아 왔으니 반드시 현재 갈 수 있는 노드 중 가장 가중치가 적은 노드가 나옴
            // 방문처리
            visited[node.adjNode] = true;
            eCnt += 1;
            result += node.weight;
            // 해당 노드와 이어져있는 노드 중 방문하지 않은 노드만 삽입
            for (Node nextNode : adjList[node.adjNode]) {
                if (visited[nextNode.adjNode]) continue;
                pq.add(nextNode);
            }
        }
        return result;

    }
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        adjList = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int now, adj, weight;
            now = Integer.parseInt(st2.nextToken());
            adj = Integer.parseInt(st2.nextToken());
            weight = Integer.parseInt(st2.nextToken());

            adjList[now].add(new Node(adj, weight));
            adjList[adj].add(new Node(now, weight));
        }
        // 입력 끝
        System.out.println(primAlgo());
    }
}