package boj2156;
import java.util.*;
import java.io.*;


/*
 * BOJ 2156 ������ �ý�
 * dp table ��� 
 * 
 * ��ȭ��:
 * dp[i][0] = max(dp[i-1][0],dp[i-1][1],dp[i-1][2]) //�̹��� ���� �ȸ��� 
 * dp[i][1] = dp[i][0] + wine[i] //������ ���� �ȸ��� + �̹��� ���� ���� 
 * dp[i][2] = dp[i][1] + wine[i] //������ ���� ���� + �̹��� ���� ���� 
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
		if(n>=2) { //n == 1 �϶��� �̰� �����ϸ� ��Ÿ�� ������ ����. 
			dp[2][0]=wine[1];
			dp[2][1]=wine[2];
			dp[2][2]=dp[1][1]+wine[2];
		}
		
		// �ȸ���
		// 1�� �������� ���� (������ �ȸ��̴ٰ� ���� ����)
		// 2�� �������� ���� 
		
		for(int i=3; i<=n ; i++) {
			dp[i][0]=max(dp[i-1][0],max(dp[i-1][1], dp[i-1][2])); //�ȸ��Ŵ� 
			dp[i][1]=dp[i-1][0]+wine[i]; // 1�� �������� ���� (������ �ȸ���) 
			dp[i][2]=dp[i-1][1]+wine[i]; // 2�� �������� ���� (������ ����) 

		}
		int ans = max(dp[n][0],max(dp[n][1],dp[n][2]));
		System.out.println(ans);
		
	}
}
