import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	// 알파벳과 매핑되는 26개의 불리언 배열 생성
	public static boolean[] alpha = new boolean[26];
	// 상하좌우
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	// 입력받을 배열을 미리 선언
	public static String[] arr;
	// 알파벳 인덱싱을 위한 map
	public static Map<Character, Integer> alphaMap;

	public static void dfs(int r, int c, int N, int M, int depth) {
		// 최대값 갱신
		if (result < depth) {
			result = depth;
		}
		// 현재 위치에서 4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 배열 경계 확인
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				// 만약 다음 위치가 방문한 적 없다면(새로운 알파벳이라면)
				if (!alpha[alphaMap.get(arr[nr].charAt(nc))]) {
					// 방문 처리 후 재귀 호출
					alpha[alphaMap.get(arr[nr].charAt(nc))] = true;

					dfs(nr, nc, N, M, depth + 1);
					// 백트래킹
					alpha[alphaMap.get(arr[nr].charAt(nc))] = false;

				}
			}
		}
	}

	public static int result;

	public static void main(String[] args) throws FileNotFoundException {
		// 알파벳 인덱싱을 위한 map 생성
		alphaMap = new HashMap<>();
		alphaMap.put('A', 0);
		alphaMap.put('B', 1);
		alphaMap.put('C', 2);
		alphaMap.put('D', 3);
		alphaMap.put('E', 4);
		alphaMap.put('F', 5);
		alphaMap.put('G', 6);
		alphaMap.put('H', 7);
		alphaMap.put('I', 8);
		alphaMap.put('J', 9);
		alphaMap.put('K', 10);
		alphaMap.put('L', 11);
		alphaMap.put('M', 12);
		alphaMap.put('N', 13);
		alphaMap.put('O', 14);
		alphaMap.put('P', 15);
		alphaMap.put('Q', 16);
		alphaMap.put('R', 17);
		alphaMap.put('S', 18);
		alphaMap.put('T', 19);
		alphaMap.put('U', 20);
		alphaMap.put('V', 21);
		alphaMap.put('W', 22);
		alphaMap.put('X', 23);
		alphaMap.put('Y', 24);
		alphaMap.put('Z', 25);

		// 입력 시작
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		sc.nextLine();
		arr = new String[R];
		for (int i = 0; i < R; i++) {
			arr[i] = sc.nextLine();
		}
		// 첫번째 자리 알파벳 방문처리
		alpha[alphaMap.get(arr[0].charAt(0))] = true;
		// 결과값 초기화
		result = 0;
		// 재귀 함수 호출
		dfs(0, 0, R, C, 1);
		System.out.println(result);

	}
}