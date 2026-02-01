import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static void dfs(boolean[] visited, List<Integer>[] adjArr, int node) {
        int nextNode = 0;
        System.out.print(node);
        System.out.print(" ");
        // 현재 노드에서 갈 수 있는 곳 중 방문한 곳이 아닌 곳이 나올 때 까지 반복
        for (Integer i : adjArr[node]) {
            if (!visited[i]) {
                nextNode = i;
                visited[nextNode] = true;
                dfs(visited, adjArr, nextNode);
            }
        }
        if (nextNode == 0) return; // 더 진행 불가

    }

    static void bfs(List<Integer>[] adjArr) {
        // 빈 큐 생성
        Queue<Integer> q = new ArrayDeque<>();
        // 방문 배열 생성
        boolean[] visited = new boolean[1 + N];
        // 빈 큐에 시작 값을 넣고 방문처리
        visited[V] = true;
        q.add(V);
        // 큐가 빌 때 까지
        while (!q.isEmpty()) {
            // 큐에서 값을 하나 꺼냄
            Integer node = q.poll();
            System.out.print(node);
            for (Integer i : adjArr[node]) {
                if (!visited[i]) {
                    // 큐에 값을 추가하고 방문처리
                    q.add(i);
                    visited[i] = true;
                }
            }
            if (!q.isEmpty()) System.out.print(" ");
        }
    }

    static int N;
    static int M;
    static int V;
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        List<Integer>[] adjArr = new List[1 + N];
        for (int i = 1; i < N + 1; i++) {
            adjArr[i] = new ArrayList<>();
        }
        // 인접 배열 생성
        for (int i = 1; i < M + 1; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st2.nextToken());
            int end = Integer.parseInt(st2.nextToken());

            adjArr[start].add(end);
            adjArr[end].add(start);
        }
        for (int i = 1; i < N + 1 ; i++) {
            Collections.sort(adjArr[i]);
        }
        // 방문 배열 생성
        boolean[] visited = new boolean[N + 1];
        visited[V] = true;
        dfs(visited, adjArr, V);
        System.out.println();
        bfs(adjArr);
    }
}
