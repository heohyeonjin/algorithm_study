import java.util.*;
import java.io.*;
class Top{
    public int num;
    public int height;

    public Top(int num, int height){
        this.num = num;
        this.height = height;
    }
}
class B2493{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int top[] = new int[N+1];
        String input[] = br.readLine().split(" ");
        for(int i=1;i<=N;i++){
            top[i] = Integer.parseInt(input[i-1]);
        }
        int answer[] = solve(N,top);
        for(int i=0;i<N;i++){
            System.out.print(answer[i]+" ");
        }
    }
    public static int[] solve(int N, int[] top){
        int answer[] = new int[N];
        answer[0] = 0;
        Stack<Top> stack = new Stack<>();
        stack.push(new Top(1,top[1])); // 탑 번호, 높이
        for(int i=2;i<=N;i++){
//            for(Top x : stack){
//                System.out.println(i+"   "+x.num+","+x.height);
//            }
//            System.out.println();
            int cur_height = top[i];
            if(cur_height>stack.peek().height){ // 바로 왼쪽 탑 수신 불가
                while(!stack.isEmpty()){
                    if(stack.peek().height>cur_height) break;
                    stack.pop();
                }
            }
            if(stack.isEmpty())  answer[i-1] = 0;
            else  answer[i-1] = stack.peek().num;
            stack.push(new Top(i,cur_height));
        }
        return answer;
    }
}