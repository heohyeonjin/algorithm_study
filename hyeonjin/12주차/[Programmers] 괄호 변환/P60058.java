import java.io.*;
import java.util.*;
class P60058 {
    static String answer = "";
    public String solution(String p) {
        return solve(p);
    }
    public static String solve(String p){

        if(p.length()==0) return ""; //1번

        //2번
        String u="",v="";
        int left=0,right=0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)==')') right++;
            else left++;
            if(right==left){
                u = p.substring(0,i+1);
                v = p.substring(i+1);
                break;
            }
        }

        left=0;
        boolean flag = true;
        for(int i=0;i<u.length();i++){
            if(p.charAt(i)==')'){
                left--;
                if(left<0) flag = false;
            }
            else left++;
        }

        //3번
        if(flag){ // u가 올바른 괄호 문자열이라면
            if(v=="") return u;
            return u+solve(v);
        }
        else{ // u가 올바른 괄호 문자가 아니라면
            String tmp="";
            tmp+="(";
            tmp+=solve(v);
            tmp+=')';
            u = u.substring(1);
            u = u.substring(0,u.length()-1);
            for(int i=0;i<u.length();i++){
                if(u.charAt(i)=='(') tmp+=")";
                else tmp+="(";
            }
            return tmp;
        }

    }

}