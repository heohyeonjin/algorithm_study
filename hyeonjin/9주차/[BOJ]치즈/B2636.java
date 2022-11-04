import java.io.*;
import java.util.*;
class B2636{
    static int row, col;
    static int map[][];
    static int copy_map[][];
    static boolean visited[][];
    static int dir_row[] = {-1,0,1,0};
    static int dir_col[] = {0,1,0,-1};
    static int phase = 0;
    static ArrayList<Integer> cheezes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        row = Integer.parseInt(str.nextToken());
        col = Integer.parseInt(str.nextToken());
        map = new int[row][col];
        copy_map = new int[row][col];
        visited = new boolean[row][col];
        for(int i=0;i<row;i++){
            str = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
             map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        BFS();
        System.out.println(phase-1);
        System.out.println(cheezes.get(phase-2));
    }

    public static void BFS(){
        Queue<int []> queue = new LinkedList<>();
        int cheeze = 1;

        while(cheeze>0){
            cheeze = 0;
            queue.offer(new int[]{0,0});
            visited[0][0] = true;

            while(!queue.isEmpty()){
                int tmp[] = queue.poll();
                for(int i=0;i<4;i++){
                    int c_row = tmp[0]+dir_row[i];
                    int c_col = tmp[1]+dir_col[i];
                    if(c_row>=0&&c_col>=0&&c_row<row&&c_col<col){
                        if(!visited[c_row][c_col]){
                            if(map[c_row][c_col]<=0){ // 빈공간
                                queue.offer(new int[]{c_row,c_col});
                            }
                            else if(map[c_row][c_col]==1){ // 치즈이면
                                cheeze++;
                                map[c_row][c_col] = phase*-1-1;
                            }
                            visited[c_row][c_col] = true;
                        }
                    }
                }
            }

            phase++;
            cheezes.add(cheeze);
            visited = new boolean[row][col];
        }
    }
}