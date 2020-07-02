package boj2309;
import java.util.*;
import java.io.*;
/* boj 2309 일곱난장이
 * 조합 
 * */
public class Main {
	private static boolean[]checker = new boolean[9];
	private static int[]input = new int[9];
	private static boolean found;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		for(int i=0; i<9 ; i++) 
		{
			st = new StringTokenizer(br.readLine());
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);

		solution(0,0);
		for(int i=0; i<9; i++) {
			if(checker[i]) {
				System.out.println(input[i]);
			}
		}
	}
	
	private static void getHeight() {
		int height = 0;
		for(int i = 0; i<9; i++) {
			if(checker[i]==true) {
				height += input[i];
			}
		}
		if(height == 100) found=true;
		
	}
	
	private static void solution(int idx, int cnt) {
		if(cnt==7) {
			getHeight();
			return;
		}
		for(int i= idx; i<9;i++) {
			if(checker[i]==true) { continue ;}
			checker[i]=true;
			solution(i,cnt+1);
			if(found) return;
			checker[i]=false;
		}
		
	}
}
