package boj1032;

/*
boj 1032 ���������Ʈ
���ڿ� ó������ 
*/
import java.io.*;
import java.util.*;
public class Main {
	private static String[] cmd;
	public static void main(String[] args)throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		cmd = new String[n];
		for(int i=0 ; i<n; i++) 
		{
			 cmd[i]=br.readLine();
		}
		char[]ans;
		int len = cmd[0].length();
		ans = new char[len]; 
		//0��° cmd�� ans�� �����س���
		//1��°���� ���Ѵ����� �ٸ��κ��� ? ���� �ٲٱ�
		ans = cmd[0].toCharArray();
		for(int i=1; i<n;i++) {
			for(int j = 0; j<len ; j++) {
				if(ans[j]!=cmd[i].charAt(j)) {
					ans[j]='?';
				}
			}
		}
		System.out.println(ans);
		
	}
}
