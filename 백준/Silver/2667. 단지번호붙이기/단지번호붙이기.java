import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static boolean[][] visit;

	public static int bfs(int[][] arr, int r, int c, int N) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int result = 1;
		// 빈 큐 생성
		Queue<Integer> q = new ArrayDeque<>();
		// 해당 위치 방문처리
		Main.visit[r][c] = true;
		// 큐에 시작 값을 넣음
		q.add(r);
		q.add(c);

		// 큐가 빌 때 까지 진행
		while (!q.isEmpty()) {
			// 큐에서 좌표를 꺼냄
			int nowR = q.poll();
			int nowC = q.poll();
			// 해당 위치에서 4방향을 탐색
			for (int i = 0; i < 4; i++) {
				int nr = nowR + dr[i];
				int nc = nowC + dc[i];
				// 배열의 경계 확인
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					// 방문 확인 및 집인지 확인
					if (!Main.visit[nr][nc] && arr[nr][nc] != 0) {
						result += 1;
						// 방문 처리 후 리스트에 삽입
						Main.visit[nr][nc] = true;
						// 큐에 좌표 삽입
						q.add(nr);
						q.add(nc);
					}
				}

			} // 4방향 탐색
		} // while
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 개행문자 제거
		sc.nextLine();
		// N*N 크기의 빈 배열 생성
		Main.visit = new boolean[N][N];
		int[][] arr = new int[N][N];
		for (int tmp = 0; tmp < N; tmp++) {
			String tmpChar = sc.nextLine();
			for (int idx = 0; idx < N; idx++) {
				int tmpInt = tmpChar.charAt(idx) - '0';
				arr[tmp][idx] = tmpInt;
			}

		} // 입력 끝

		// 빈 리스트 생성
		List<Integer> result = new ArrayList<>();
		// 입력받은 배열을 순회하며 집이고 방문하지 않았다면 bfs 시작
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] != 0 && !Main.visit[r][c]) {
					result.add(Main.bfs(arr, r, c, N));
				}
			}
		}
		Collections.sort(result);
		System.out.println(result.size());
		for(int ans: result) {
			System.out.println(ans);
		}
	}
}