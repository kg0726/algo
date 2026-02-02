import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        long result = 0;
        long tmp = 1;
        long N = 0;
        while (result < S) {
            result += tmp;
            tmp += 1;
            N += 1;
        }
        if (result == S) {
            System.out.println(N);
        } else {
            System.out.println(N - 1);
        }

    }
}
