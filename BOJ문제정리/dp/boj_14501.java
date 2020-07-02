package boj14501;
import java.util.*;
import java.io.*;

/* boj 14501 퇴사
 * 브루트 포스 / 다이나믹 프로그래밍 ? 
 * 특정 범위에 해당하는 최댓값들의 합 == 최댓값 
 * */
public class Main {
	static int[][]input;
	public static void main(String[]args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		input = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		int get = 0; 
		
		for(int i=0; i<n ; i++) {
			if(input[i][0]+i>n) continue; //범위 넘는것 건너뛰기 
			get = solve(i,n); //모든 1일부터 n일까지 시작해보기 
			if(get > max) max =get;//최댓값 구하기 
		}
		System.out.println(max);
		
	}
	
	static int solve(int idx, int n){
		int max = 0;
		int sum = 0;
		for(int i = idx; i < n ; i++) {
			if(input[i][0]+i>n) continue; //범위 넘는것 건너뛰기
			sum+=input[i][1]+solve(input[i][0]+i,n); //최댓값구하기 
			if(sum > max) max = sum; // 범위 내 최댓값 구하기 
			sum = 0;
		}
		return max; //범위내 최댓값 리턴 
	}
}
