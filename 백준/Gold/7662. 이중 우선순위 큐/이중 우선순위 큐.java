import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < K; i ++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                // 삽입 연산
                if (st.nextToken().equals("I")) {
                    int nextInt = Integer.parseInt(st.nextToken());
                    if (!map.containsKey(nextInt)) {
                        map.put(nextInt, 1);
                        continue;
                    }
                    map.put(nextInt, map.get(nextInt) + 1);
                    // 삭제 연산
                } else {
                    if (map.isEmpty()) continue;
                    int nextInt;
                    // 최대값 삭제
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        nextInt = map.lastKey();
                    } else {
                        nextInt = map.firstKey();
                    }
                    // 하나 남았으면 삭제
                    if (map.get(nextInt) == 1) {
                        map.remove(nextInt);
                        continue;
                    }
                    // 삭제 연산
                    map.put(nextInt, map.get(nextInt) - 1);
                }
            }
            // 출력
            if (map.isEmpty()) System.out.println("EMPTY");
            else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }

    }
}