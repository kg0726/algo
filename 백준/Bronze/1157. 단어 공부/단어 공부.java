import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력 끝
        String line = br.readLine();

        // 빈 맵 생성
        Map<Character, Integer> map = new HashMap<>();
        // 입력받은 문자열을 순회하며 맵에 있는지 확인
        for (Character i: line.toCharArray()) {
            // 순회중인 문자를 대문자로 변환
            Character target = Character.toUpperCase(i);
            if (map.get(target) == null) {
                map.put(target, 1);
            } else {
                int value = map.get(target);
                value += 1;
                map.put(target, value);
            }
        } // 순회 끝
        // 생성된 맵을 순회하며 최댓값을 찾음
        int maxResult = 0;
        int maxCount = 1;
        char result = 0;
        for (Character key: map.keySet()) {
            if (maxResult == map.get(key)) {
                maxCount += 1;
            }
            if (maxResult < map.get(key)) {
                maxResult = map.get(key);
                result = key;
                maxCount = 1;
            }
        } // 순회 끝
        // 출력 확인
        if (maxCount > 1) {
            System.out.println('?');
        } else {
            System.out.println(result);
        }
    }
}