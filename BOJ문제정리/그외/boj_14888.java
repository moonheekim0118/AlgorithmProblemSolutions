package boj14888;
import java.io.*;
import java.util.*;

/* boj 14888 연산자 끼워넣기 브루트포스 
 * dfs 로 모든 경우의 수를 탐색 
 * 그 중 max와 min을 고른다. 
 * */
public class Main {
	private static long getMax(long n , long m) {
		if(n>m) return n;
		else return m;
	}
	
	private static long getMin(long n, long m) {
		if(n>m) return m;
		else return n;
	}
	private static int[]number;
	private static long max=Integer.MIN_VALUE;
	private static long min=Integer.MAX_VALUE;
	private static void dfs(int plus, int minus, int mult, int sub, int n, int cnt, long sum) {
		if( n == cnt) {
			max = getMax(max,sum);
			min = getMin(min,sum);
		}
		
		if(plus>0) {
			dfs(plus-1,minus, mult, sub, n, cnt+1, sum+number[cnt]);
		}
		if(minus>0) {
			dfs(plus,minus-1, mult, sub, n, cnt+1, sum-number[cnt]);
		}
		if(mult>0) {
			dfs(plus,minus, mult-1, sub, n, cnt+1, sum*number[cnt]);
		}
		if(sub>0) {
			dfs(plus,minus, mult, sub-1, n, cnt+1, sum/number[cnt]);
		}
		
	}
	
	private static int operation[];
	public static void main(String[]args)throws IOException {
		String[]tmp;
		operation = new int[4];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 숫자 개수
		tmp= br.readLine().split(" ");
		number = new int[n];
		for(int i=0; i<n ; i++) {
			number[i] = Integer.parseInt(tmp[i]);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4 ; i++) {
			operation[i] = Integer.parseInt(st.nextToken());
		}
		dfs(operation[0],operation[1],operation[2],operation[3],n,1,number[0]);
		System.out.println(max);
		System.out.println(min);
	}
}
