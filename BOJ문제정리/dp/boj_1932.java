package boj1932;
import java.util.*;
import java.io.*;

/*
 * boj 1932 정수삼각형 
 * dp 
 * dp 테이블 필요 X 
 * */

public class Main {
	private static int max(int n, int m) {
		if(n>m) return n;
		else return m;
	}
	
	static int[][] tri;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //삼각형 크기 
		tri = new int[n][n];
		String[] s;
		s = new String[n];
		for(int i=0; i< n ;i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j <= i ; j++){
				tri[i][j]=Integer.parseInt(s[j]);
			}
		}	
		if(n>1) { //런타임 에러 방지 
			tri[1][0]+=tri[0][0];
			tri[1][1]+=tri[0][0];
		}
		
		for(int i= 2 ; i < n ; i++) 
		{
			for(int j=0; j<=i ; j++) { 
				if(j==0) { //맨 앞값이면 바로 위에값 가져온다 . 
					tri[i][j]+=tri[i-1][j];
				}
				else if(j==i) { //맨 뒷값이면 위에값 바로 가져온다. 
					tri[i][j]+=tri[i-1][j-1];
				}
				else { //가운데 값이면 대각선 오른쪽, 대각선 왼쪽 중에 골라서 가져온다. 
					tri[i][j]+=max(tri[i-1][j-1],tri[i-1][j]);
				}
			}
		}
		
		Arrays.sort(tri[n-1]); //마지막 결과들 소트해준다.최대값을 찾기위해서 
		System.out.println(tri[n-1][n-1]); //최대값(마지막에 놓인 값 ) 출력 
	}

}
