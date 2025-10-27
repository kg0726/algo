import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		// 개행문자 제거용
		sc.nextLine();
		int result = 0;
		for (int i = 0; i < N; i++) {
			String st = sc.nextLine();
			// 셋 초기화
			Set<Character> set = new HashSet<>();
			// 입력받은 st를 순회함
			for (int j = 0; j < st.length(); j++) {
				Character target = st.charAt(j);
				// 첫번째 순서일 때
				if (j == 0) {
					set.add(target);
					if(j == st.length() - 1) {
						result += 1;
					}
					continue;
				}

				Character prevTarget = st.charAt(j - 1);
				// 이전의 target과 같은지 확인
				if (target.equals(prevTarget)) {
					// 다음으로 넘어감
					if(j == st.length() - 1) {
						result += 1;
					}
					continue;
				} else {
					// set에 있는지 확인
					if (set.contains(target)) {
						break;
					} else { // 없다면 set에 추가
						set.add(target);
					}
				}
				// 끝까지 진행됐다면
				if (j == st.length() - 1) {
					result += 1;
				}
			} // 문자열 순회 for문

		} // 입력 for문
		System.out.println(result);
	}// 메인

}
