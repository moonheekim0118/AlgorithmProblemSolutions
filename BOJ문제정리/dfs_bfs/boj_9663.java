package boj9663;
import java.io.*;
import java.util.*;

/*
 * boj 9663 N-Queens
 * backtracking 
 * 한 행당 무조건 1개의 퀸은 놓여져야함
 * 해당 행이 유망한 경우에만 재귀
 * 만약 유망하지 않다면 백트래킹해서 다시 선택
 * */

public class Main {
	private static int n;
	private static int count=0;
	private static int[] cols;
	public static void main(String[]args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		cols = new int [n+1];
		for(int i=1; i<=n; i++) {
			cols[1]=i;
			backtracking(1);
		}
		System.out.println(count);
	}

	
	private static boolean feasible(int n) 
	{
		for(int i=1; i < n ; i++) {
			if(cols[i]==cols[n]) return false; //같은 열
			if(Math.abs(cols[i]-cols[n])==n-i) return false; //대각선 
		}
		return true;
		
	}
	private static void backtracking(int level) 
	{
		if(level==n) { 
			count++;
		}
		else {
		for(int i = 1; i<=n; i++) {
			cols[level+1]=i;
			if(feasible(level+1)) backtracking(level+1);
			else cols[level+1]=0;
		}
		}
		
	}
}
