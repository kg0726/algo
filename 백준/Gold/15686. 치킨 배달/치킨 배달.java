import java.io.*;
import java.util.*;

public class Main {
    // 입력에서 주어지는 상수
    public static int N;
    public static int M;

    // 총 치킨집 수
    public static int num;

    // 치킨집 조합
    public static int[] combinationArr;

    // 치킨집 객체 배열
    public static List<Chicken> chickenList;

    // 집 객체 배열
    public static List<House> houseList;


    public static int result;
    // 치킨집 객체
    public static class Chicken {
        public Chicken(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int r;
        public int c;
    }

    // 집 객체
    public static class House {
        public int dis;
        public House(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int r;
        public int c;
    }

    // 치킨거리 찾는 메서드
    public static void distance(int[] arr) {
        int returnValue = 0;
        for (House house : houseList) {
            int tmp = 0;
            for (int i : arr) {
                Chicken chicken = chickenList.get(i);
                if (tmp == 0) {
                    tmp = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
                }
                tmp = Math.min(tmp, Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c));
            }
            returnValue += tmp;
        }
        if (result == 0) {
            result = returnValue;
            return;
        }
        result = Math.min(returnValue, result);
    }

    // 조합 메서드
    public static void combination(int start, int depth) {
        if (depth == M) {
            distance(combinationArr);
            return;
        }

        // 모든 치킨 집을 순회하면서 하나씩 뽑음
        for (int i = start; i < num; i++) {
            combinationArr[depth] = i;
            combination(i + 1, depth + 1);
        }
    }


    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();

        int[][] arr = new int [N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
                if (arr[i][j] == 1) {
                    houseList.add(new House(i, j));
                } else if (arr[i][j] == 2) {
                    chickenList.add(new Chicken(i, j));
                }
            }
        }
        // 치킨 집 수 할당
        num = chickenList.size();
        // 입력 끝 메인 로직
        // 치킨집 조합을 담을 리스트 생성
        combinationArr = new int[M];
        // 조합 및 메인 로직 실행
        combination(0, 0);

        System.out.println(result);
    }
}
