import java.io.*;
import java.util.*;
class B_14719{
    // 각 인덱스를 기준으로 빗물 높이 구하기기
    static int h,w;
   public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       Integer arr[];
       String str[] = br.readLine().split(" ");
       h = Integer.parseInt(str[0]);
       w = Integer.parseInt(str[1]);
       arr = new Integer[w];

       str = br.readLine().split(" ");
       for(int i=0;i<w;i++){
           arr[i] = Integer.parseInt(str[i]);
       }

       int result = solve(arr);
       System.out.println(result);
    }
    public static int solve(Integer arr[]){
       int sum=0;
       for(int i=1;i<w-1;i++){
//           Integer left[] = Arrays.copyOfRange(arr,0,i);
//           Arrays.sort(left,Collections.reverseOrder()); // i기준 왼쪽에서 제일 높은 것
           int left = Integer.MIN_VALUE;
           for(int j=0;j<i;j++){
               if(left<arr[j]){
                   left = arr[j];
               }
           }
//           Integer right[] = Arrays.copyOfRange(arr,i+1,w);
//           Arrays.sort(right,Collections.reverseOrder()); // i기준 오른쪽에서 제일 높은 것
           int right = Integer.MIN_VALUE;
           for(int j=i+1;j<w;j++){
               if(right<arr[j]){
                   right = arr[j];
               }
           }
           int max_h = Math.min(left,right);
           if(max_h>arr[i]){
               sum+=max_h-arr[i];
           }
       }
       return sum;
    }
}