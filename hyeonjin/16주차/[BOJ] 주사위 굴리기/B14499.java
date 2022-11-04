import java.util.*;
import java.io.*;
class B14499{
    static int N,M;
    static int map[][];
    static int dice[][];
    static int dice_row,dice_col; // 주사위 위치
    static int bottom_row=1, bottom_col=1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map= new int[N][M];

        // 현재 주사위 좌표
        dice_row = Integer.parseInt(input[2]);
        dice_col = Integer.parseInt(input[3]);

        // 명령어 개수
        int K = Integer.parseInt(input[4]);

        //지도 초기화
        for(int i=0;i<N;i++){
            input = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        // 주사위 전개도 초기화
        dice = new int[4][3];
        // 주사위 바닥 위치 초기화
        input = br.readLine().split(" ");
        roll(input,K);
    }
    public static void roll(String input[],int K){
        for(int i=0;i<K;i++){
            int dir = Integer.parseInt(input[i]);
            // 지도 위 주사위 위치 이동
            if(dir==1) dice_col++;
            else if(dir==2) dice_col--;
            else if(dir==3) dice_row--;
            else dice_row++;
            if(!out_check()) continue;

            // 주사위 전개도 재배치
            if(dir==1){ //동쪽
                int tmp = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = tmp;
            }
            else if(dir==2){ //서쪽
                int tmp = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = tmp;
            }
            else if(dir==3){ //북쪽
               int tmp = dice[3][1];
               dice[3][1] = dice[2][1];
               dice[2][1] = dice[1][1];
               dice[1][1] = dice[0][1];
               dice[0][1] = tmp;
            }
            else{ //남쪽
                int tmp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = tmp;
            }

            if(map[dice_row][dice_col]==0){ // 지도가 0이면
                map[dice_row][dice_col] = dice[1][1];
            }
            else{
                dice[1][1] = map[dice_row][dice_col];
                map[dice_row][dice_col] = 0;
            }
            print_top_dice(bottom_row,bottom_col);
        }
    }
    public static boolean out_check(){
        if(dice_col<0||dice_row<0||dice_col>M-1||dice_row>N-1){
            if(dice_col<0) dice_col=0;
            if(dice_row<0) dice_row=0;
            if(dice_col>M-1) dice_col = M-1;
            if(dice_row>N-1) dice_row = N-1;
            return false;
        }
        else return true;
    }
    public static void print_top_dice(int bottom_row,int bottom_col){
        if(bottom_col==1){ //주사위 전개도 세로
            if(bottom_row<=1){
                System.out.println(dice[bottom_row+2][bottom_col]);
            }
            else{
                System.out.println(dice[bottom_row-2][bottom_col]);
            }
        }
        else{ // 주사위 전개도 가로
            if(bottom_col==0)
                System.out.println(dice[bottom_row][bottom_col+2]);
            else
                System.out.println(dice[bottom_row][bottom_col-2]);
        }
    }
}