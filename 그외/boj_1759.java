package boj1759;
import java.io.*;
import java.util.*;

/*
 * boj 1759 암호 만들기
 * 조합 알고리즘 이용 
 * 
 * */
public class Main {
	static String[] arr ;
	static boolean[] checked;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new String[m];
		checked = new boolean[m];
		String s[];
		s = br.readLine().split(" ");
		for(int i=0; i<m; i++) {
			arr[i]=s[i];
		}		
		Arrays.sort(arr);
		solution(0,0,n,m);
	}
	
	//조합
	static String v ="aeiou";
	static String c ="bcdfghjklmnpqrstvwxyz";
	static void show(int m) {
		String ans="";
		int v_num=0;
		int c_num=0;
		for(int i=0; i<m;i++) {
			if(checked[i]) {
				if(v.contains(arr[i])) v_num++;
				if(c.contains(arr[i])) c_num++;
				ans+=arr[i];
			}
		}
		if(v_num>=1 && c_num >=2) System.out.println(ans);
		
		
	}
	
	static void  solution(int cnt, int idx, int n, int m) {
		if(cnt==n) {
			show(m);
		}
		else {
			for(int i = idx ; i<m; i++ ) {
				if(checked[i]) continue;
				checked[i]=true;
				solution(cnt+1, i, n, m);
				checked[i]=false;
			}
			
		}
	}
}
