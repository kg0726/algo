
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] square = new int[N][2];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < 2; c++) {
				square[r][c] = sc.nextInt();
			}
		}
		int result = 0;
		// 100*100 배열 생성
		int[][] arr = new int[100][100];
		// 만들어진 입력 배열을 순회
		for (int i = 0; N > i; i++) {
			int r = 100 - square[i][1] - 9;
			int c = square[i][0];
			// arr을 10*10만큼 순회하며 0을 1로 만든다.
			for (int dr = 0; dr < 10; dr++) {
				for (int dc = 0; dc < 10; dc++) {
					int nr = r + dr;
					int nc = c + dc;
					if (arr[nr][nc] == 0) {
						result += 1;
						arr[nr][nc] = 1;
					}

				}
			}
		} // 입력 배열 순회
		System.out.println(result);
	}

}