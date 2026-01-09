import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Virus {
        int r;
        int c;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static class Blank {
        int r;
        int c;

        public Blank(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    // 조합 배열
    static int[] combinationArr;
    static int blankNum;

    static List<Blank> blankList;
    static List<Virus> virusList;
    static int[][] arr;
    static int N;
    static int M;

    // 델타 정의(상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int result = 0;

    static void combination(int start, int depth) {
        if (depth == 3) {
            preStart();
            return;
        }

        for (int i = start; i < blankNum; i++) {
            combinationArr[depth] = i;
            combination(i + 1, depth + 1);
        }
    }

    static void preStart() {
        // 먼저 원본 배열을 복사함
        int[][] copiedArr = new int[N][];
        for (int i = 0; i < N; i++) {
            copiedArr[i] = new int[arr[i].length];
            System.arraycopy(arr[i], 0, copiedArr[i], 0, arr[i].length);
        }

        // 현재 조합으로 뽑힌 배열을 순회하며 그 칸을 1로 만듦
        for (int i : combinationArr) {
            Blank blank = blankList.get(i);
            // 벽 세우기
            arr[blank.r][blank.c] = 1;
        }
        // 벽이 세워진 배열을 순회하며 바이러스(2)를 만나면 bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 2) {
                    int[] start = {i, j};
                    bfs(start);
                }
            }
        }
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    tmp += 1;
                }
            }
        }
        result = Math.max(tmp, result);

        // bfs가 끝나면 배열을 다시 원본으로 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = copiedArr[i][j];
            }
        }
    }

    public static void bfs(int[] start) {
        // 방문 배열 생성
        boolean[][] visited = new boolean[N][M];
        // 빈 큐 생성
        Queue<int[]> q = new ArrayDeque<>();
        // 인자로 받은 첫번째 좌표를 큐에 넣음
        q.add(start);

        // 큐가 비어있지 않은 동안 실행
        while (! q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (arr[nr][nc] == 0 && ! visited[nr][nc]) {
                        visited[nr][nc] = true;
                        arr[nr][nc] = 2;
                        int[] tmp = {nr, nc};
                        q.add(tmp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        blankList = new ArrayList<>();
        virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
                if (arr[i][j] == 0) {
                    blankList.add(new Blank(i, j));
                } else if (arr[i][j] == 2) {
                    virusList.add(new Virus(i, j));
                }
            }
        }
        // 입력 끝
        blankNum = blankList.size();
        combinationArr = new int[3];

        combination(0, 0);
        System.out.println(result);
    }
}