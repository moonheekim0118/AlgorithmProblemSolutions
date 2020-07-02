package boj1016;
import java.io.*;
import java.util.*;
/* boj 1016 제곱 ㄴㄴ수
 * 꼭 다시 풀어보기 
 * 계속 시간초과 났었음
 * */

public class Main {
	public static void main(String[]args)throws IOException
	{
		boolean [] check;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		int cnt=0;
		check = new boolean[(int)(max-min+1)];
		
		for (long i=2 ; i*i<=max; i++) {
			long pwr = i*i;
			long start = min%pwr ==0 ? min/pwr : (min/pwr)+1;
			for(long j = start ; pwr*j <=max ; j++)
				check[(int)((j*pwr)-min)]=true;
		}
		
		for(long i =0; i<max-min+1;i++) {
			if(check[(int)i]==false) cnt++;
		}
		System.out.println(cnt);
	}
}
