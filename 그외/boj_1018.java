package boj1018;
import java.util.*;
import java.io.*;
/*
 * boj 1018 체스판 다시 칠하기 
 * 브루트 포스 문제
 * */

public class Main {
	static String [][] boardB={
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"}};

    static String [][] boardW={
    		{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"},
			{"W","B","W","B","W","B","W","B"},
			{"B","W","B","W","B","W","B","W"}};
	private static String[][]chess;
	private static int min(int n, int m) {
		if(n<m) return n;
		else return m;
	}
	
	private static int whtieFirstChange(int x, int y) {
		int cnt = 0;
		for(int i=x; i< x+8; i++) {
			for(int j= y; j<y+8; j++) {
				if(!chess[i][j].equals(boardW[i-x][j-y]))
					cnt++;
			}
		}
		return cnt;
	}
	
	private static int blackFirstChange(int x, int y) {
		int cnt=0;
		for(int i = x; i < x+8; i++) {
			for(int j= y; j< y+8; j++) {
				if(!chess[i][j].equals(boardB[i-x][j-y])) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[]s;
		chess =new String[n][m];
		for(int i=0; i<n; i++) {
			s=br.readLine().split("");
			for(int j=0; j<m; j++) {
				chess[i][j]=s[j];
			}
		}
		
		int result=Integer.MAX_VALUE;
		
		for(int i=0; i+7<n ; i++) {
			for(int j=0; j+7<m;j ++)
				result=min(result,min(whtieFirstChange(i,j), blackFirstChange(i,j)));
		}
		
		System.out.println(result);
	}
}
