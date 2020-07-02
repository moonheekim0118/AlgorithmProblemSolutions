package boj1463;
import java.util.*;
import java.io.*;

/*
 * boj 1463 1로 만들기 
 * 다이나믹 프로그래밍
 * 1) 2로 나누어지는 경우 +1 하고, i/2 번째 테이블의 최적해 더하기
 * 2) 3으로 나누어지는 경우 +1 하고 i/2 번재 테이블의 최적해 더하기
 * 3) +1 하고 i-1번째 테이블의 최적해 더하기 
 * */

public class Main {
	private static int min(int n, int m) {
		if(n==0) return m; //0 제외 최속밧 
		if(m==0) return n; //0은 아예 연산이 안된 것 
		if(n>m) return m;
		else return n;
	}
	
	static int[][] dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int x = Integer.parseInt(stk.nextToken());
		dp = new int[x+1][3];
		for(int i=2; i<=x; i++) { //bottom-up 방식 
			if(i%2==0)  //2로 나누어지는 경우 
				dp[i][0]=1+min(dp[i/2][2],min(dp[i/2][0],dp[i/2][1]));
			if(i%3==0)  //3으로 나누어지는 경우 
				dp[i][1]=1+min(dp[i/3][2],min(dp[i/3][0],dp[i/3][1]));
			dp[i][2]=1+min(dp[i-1][2],min(dp[i-1][0],dp[i-1][1]));
			
		}
		int res = min(dp[x][2],min(dp[x][0],dp[x][1]));
		System.out.println(res);
	}
}
