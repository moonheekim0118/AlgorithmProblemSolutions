package boj14501;
import java.util.*;
import java.io.*;

/* boj 14501 ���
 * ���Ʈ ���� / ���̳��� ���α׷��� ? 
 * Ư�� ������ �ش��ϴ� �ִ񰪵��� �� == �ִ� 
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
			if(input[i][0]+i>n) continue; //���� �Ѵ°� �ǳʶٱ� 
			get = solve(i,n); //��� 1�Ϻ��� n�ϱ��� �����غ��� 
			if(get > max) max =get;//�ִ� ���ϱ� 
		}
		System.out.println(max);
		
	}
	
	static int solve(int idx, int n){
		int max = 0;
		int sum = 0;
		for(int i = idx; i < n ; i++) {
			if(input[i][0]+i>n) continue; //���� �Ѵ°� �ǳʶٱ�
			sum+=input[i][1]+solve(input[i][0]+i,n); //�ִ񰪱��ϱ� 
			if(sum > max) max = sum; // ���� �� �ִ� ���ϱ� 
			sum = 0;
		}
		return max; //������ �ִ� ���� 
	}
}
