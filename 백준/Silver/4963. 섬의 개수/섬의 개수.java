import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static boolean[][] makeVisit(int N, int M) {
		// 방문 배열을 생성하는 static 함수
		// 방문 배열을 생성
		boolean[][] visit = new boolean[N][M];
		return visit;
	}


	public static boolean[][] visit;

	public static void DFS(int[][] arr, int r, int c, int[] dr, int[] dc, int N, int M) {
		// 종료조건은 아무곳도 방문할 수 없을 때 자동으로 종료된다.
		// 8방향 탐색
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 배열의 경계 확인
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				// 방문하지 않았고 섬이라면
				if (!Main.visit[nr][nc] && arr[nr][nc] == 1) {
					// 방문 처리 후 재귀호출
					Main.visit[nr][nc] = true;
					Main.DFS(arr, nr, nc, dr, dc, N, M);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// 8방향 델타이동
		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
		// 입력
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);

		while (true) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			if (N == 0 && M == 0) {
				break;
			}

			// 입력을 바탕으로 2차원 배열 생성
			int[][] arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			// 방문 배열을 생성
			Main.visit = Main.makeVisit(N, M);
			// 결과값 초기화
			int result = 0;
			// 입력받은 배열을 순회
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					// 만약 해당 위치를 방문하지 않았고 해당 위치가 섬이라면
					if (!Main.visit[r][c] && arr[r][c] == 1) {
						// 새로운 섬을 발견
						result += 1;
						// 방문처리 후
						Main.visit[r][c] = true;
						// dfs진행
						Main.DFS(arr, r, c, dr, dc, N, M);
					}
				}
			} // arr 순회 배열
			System.out.println(result);
		} // 입력 끝

	}

}
