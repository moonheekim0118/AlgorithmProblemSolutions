package boj11054;
import java.util.*;
import java.io.*;
/*
 * boj 11054 ���� �� �κ� ������� ���� 
 * ������ -> ���� ( �����ϴ� �κ� ����) �迭�� ����
 * ���� -> ������ ( �����ϴ� �κ� ����) �迭�� ����
 * �� �迭 ��ġ�� �κ� ������� ���� 
 * */
public class Main {	
	private static int [] arr;
	private static int[]dec;
	private static int[]inc;
	private static int[]res;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken()); //���� ���� 
		arr = new int[n];
		dec = new int[n]; //���� 
		inc = new int[n]; //����
		res = new int[n];
		String[] s;
		s = br.readLine().split(" ");
		for(int i=0; i< n;i++) {
			arr[i]=Integer.parseInt(s[i]);
		}
		for(int i=0; i<n; i++) {
			inc[i]=1; //���� 0~ n 
			dec[n-i-1]=1; //���Ҵ� �Ųٷ� n����0 
			for(int j = 0; j<i;j++) {
				if(arr[i]>arr[j] && inc[j]+1>inc[i]) inc[i]=inc[j]+1;
				if(arr[n-i-1]>arr[n-j-1]&& dec[n-j-1]+1>dec[n-i-1]) dec[n-i-1]=dec[n-j-1]+1;
			}
		}
		int max=0;
		for(int i=0; i<n; i++) { //��ġ�� 
			res[i]=inc[i]+dec[i]-1; //i���� ��ġ�Ƿ� -1 
			if(max<res[i]) max=res[i];
		}
		System.out.println(max);
		
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               