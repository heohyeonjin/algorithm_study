import java.util.*;
import java.io.*;
class P72410 {
    public String solution(String new_id) {
        String answer = "";


        new_id = new_id.toLowerCase(); //1단계 : 모든 대문자 --> 소문자
        new_id = new_id.replaceAll("[^a-z0-9-_.]",""); //2단계 : 특수문자 정리
        new_id = new_id.replaceAll("[.]{2,}","."); //3단계 : 연속되는 . 제거

        //4단계 : . 처음,끝 제거
        if(new_id.charAt(0)=='.')
            new_id =  new_id.substring(1);
        new_id = new_id.replaceAll("[.]$",""); //.으로 끝나는 문자열


        //5단계 : new_id가 빈 문자열이라면 "a" 대입
        if(new_id.length()==0){
            new_id +="a";
        }

        //6단계
        if(new_id.length()>=16){
            new_id = new_id.substring(0,15);
        }
        new_id = new_id.replaceAll("[.]$",""); // .으로 끝나면

        //7단계
        if(new_id.length()<=2){
            while(new_id.length()!=3){
                new_id+=new_id.charAt(new_id.length()-1);
            }

        }
        answer = new_id;
        return answer;
    }
}