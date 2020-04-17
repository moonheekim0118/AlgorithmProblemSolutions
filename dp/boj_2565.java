package boj2565;
import java.io.*;
import java.util.*;

/*
 * boj 2565 ������ 
 * LIS ���빮��
 * ������1- ������ 2 �־������� ������ 1�� ������������ �����Ѵ�.
 * �� �� ������ 2���� LIS ������ ���� ���� ������ �ٷ� �߷����� �ּ� �������� �����̴�. 
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
		int n = Integer.parseInt(stk.nextToken()); //������ ����
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

		for(int i =0 ; i<n; i++)  // LIS ã�� 
		{
			LIS[i]=1;
			int tmp = info[i].right;
			for(int j = 0; j<i ; j++) 
			{
				if(info[j].right<tmp && LIS[j]+1 > LIS[i]) LIS[i]=LIS[j]+1;
			}
		}
		Arrays.sort(LIS);
		int res = n - LIS[n-1]; //������ �������� LIS ���� ���� ,����� �������� ������ ���´�.
		System.out.println(res);
	}
}
