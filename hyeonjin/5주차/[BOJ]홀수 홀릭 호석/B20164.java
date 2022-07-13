import java.io.*;
import java.util.*;
class B20164{
    static int max_result = Integer.MIN_VALUE;
    static int min_result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        solve(N,0);
        System.out.println(min_result+" "+max_result);
    }

    public static void solve(String s, int cnt){
        int odd_num = count_odd(s);
        if(s.length()==1){ // 최종적으로 나온 cnt 값 비교
            cnt+=odd_num;
            max_result = Math.max(cnt,max_result);
            min_result = Math.min(cnt,min_result);
            return;
        }

        else if(s.length()==2){
            cnt+=odd_num;
            int n = (s.charAt(0)-'0')+(s.charAt(1)-'0');
            solve(Integer.toString(n),cnt);
        }
        else{ // 문자열의 길이가 3이상인 경우
            cnt+=odd_num;

            int divide_spot = s.length()-1; // 나누어질 부분의 개수
            for(int i=1;i<divide_spot;i++){ // i,j : 나누어지는 부분
                for(int j=i+1;j<=divide_spot;j++){
                    String one = s.substring(0,i);
                    String two = s.substring(i,j);
                    String three = s.substring(j);
                    int n = Integer.parseInt(one)+Integer.parseInt(two)+Integer.parseInt(three);
                    solve(Integer.toString(n),cnt);
                }
            }
        }

    }

    public static int count_odd(String s){
        int odd=0;
        int s_num = Integer.parseInt(s);

        while(s_num>=1){
            int one_digit = s_num%10;
            if(one_digit%2==1)
                odd++;
            s_num/=10;
        }
        return odd;
    }


}