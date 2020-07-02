package boj1932;
import java.util.*;
import java.io.*;

/*
 * boj 1932 �����ﰢ�� 
 * dp 
 * dp ���̺� �ʿ� X 
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
		int n = Integer.parseInt(stk.nextToken()); //�ﰢ�� ũ�� 
		tri = new int[n][n];
		String[] s;
		s = new String[n];
		for(int i=0; i< n ;i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j <= i ; j++){
				tri[i][j]=Integer.parseInt(s[j]);
			}
		}	
		if(n>1) { //��Ÿ�� ���� ���� 
			tri[1][0]+=tri[0][0];
			tri[1][1]+=tri[0][0];
		}
		
		for(int i= 2 ; i < n ; i++) 
		{
			for(int j=0; j<=i ; j++) { 
				if(j==0) { //�� �հ��̸� �ٷ� ������ �����´� . 
					tri[i][j]+=tri[i-1][j];
				}
				else if(j==i) { //�� �ް��̸� ������ �ٷ� �����´�. 
					tri[i][j]+=tri[i-1][j-1];
				}
				else { //��� ���̸� �밢�� ������, �밢�� ���� �߿� ��� �����´�. 
					tri[i][j]+=max(tri[i-1][j-1],tri[i-1][j]);
				}
			}
		}
		
		Arrays.sort(tri[n-1]); //������ ����� ��Ʈ���ش�.�ִ밪�� ã�����ؼ� 
		System.out.println(tri[n-1][n-1]); //�ִ밪(�������� ���� �� ) ��� 
	}

}
