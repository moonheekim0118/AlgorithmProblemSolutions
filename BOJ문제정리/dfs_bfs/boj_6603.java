package boj_6603;
import java.io.*;
import java.util.*;
/*
 * boj 6603 로또 
 * 조합 알고리즘 이용
 * */

public class Main {
	private static int[] arr;
	private static int k;
	private static boolean[] select;
	public static void main(String[]args)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[]s;
		while(true) {
			s = br.readLine().split(" ");
			k = Integer.parseInt(s[0]);
			if(k==0) break;
			arr= new int[k];
			select= new boolean[k];
			for(int i=1; i<s.length;i++) {
				arr[i-1]=Integer.parseInt(s[i]);
			}
			solution(0,0);
			System.out.println();
		}
		
	}
	
	private static void print() {
		for(int i=0; i<arr.length;i++) {
			if(select[i]==true) {
				System.out.print(arr[i]+" ");
			}
		}
		System.out.println();
	}
	
	
	private static void solution(int Idx , int Cnt) {
		if(Cnt==6) {
			print();
			return;
		}
		for(int i=Idx; i<arr.length;i++) {
			if(select[i]==true) continue;
			select[i]=true;
			solution(i,Cnt+1);
			select[i]=false;
		}
		
	}
}
