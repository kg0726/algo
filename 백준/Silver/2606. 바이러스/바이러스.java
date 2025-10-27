import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 임시 클래스 생성
class Tmp {
	// bfs 함수 정의
	public static void bfs(boolean[] visit, List<List<Integer>> adjArr, int startNode) {
		int result = 0;
		// 빈 큐 생성
		Queue<Integer> q = new ArrayDeque<>();
		// 큐에 첫번째 노드를 넣음
		q.offer(startNode);
		// 해당 노드 방문처리
		visit[startNode] = true;

		// 큐가 빌 때 까지 진행
		while (q.size() > 0) {
			// 큐에서 값을 하나 꺼냄
			int node = q.poll();
			// 해당 큐와 연결된 다른 노드들을 탐색
			for (int nextNode : adjArr.get(node)) {
				// 방문하지 않았다면
				if (visit[nextNode] == false) {
					// 방문 처리 후 큐에 삽입
					visit[nextNode] = true;
					q.offer(nextNode);
					result += 1;
				}
			}
		}
		System.out.println(result);
	}
}

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();

		// 인접 리스트 생성
		List<List<Integer>> adjArr = new ArrayList<>();

		for (int i = 0; i < V + 1; i++) {
			adjArr.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			int idx = sc.nextInt();
			int idx2 = sc.nextInt();
			adjArr.get(idx).add(idx2);
			adjArr.get(idx2).add(idx);
		}

		// 방문 배열 생성
		boolean visit[] = new boolean[V + 1];
		
		Tmp.bfs(visit, adjArr, 1);

	}// 메인
}
