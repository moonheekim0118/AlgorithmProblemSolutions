package boj11053;
import java.util.*;
import java.io.*;

/*
 * boj 11053 가장 긴 증가하는 부분 수열 
 * 다이나믹 프로그래밍 메모이제이션 
 * */

public class Main {
	private static int [] arr;
	private static int[]dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //수열 길이 
		arr = new int[n];
		dp = new int[n];
		String[] s;
		s = br.readLine().split(" ");
		for(int i=0; i< n;i++) {
			arr[i]=Integer.parseInt(s[i]);
		}
		
		for(int i = 0 ; i < n ; i++) 
		{
			dp[i]++; //현재 수열에 포함되므로 일단 1 플러스 
			int max=0;
			for(int j = i-1 ; j>=0; j--) { // 이전 수들 살펴보기 
				if(arr[j]<arr[i]) { //만약 arr[i] 보다 작은 수가 있다면 ? 증가하는 부분수열이다. 
					if(dp[j]>max) max= dp[j]; //arr[i]보다 작은 수 중에서 부분 수열의 길이가 가장 긴 것을 저장한다. 
				}
			}
			dp[i]+=max; //arr[i]보다 작은 수 중에서 부분 수열의 길이가 가장 긴 것을 현재 부분 수열 길이에 더한다 .
		}
		Arrays.sort(dp);
		System.out.println(dp[n-1]);
		
	}
}
