import java.util.*;
import java.io.*;
class B2812{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
         int n = Integer.parseInt(input[0]);
         int k = Integer.parseInt(input[1]);
        String number = br.readLine();
        System.out.println(solve(number,k));
    }

    public static String solve(String num, int k){
        String answer="";
        Stack<Character> stack = new Stack<>();
        boolean flag = false;
        int j = 0;
        for(int i=0;i<num.length();i++){
            while(true){
                if(stack.isEmpty() || stack.peek()>=num.charAt(i)||k==0){
                    stack.push(num.charAt(i));
                    j=i; break;
                }
                k--;
                stack.pop();
            }
        }
        StringBuffer sb = new StringBuffer();

        while(true){ //k를 다 소모하지 않은 경우 k만큼 빼주기
            if(k==0) break;
            stack.pop();
            k--;
        }
        // 스택에 있는 값들 문자열로 변환
        while(!stack.isEmpty())
            sb.append(stack.pop());
        answer = sb.reverse().toString();

        // 나머지 합치기
        if(j>0) answer+=num.substring(j+1);

        return answer;
    }
}
