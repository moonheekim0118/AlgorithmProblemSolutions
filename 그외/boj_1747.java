package boj1747;
import java.io.*;
import java.util.*;

public class Main {
	private static boolean[] prime_num;
	public static void main(String[]args)throws IOException
	{
		prime_num= new boolean[1004001];
		prime_num[1]=true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		if(n <=7) {
			Eratosthenes(10);
			for(int i = n; i<=10;i++) {
				if(!prime_num[i]) {
					System.out.println(i);
					break;
				}
			}
			}
		else {			
			   Eratosthenes(1004000);
				for(int i = n; i<=1004000;i++) {
					if(!prime_num[i]&&isPalindrome(i)==true) {
						System.out.println(i);
						break;
					}
				}
			}
		}
	
	private static void Eratosthenes(int n) {
		for(int i = 2; i*i <= n; i++) {
			if(prime_num[i]==false) {
				for(int k = i*i; k<=n; k+=i)
					prime_num[k]=true;
			}
		}
	} 
	private static boolean isPalindrome(int num) {
		String s = Integer.toString(num);
		if(s.length()%2==0) { 
			int i = 0;
			int j = s.length()-1;
			while(i<j) {
				if(s.charAt(i)!=s.charAt(j))
					return false;
				i++;
				j--;
			}
		}
		else { //È¦¼öÀÏ °æ¿ì 
			int i =0 ;
			int j = s.length()-1;
			while(i!=j) {
				if(s.charAt(i)!=s.charAt(j)) 
					return false;
				i++;
				j--;
			}
		}
		return true;
	}
}
