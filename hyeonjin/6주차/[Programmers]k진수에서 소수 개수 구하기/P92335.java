import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = -1;
        String change = Integer.toString(n,k);
        answer = solve(change);
        return answer;
    }
    public static int solve(String str){
        int sum = 0;
        int zero_idx = -1;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='0'){ // 0이면 멈춤 조건
                if(isPrime(str.substring(zero_idx+1,i))){ // 0기준 왼쪽 숫자 소수 판별
                    System.out.println(i);
                    sum++;
                }
                zero_idx = i;
            }
        }

        if(zero_idx!=str.length()-1){ // zero_idx가 마지막이 아닌 경우 소수 판별
            if(isPrime(str.substring(zero_idx+1,str.length()))){
                sum++;
            }
        }
        return sum;
    }
    public static boolean isPrime(String str){
        if(str.length()==0) // 0이 연속될 경우
            return false;

        else {
            Long x = Long.parseLong(str);
            if(x==1)
                return false;
            for(int i=2;i<=Math.sqrt(x);i++){
                if(x%i==0)
                    return false;
            }
        }
        return true;
    }
}