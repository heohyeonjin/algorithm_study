import java.io.*;
import java.util.*;
class P60057{
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        if(s.length()==1) return 1;
        for(int i=0;i<s.length()/2;i++){ // 절반 길이만큼 경우의 단위 수
            int unit = i+1;
            int result = solve(unit,s); // 자르는 단위, 문자열
            if(answer>result){
                answer = result;
            }
        }

        return answer;
    }
    public static int solve(int unit, String s){
        String compact="";

        while(s.length()>=unit){
            String str = s.substring(0, unit);
            s = s.substring(unit);
            int cnt =0;

            while(true){
                if(s.length()<unit){
                    compact+=s;
                    break;
                }
                if(!s.substring(0,unit).equals(str)) break;
                s = s.substring(unit);
                cnt++;
            }
            if(cnt==0) compact+=str;
            else compact+=(Integer.toString(cnt+1)+str);
        }
        return compact.length();
    }

}