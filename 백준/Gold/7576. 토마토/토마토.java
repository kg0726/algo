import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        // 익은 토마토의 좌표
        Queue<int[]> tomato = new ArrayDeque<>();
        // 익은 토마토의 수
        int ripeTomato = 0;
        // 익지 않은 토마토의 수
        int unRipeTomato = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st2.nextToken());
                arr[i][j] = num;
                // 익은 토마토
                if (num == 1) {
                    tomato.add(new int[]{i, j});
                    ripeTomato += 1;
                } else if (num == 0){
                    unRipeTomato += 1;
                }
            }
        }

        // 메인 로직 시작
        int result = 0;
        while (unRipeTomato > 0 && !tomato.isEmpty()) {
            // 아직 익지 않은 토마토가 있는데 익힘을 전이하지 못하는 경우
            boolean flag = false;
            Queue<int[]> tmp = new ArrayDeque<>();
            // 익은 토마토 좌표 순회
            while (!tomato.isEmpty()) {
                int[] ints = tomato.poll();
                int r = ints[0];
                int c = ints[1];
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                        if (arr[nr][nc] == 0) {
                            flag = true;
                            arr[nr][nc] = 1;
                            tmp.add(new int[]{nr, nc});
                            ripeTomato += 1;
                            unRipeTomato -= 1;
                            if (unRipeTomato == 0) {
                                System.out.println(result + 1);
                                return;
                            }
                        }
                    }
                }
            }
            tomato = tmp;
            result += 1;
        }
        if (unRipeTomato != 0) {
            System.out.println(-1);
        } else System.out.println(result);
    }
}