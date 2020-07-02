package boj11054;
import java.util.*;
import java.io.*;
/*
 * boj 11054 가장 긴 부분 바이토닉 수열 
 * 오른쪽 -> 왼쪽 ( 증가하는 부분 수열) 배열에 저장
 * 왼쪽 -> 오른쪽 ( 감소하는 부분 수열) 배열에 저장
 * 두 배열 합치면 부분 바이토닉 수열 
 * */
public class Main {	
	private static int [] arr;
	private static int[]dec;
	private static int[]inc;
	private static int[]res;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //수열 길이 
		arr = new int[n];
		dec = new int[n]; //감소 
		inc = new int[n]; //증가
		res = new int[n];
		String[] s;
		s = br.readLine().split(" ");
		for(int i=0; i< n;i++) {
			arr[i]=Integer.parseInt(s[i]);
		}
		for(int i=0; i<n; i++) {
			inc[i]=1; //증가 0~ n 
			dec[n-i-1]=1; //감소는 거꾸로 n부터0 
			for(int j = 0; j<i;j++) {
				if(arr[i]>arr[j] && inc[j]+1>inc[i]) inc[i]=inc[j]+1;
				if(arr[n-i-1]>arr[n-j-1]&& dec[n-j-1]+1>dec[n-i-1]) dec[n-i-1]=dec[n-j-1]+1;
			}
		}
		int max=0;
		for(int i=0; i<n; i++) { //합치기 
			res[i]=inc[i]+dec[i]-1; //i값이 겹치므로 -1 
			if(max<res[i]) max=res[i];
		}
		System.out.println(max);
		
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               