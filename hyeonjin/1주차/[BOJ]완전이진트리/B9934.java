// 완전 이진 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class B9934 {
    static int K;
    static ArrayList<ArrayList<Integer>> nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[str.length];
        for(int i=0;i<str.length;i++){
            arr[i] = Integer.parseInt(str[i]);
        }

       nodes = new ArrayList<>(); // 이중 ArrayList 초기화
        for(int i=0;i<K;i++){
            nodes.add(new ArrayList<Integer>());
        }
        Solution(arr,0,0,arr.length-1);
        Print(nodes); // 출력
    }

    public static void Solution(int arr[], int L, int start,int end){
       if(start>end){
           return;
       }
       else{
           int mid = (start+end)/2;
           nodes.get(L).add(arr[mid]); // 해당 깊이에 노드 값 넣기
           Solution(arr,L+1,start,mid-1);
           Solution(arr,L+1,mid+1,end);

       }

    }
    public static void Print(ArrayList<ArrayList<Integer>> nodes){

        for(ArrayList<Integer> array: nodes){
            for(int x:array){
                System.out.print(x+" ");
            }
            System.out.println();

        }
    }

    }


