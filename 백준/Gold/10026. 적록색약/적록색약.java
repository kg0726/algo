import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// 적록색약이 아닌사람
	public static void nomalDfs(int r, int c, String[] arr, char cr) {
		// 현재 위치에서 4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 배열의 경계를 확인
			if (0 <= nr && nr < N && 0 <= nc && nc < N) {
				// 이전의 cr 과 같고, 방문한 적이 없다면 방문처리 후 재귀호출
				if (arr[nr].charAt(nc) == cr && !nomalVisit[nr][nc]) {
					nomalVisit[nr][nc] = true;
					nomalDfs(nr, nc, arr, cr);
				}
			}
		}
	}

	// 적록색약인 사람
	public static void dfs(int r, int c, String[] arr, char cr) {
		// 현재 위치에서 4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 배열의 경계를 확인
			if (0 <= nr && nr < N && 0 <= nc && nc < N) {
				// 이전의 cr이 빨강이거나 초록인 경우로 분기
				if (cr == 'R' || cr == 'G') {
					// 다음 위치를 방문하지 않았고 다음 위치가 R, G라면 재귀호출
					if (!visit[nr][nc] && (arr[nr].charAt(nc) == 'G' || arr[nr].charAt(nc) == 'R')) {
						// 방문처리 후 재귀호출
						visit[nr][nc] = true;
						dfs(nr, nc, arr, cr);
					}
					// 빨강, 초록이 아닌 경우
				} else {
					if (!visit[nr][nc] && arr[nr].charAt(nc) == cr) {
						visit[nr][nc] = true;
						dfs(nr, nc, arr, cr);
					}
				}
			}
		}
	}

	public static int N;
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static boolean[][] nomalVisit;
	public static boolean[][] visit;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sc.nextLine();
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextLine();
		}
		// 방문 배열 정의
		visit = new boolean[N][N];
		nomalVisit = new boolean[N][N];

		int result1 = 0;
		int result2 = 0;
		// 입력받은 문자열을 순회
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 해당 위치에 방문한 적이 없다면 DFS
				// 적록색약이 없는 사람의 경우
				if (!nomalVisit[r][c]) {
					result1 += 1;
					// 해당 위치 방문처리
					nomalVisit[r][c] = true;
					nomalDfs(r, c, arr, arr[r].charAt(c));
				}
				// 적록색약이 있는 사람의 경우
				if (!visit[r][c]) {
					result2 += 1;
					// 해당위치 방문처리
					visit[r][c] = true;
					dfs(r, c, arr, arr[r].charAt(c));
				}

			}
		}

		System.out.println(result1 + " " + result2);
	}
}
