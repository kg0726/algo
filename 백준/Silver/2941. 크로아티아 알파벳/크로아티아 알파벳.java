import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("sample.txt"));
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		// 입력받은 문자열을 거꾸로 순회함
		int result = 0;
		for (int i = st.length() - 1; i > -1; i--) {
			if(i <= 0) {
				if (i == 0) {
					result += 1;
				}
				break;
			}
			result += 1;
			// 현재 알파벳이 크로아티아 알파벳의 조합에 해당된다면
			char nowChar = st.charAt(i);
			if (nowChar == '=') {
				if (i >= 2 && st.charAt(i - 1) == 'z' && st.charAt(i - 2) == 'd') {
					i -= 2;
					continue;
				}
				char prevChar = st.charAt(i - 1);
				if (prevChar == 'c' || prevChar == 's' || prevChar == 'z') {
					i -= 1;
				}
				
			}
			else if (nowChar == '-') {
				char prevChar = st.charAt(i - 1);
				if (prevChar == 'c' || prevChar == 'd') {
					i -= 1;
				}
			}
			else if (nowChar == 'j') {
				char prevChar = st.charAt(i - 1);
				if (prevChar == 'l' || prevChar == 'n') {
					i -= 1;
				}
			}
		}
		System.out.println(result);

	}// 메인
}
