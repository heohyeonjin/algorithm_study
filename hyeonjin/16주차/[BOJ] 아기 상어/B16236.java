import java.util.*;
import java.io.*;
class Shark implements Comparable<Shark>{
    public int row;
    public int col;
    public int move;

    public Shark(int row, int col, int move){
        this.row = row;
        this.col = col;
        this.move = move;
    }

    public void setMove(int move){
        this.move = move;
    }
    @Override
    public int compareTo(Shark o){
        if(this.move==o.move){
            if(this.row==o.row)
                return this.col-o.col;
            else return this.row-o.row;
        }
        return this.move-o.move;
    }
}
class B16236{
    static int N;
    static int space[][];
    static int dir_row[] = {-1,0,1,0};
    static int dir_col[] = {0,1,0,-1};
    static int shark_row, shark_col;
    static int eat_cnt;
    static int shark_size = 2;
    static int answer = 0;
    static boolean visited[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        for(int i=0;i<N;i++){
            String str[] = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                space[i][j] = Integer.parseInt(str[j]);
                if(space[i][j]==9) {
                    shark_row = i;
                    shark_col = j;
                    space[shark_row][shark_col] = 0;
                }
            }
        }
        while(true){
            visited = new boolean[N][N];
            Shark fish = bfs(shark_row,shark_col); // 물고기 먹음
            if(fish==null) break;
            space[fish.row][fish.col] = 0;
            shark_row = fish.row;
            shark_col = fish.col;
            answer+=fish.move;
            eat_cnt ++;
            if(eat_cnt==shark_size) {
                shark_size++;
                eat_cnt = 0;
            }
        }
        System.out.println(answer);
    }
    public static Shark bfs(int shark_row, int shark_col){
        PriorityQueue<Shark> queue = new PriorityQueue<>();
        queue.offer(new Shark(shark_row,shark_col,0));
        PriorityQueue<Shark> fish = new PriorityQueue<>();

        while(!queue.isEmpty()){
          Shark shark = queue.poll();

          if(space[shark.row][shark.col]<shark_size && space[shark.row][shark.col]!=0) fish.offer(shark); // 먹을 수 있는 물고기

          for(int i=0;i<4;i++){
              int n_row = dir_row[i] + shark.row;
              int n_col = dir_col[i] + shark.col;

              if(n_row<0 || n_col<0 || n_row>N-1 || n_col>N-1) continue;
              if(visited[n_row][n_col]) continue;
              visited[n_row][n_col] = true;

              if(space[n_row][n_col]<=shark_size){
                  queue.offer(new Shark(n_row,n_col,shark.move+1));
              }
          }
       }
        if(fish.size()==0) return null;
        else return fish.poll();
    }
}