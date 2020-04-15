package boj2156;
import java.util.*;
import java.io.*;


/*
 * BOJ 2156 포도주 시식
 * dp table 사용 
 * 
 * 점화식:
 * dp[i][0] = max(dp[i-1][0],dp[i-1][1],dp[i-1][2]) //이번에 와인 안마심 
 * dp[i][1] = dp[i][0] + wine[i] //직전에 와인 안마심 + 이번에 와인 마심 
 * dp[i][2] = dp[i][1] + wine[i] //직전에 와인 마심 + 이번에 와인 마심 
 * 
 * 
 * 
 * */
public class Main {
	private static int max(int n, int m) {
		if(n>m) return n;
		else return m;
	}
	
	private static int []wine = new int [10001];
	private static int [][] dp = new int [10001][3];
	public static void main(String[] args)throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		wine = new int[n+1];
		dp = new int[n+1][3];
		for(int i=1; i<n+1;i++) {
			stk = new StringTokenizer(br.readLine());
			int input= Integer.parseInt(stk.nextToken());
			wine[i]= input;
		}
		
		dp[1][0]=0;
		dp[1][1]=wine[1];
		dp[1][2]=wine[1];
		if(n>=2) { //n == 1 일때는 이걸 수행하면 런타임 에러가 난다. 
			dp[2][0]=wine[1];
			dp[2][1]=wine[2];
			dp[2][2]=dp[1][1]+wine[2];
		}
		
		// 안마심
		// 1번 연속으로 마심 (이전에 안마셨다가 지금 마심)
		// 2번 연속으로 마심 
		
		for(int i=3; i<=n ; i++) {
			dp[i][0]=max(dp[i-1][0],max(dp[i-1][1], dp[i-1][2])); //안마신다 
			dp[i][1]=dp[i-1][0]+wine[i]; // 1번 연속으로 마심 (직전에 안마심) 
			dp[i][2]=dp[i-1][1]+wine[i]; // 2번 연속으로 마심 (직전에 마심) 

		}
		int ans = max(dp[n][0],max(dp[n][1],dp[n][2]));
		System.out.println(ans);
		
	}
}
