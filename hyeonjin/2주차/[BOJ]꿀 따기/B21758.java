import java.io.*;
import java.util.*;
// 1) 벌 벌 꿀통
// 2) 꿀통 벌 벌
// 3) 벌 꿀통 벌

class B21758{
    static int answer;
    static int arr[];
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        int len = arr.length;
        Solution(len);
        System.out.println(answer);
    }
    public static void Solution(int len) {
        int sum1=0;
        // 벌 벌 꿀통 (점점 추가해나가는 방식)
        for(int i=1;i<len-1;i++){
            sum1 +=arr[i];
        }
        for(int i=len-1;i>=2;i--) {
            answer = Math.max(Math.max(answer, sum1), sum1 - arr[i - 1] + arr[i] * 2);
            sum1 = sum1 - arr[i - 1] + arr[i] * 2;
        }
        // 꿀통 벌 벌
        int sum2=0;
        for(int i=1;i<len-1;i++){
            sum2+=arr[i];
        }
        for(int i=0;i<len-2;i++){
            answer = Math.max(Math.max(answer,sum2), sum2+arr[i]*2-arr[i+1]);
            sum2 = sum2+arr[i]*2-arr[i+1];
        }

        // 벌 꿀통 벌
        int sum3=0;
        for(int i=1;i<len-1;i++){
            sum3+=arr[i];
        }
        sum3+=arr[1];
        for(int i=2;i<len-1;i++){
            answer = Math.max(Math.max(answer,sum3), sum3-arr[i-1]+arr[i]);
            sum3= sum3-arr[i-1]+arr[i];
        }
        answer = Math.max(sum3,answer);
    }
}