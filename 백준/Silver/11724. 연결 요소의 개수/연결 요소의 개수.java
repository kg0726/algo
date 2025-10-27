import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void dfs(int node, List<List<Integer>> adjArr) {
		// 종료조건: 더 이상 갈 수 있는 곳이 없다면 자동 종료
		// 현재 정점에서 인접한 정점을 찾는다.
		for(int nextNode: adjArr.get(node)) {
			// 해당 정점에 방문하지 않았다면 방문 처리 후 재귀호출
			if(!Main.visit[nextNode]) {
				Main.visit[nextNode] = true;
				// 재귀호출
				Main.dfs(nextNode, adjArr);
			}
		}
		
	}
	public static boolean[] visit;
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		// 빈 인접리스트 생성
		List<List<Integer>> adjArr = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			adjArr.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < E; i++) {
			int tmp1 = sc.nextInt();
			int tmp2 = sc.nextInt();
			adjArr.get(tmp1).add(tmp2);
			adjArr.get(tmp2).add(tmp1);
		}
		// 방문 배열 초기화
		visit = new boolean[V + 1];
		int result = 0;
		// 1번부터 V번 까지의 정점들을 순회하며 방문하지 않았다면 DFS 시작
		for(int i=1; i < V + 1; i ++) {
			if(!Main.visit[i]) {
				// DFS를 호출한다는 것은 새로운 연결 요소가 발견됨
				result += 1;
				Main.dfs(i, adjArr);
			}
		}
		System.out.println(result);
		
	}// 메인
}
