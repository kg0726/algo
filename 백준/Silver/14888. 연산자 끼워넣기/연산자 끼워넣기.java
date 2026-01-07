import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int finish;
    static int[] arr;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;
    public static void recursion(
            int plus, int minus, int multiply, int divide, int depth, int num
    ) {
        if (finish == depth) {
            maxResult = Math.max(maxResult, num);
            minResult = Math.min(minResult, num);
        }
        if (plus > 0) recursion(plus - 1, minus, multiply, divide, depth + 1, num + arr[depth + 1]);
        if (minus > 0) recursion(plus, minus - 1, multiply, divide, depth + 1, num - arr[depth + 1]);
        if (multiply > 0) recursion(plus, minus, multiply - 1, divide, depth + 1, num * arr[depth + 1]);
        if (divide > 0) {
            if (num < 0) {
                recursion(plus, minus, multiply, divide - 1, depth + 1, num / arr[depth + 1]);
            } else {
                num = num * -1;
                num = -(num / arr[depth + 1]);
                recursion(plus, minus, multiply, divide - 1, depth + 1, num);
            }
        }


    }




    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st2.nextToken());
        int minus = Integer.parseInt(st2.nextToken());
        int multiply  = Integer.parseInt(st2.nextToken());
        int divide = Integer.parseInt(st2.nextToken());
        finish = plus + minus + multiply + divide;
        // 입력 끝
        recursion(plus, minus, multiply, divide, 0, arr[0]);
        System.out.println(maxResult);
        System.out.println(minResult);
    }
}
