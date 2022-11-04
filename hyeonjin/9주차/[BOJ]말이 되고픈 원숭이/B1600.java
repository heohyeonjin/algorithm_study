import java.io.*;
import java.util.*;
class Horse {
    public int x;
    public int y;
    public int K;
    public int cnt;

    public Horse(int x,int y, int K,int cnt){
        this.x = x;
        this.y = y;
        this.K = K;
        this.cnt = cnt;
    }
}
class B1600 {
    static int k, w, h;
    static int board[][];
    static boolean visited[][][];
    static int answer = Integer.MAX_VALUE;
    static int knight_x[] = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int knight_y[] = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int adj_x[] = {0, 1, 0, -1};
    static int adj_y[] = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        k = Integer.parseInt(str.nextToken());
        str = new StringTokenizer(br.readLine());
        w = Integer.parseInt(str.nextToken()); //열
        h = Integer.parseInt(str.nextToken()); //행

        board = new int[h][w];
        visited = new boolean[h][w][k+1];

        for (int i = 0; i < h; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        BFS();

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static void BFS() {
        Queue<Horse> queue = new LinkedList<>();
        queue.offer(new Horse(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Horse horse = queue.poll();
            if (horse.x == w - 1 && horse.y == h - 1) {
                if (horse.cnt < answer) {
                    answer = horse.cnt;
                }
            }

            // 원숭이
            for(int i=0;i<4;i++){
                int c_x = horse.x+adj_x[i];
                int c_y = horse.y+adj_y[i];
                if (c_x >= 0 && c_y >= 0 && c_x < w && c_y < h && board[c_y][c_x] == 0){
                    if(!visited[c_y][c_x][horse.K]){
                        queue.offer(new Horse(c_x,c_y,horse.K,horse.cnt+1));
                        visited[c_y][c_x][horse.K] = true;
                    }
                }
            }
            if(horse.K == k)
                continue;

            // 말
            for(int i=0;i<8;i++){
                int c_x = horse.x+knight_x[i];
                int c_y = horse.y+knight_y[i];
                if (c_x >= 0 && c_y >= 0 && c_x < w && c_y < h && board[c_y][c_x] == 0){
                    if(!visited[c_y][c_x][horse.K+1]){
                        queue.offer(new Horse(c_x,c_y,horse.K+1,horse.cnt+1));
                        visited[c_y][c_x][horse.K+1] = true;
                    }
                }
            }

        }
    }
}
