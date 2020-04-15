package boj2579;
import java.util.*;
import java.io.*;

public class Main {
	/*
	 * boj 2579 계단오르기 
	 * 다이나믹 프로그래밍
	 * 세가지 경우
	 * 1) 현재 계단 오름  + 직전 계단 오름
	 * 2) 현재 계단 오르지 않음
	 * 3) 현재 계단 오름 + 직전 계단 오르지 않음
	 * */
	
	private static int max(int n,int m) {
		if(n>m) return n;
		else return m;
	}
	private static int[] step;
	private static int[][] dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); // 계단 수 
		
		step = new int[n+1];
		dp = new int[n+1][3];
		for(int i =1; i<n+1; i++) {
			stk = new StringTokenizer(br.readLine());
			int input= Integer.parseInt(stk.nextToken());
			step[i]=input; //저장 
		}
		for(int i= 1;i < n ; i++) {
			dp[i][0]=step[i]+dp[i-1][2];
			dp[i][1]=max(dp[i-1][0],dp[i-1][2]);
			dp[i][2]= step[i]+dp[i-1][1];
		}
		int res = max(dp[n-1][1],dp[n-1][2]);
		res+=step[n];
		System.out.println(res);
	}
}