package boj2565;
import java.io.*;
import java.util.*;

/*
 * boj 2565 전깃줄 
 * LIS 응용문제
 * 전봇대1- 전봇대 2 주어졌을때 전봇대 1을 오름차순으로 소팅한다.
 * 이 때 전봇대 2에서 LIS 구조를 깨는 것의 개수가 바로 잘려나갈 최소 전깃줄의 개수이다. 
 * */

class fair implements Comparable<fair>
{
	int left;
	int right;
	public int compareTo(fair o) {
		return left-o.left ;
	}
}

public class Main {
	static fair[]info = new fair[501];
	static int[] LIS;
	public static void main(String[] args)throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //전기줄 개수
		String[] s;
		int index, num;
		for(int i= 0 ; i< 501; i++) {
			info[i]=new fair();
			info[i].left=501;
		}
		LIS = new int[n];
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			index = Integer.parseInt(s[0]);
			num = Integer.parseInt(s[1]);
			info[i].left=index;
			info[i].right=num;
		}
		Arrays.sort(info);

		for(int i =0 ; i<n; i++)  // LIS 찾기 
		{
			LIS[i]=1;
			int tmp = info[i].right;
			for(int j = 0; j<i ; j++) 
			{
				if(info[j].right<tmp && LIS[j]+1 > LIS[i]) LIS[i]=LIS[j]+1;
			}
		}
		Arrays.sort(LIS);
		int res = n - LIS[n-1]; //전깃줄 개수에서 LIS 개수 빼면 ,끊어여할 전깃줄의 개수가 나온다.
		System.out.println(res);
	}
}
