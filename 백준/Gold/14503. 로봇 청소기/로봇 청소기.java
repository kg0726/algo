import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 델타 정의
    public static int[] dr = new int[] {-1, 0, 1, 0};
    public static int[] dc = new int[] {0, 1, 0, -1};

    // 배열의 경계 확인 메서드
    public static boolean isBoundary(int nr, int nc, int N, int M) {
        return 0 <= nr && nr < N && 0 <= nc && nc < M;
    }

    // 로봇청소기 클래스 정의
    public static class Robot {
        int r;
        int c;
        int dir;
        int result = 0;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        // 청소 메서드 -> 현재 칸이 청소되지 않은 경우 청소하는 메서드
        void clear(int[][] arr) {
            arr[this.r][this.c] = 2;
            this.result += 1;
        }

        // 방향전환 메서드 -> 현재 칸의 4칸 중 청소되지 않은 빈 칸이 있는 경우
        void changeDir() {
            if (this.dir == 0) {
                this.dir = 3;
            } else {
                this.dir -= 1;
            }
        }

        // 전진 메서드 -> 바라보는 방향을 기준으로 해당 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
        void front() {
            this.r = this.r + dr[this.dir];
            this.c = this.c + dc[this.dir];
        }

        // 후진 메서드 -> 청소되지 않은 빈 칸이 없는 경우 후진
        void back() {
            this.r = this.r + dr[this.dir] * (-1);
            this.c = this.c + dc[this.dir] * (-1);
        }
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        // 청소기 객체 생성
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Robot robot = new Robot(
                Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()),
                Integer.parseInt(st2.nextToken()));

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st3.nextToken());
            }
        }

        // 입력 후 메인 로직 구현부
        // 청소기 객체 생성
        while (true) {
            // 0. 현재 칸 파싱
            int r = robot.r;
            int c = robot.c;
            // 1. 현재 칸이 아직 청소되지 않은 경우
            if (arr[r][c] == 0) {
                robot.clear(arr);
            }
            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인
            boolean goBack = true;
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isBoundary(nr, nc, N ,M)) {
                    if (arr[nr][nc] == 0) {
                        goBack = false;
                        break;
                    }
                }
            }
            if (goBack) {
                robot.back();
                if (robot.r < 0 || robot.r >= N || robot.c < 0 || robot.c >= M || arr[robot.r][robot.c] == 1) {
                    break;
                }
                continue;
            }
            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            robot.changeDir();
            int nr = robot.r + dr[robot.dir];
            int nc = robot.c + dc[robot.dir];
            if (isBoundary(nr, nc, N ,M)) {
                // 해당 칸이 청소되지 않은 칸이면 해당 칸으로 이동
                if (arr[nr][nc] == 0) {
                    robot.r = nr;
                    robot.c = nc;
                }
            }

        }
        System.out.println(robot.result);

    }
}
