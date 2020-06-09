package boj1032;

/*
boj 1032 명령프롬프트
문자열 처리문제 
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
		//0번째 cmd를 ans에 저장해놓기
		//1번째부터 비교한다음에 다른부분은 ? 으로 바꾸기
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
