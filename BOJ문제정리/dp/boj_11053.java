package boj11053;
import java.util.*;
import java.io.*;

/*
 * boj 11053 ���� �� �����ϴ� �κ� ���� 
 * ���̳��� ���α׷��� �޸������̼� 
 * */

public class Main {
	private static int [] arr;
	private static int[]dp;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //���� ���� 
		arr = new int[n];
		dp = new int[n];
		String[] s;
		s = br.readLine().split(" ");
		for(int i=0; i< n;i++) {
			arr[i]=Integer.parseInt(s[i]);
		}
		
		for(int i = 0 ; i < n ; i++) 
		{
			dp[i]++; //���� ������ ���ԵǹǷ� �ϴ� 1 �÷��� 
			int max=0;
			for(int j = i-1 ; j>=0; j--) { // ���� ���� ���캸�� 
				if(arr[j]<arr[i]) { //���� arr[i] ���� ���� ���� �ִٸ� ? �����ϴ� �κм����̴�. 
					if(dp[j]>max) max= dp[j]; //arr[i]���� ���� �� �߿��� �κ� ������ ���̰� ���� �� ���� �����Ѵ�. 
				}
			}
			dp[i]+=max; //arr[i]���� ���� �� �߿��� �κ� ������ ���̰� ���� �� ���� ���� �κ� ���� ���̿� ���Ѵ� .
		}
		Arrays.sort(dp);
		System.out.println(dp[n-1]);
		
	}
}
