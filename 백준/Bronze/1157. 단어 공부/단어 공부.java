import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		// 입력 끝 로직 구현

		// 문자열의 개수를 카운팅 할 map
		Map<Character, Integer> map = new HashMap<>();

		// 입력받은 문자열을 순회
		for (int i = 0; i < st.length(); i++) {

			// 순회중인 문자를 대문자로 변환
			Character target = Character.toUpperCase(st.charAt(i));

			// 이미 해당 키가 있다면
			if (map.containsKey(target)) {
				// 해당 키의 밸류값을 + 1
				int value = map.get(target);
				value += 1;
				map.put(target, value);

				// 해당 키가 없다면 현재 알파벳을 대문자로 변환하여 삽입
			} else {
				map.put(target, 1);
			}

		} // 문자열 순회 for문

		int maxNum = 0;
		Character maxKey = null;
		for (Character key : map.keySet()) {
			if (map.get(key) > maxNum) {
				maxNum = map.get(key);
				maxKey = key;
			}
		} // map 순회 for문

		int result = 0;
		// 만약 최대값이 두개 이상 있으면 ? 출력
		for (Character key : map.keySet()) {
			if (map.get(key) == maxNum) {
				result += 1;
			}
		} // map 순회

		// result가 2 이상이면 ? 출력
		if (result >= 2) {
			System.out.println('?');
		} else {
			System.out.println(maxKey);
		}

	} // 메인

}