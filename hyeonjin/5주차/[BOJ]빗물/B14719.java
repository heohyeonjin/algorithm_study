import java.io.*;
import java.util.*;

// 정답 아닌 코드
class B14719{
    private static int h,w;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        h = Integer.parseInt(str[0]);
        w = Integer.parseInt(str[1]);
        int arr[] = new int[w];
        str = br.readLine().split(" ");
        for(int i=0;i<w;i++){
            arr[i] = Integer.parseInt(str[i]);
        }
        int result = solve(arr);
        System.out.println(result);
    }

    public static int solve(int arr[]){
        int sum=0;
        int bucket_left=-1;
        int prev=-1;
        boolean flag = false;

        ArrayList<Integer> drops = new ArrayList<>();
        for(int i=0;i<w;i++){
            if(arr[i]>=bucket_left){ // 웅덩이 가장 왼쪽 보다 현재 값이 크거나 같으면 (고인 물 계산)
                int max_h = Math.min(bucket_left,arr[i]);
                for(int j=0;j<drops.size();j++){
                    sum+=max_h-drops.get(j);
                }
                drops = new ArrayList<>();
                bucket_left = arr[i];
                flag = false;

            }
            else if(flag==true && arr[i]<prev){ // 이전 값보다 높이가 작아지는 시점+왼쪽 기둥 있을 때  --> 그전까지 웅덩이
                int max_h = Math.min(bucket_left,prev);
                for(int j=0;j<drops.size();j++){
                    sum+=max_h-drops.get(j);
                }
                drops = new ArrayList<>();
                drops.add(arr[i]);
                bucket_left = prev;

                flag = false;
            }
            else{ // 그전 높이들 저장하기
                flag = true;
                drops.add(arr[i]);
            }
            prev = arr[i]; // 직전 값 계속 갱신

        }

        // 제일 마지막 부분 구하기
        int max_h = Math.min(bucket_left,arr[w-1]);
        for(int i=0;i<drops.size();i++){
            sum+=max_h-drops.get(i);
        }

        return sum;
    }
}