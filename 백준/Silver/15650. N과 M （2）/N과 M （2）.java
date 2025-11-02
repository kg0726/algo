import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	// 방문 배열 정의
	static boolean[] visit;
	static int N;
	static int M;

	static void dfs(int num, int depth, int maxInt, int[] arr) {
		// 종료조건: M과 depth가 같다면 종료
		if (M == depth) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		// 1부터 N까지의 수를 순회하며 사용할지 말지 결정
		for (int i = 1; i < N + 1; i++) {
			// 방문하지 않았고, 현재 배열에 들어간 가장 큰 수보다 크다면 가능
			if (!visit[i] && i > maxInt) {
				visit[i] = true;
				arr[depth] = i;
				dfs(i, depth + 1, i, arr);
				visit[i] = false;
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		for (int i = 1; i < N + 1; i++) {
			// 방문 배열을 N개의 길이로 초기화(0번 인덱스 사용 안함)
			visit = new boolean[N + 1];
			int[] arr = new int[M];
			visit[i] = true;
			arr[0] = i;
			dfs(i, 1, i, arr);
		}

	} // 메인
}