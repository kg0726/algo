import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        // 빈 스택 생성
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i < T; i++) {
            String line = br.readLine();
            // 공백을 기준으로 문자열을 나눔
            StringTokenizer st = new StringTokenizer(line);

            String prompt = st.nextToken();
            // 만약 명령어가 push라면 뒤에 숫자도 있음
            if (prompt.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
            } else if (prompt.equals("pop")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.pop());
                }
            } else if (prompt.equals("size")) {
                System.out.println(stack.size());
            } else if (prompt.equals("empty")) {
                if (stack.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (prompt.equals("top")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.peek());
                }
            }
        }
    }
}