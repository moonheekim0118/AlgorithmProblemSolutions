package boj1463;
import java.util.*;
import java.io.*;

/*
 * boj 1463 1�� ����� 
 * ���̳��� ���α׷���
 * 1) 2�� ���������� ��� +1 �ϰ�, i/2 ��° ���̺��� ������ ���ϱ�
 * 2) 3���� ���������� ��� +1 �ϰ� i/2 ���� ���̺��� ������ ���ϱ�
 * 3) +1 �ϰ� i-1��° ���̺��� ������ ���ϱ� 
 * */

public class Main {
	private static int min(int n, int m) {
		if(n==0) return m; //0 ���� �ּӹ� 
		if(m==0) return n; //0�� �ƿ� ������ �ȵ� �� 
		if(n>m) return m;
		else return n;
	}
	
	static int[][] dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int x = Integer.parseInt(stk.nextToken());
		dp = new int[x+1][3];
		for(int i=2; i<=x; i++) { //bottom-up ��� 
			if(i%2==0)  //2�� ���������� ��� 
				dp[i][0]=1+min(dp[i/2][2],min(dp[i/2][0],dp[i/2][1]));
			if(i%3==0)  //3���� ���������� ��� 
				dp[i][1]=1+min(dp[i/3][2],min(dp[i/3][0],dp[i/3][1]));
			dp[i][2]=1+min(dp[i-1][2],min(dp[i-1][0],dp[i-1][1]));
			
		}
		int res = min(dp[x][2],min(dp[x][0],dp[x][1]));
		System.out.println(res);
	}
}
