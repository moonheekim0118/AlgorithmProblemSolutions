package boj1182;
import java.util.*;
import java.io.*;
/* boj 1182 부분수열의 합 
 * 조합 이용 
 * 각각 1개부터 n개까지의 조합을 찾고, 해당 조합의 합이 m이 되면 count를 늘린다.
 * */

public class Main {
	static int[]num;
	static boolean[]checked;
	static int count = 0;
	static int n;
	static int m;
	public static void main(String[] args)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n];
		checked = new boolean[n];
		String s[];
		s = br.readLine().split(" ");
		for(int i = 0 ; i< n; i++) {
			num[i]=Integer.parseInt(s[i]);
		}
		
		for(int i = 1 ; i<= n; i++) {
			combination(0,0,i);
			Arrays.fill(checked, false);
		}
		System.out.println(count);
	}
	
	private static boolean checking() { //조합의 sum 찾는 메서드 
		int sum = 0 ;
		for(int i = 0; i< n; i++) {
			if(checked[i]) {
				sum+=num[i];
			}
		}
		if(sum == m) return true;
		return false;
	}
	
	private static void combination (int idx, int cnt, int num) { //조합 메서드 
		if(cnt == num) {
			if(checking()) count++;
			}
		else {
			for(int i = idx; i<n;i++) {
				if(checked[i]==true) continue;
				checked[i]=true;
				combination(i,cnt+1,num);
				checked[i]=false;
			}
		}
	}
}
