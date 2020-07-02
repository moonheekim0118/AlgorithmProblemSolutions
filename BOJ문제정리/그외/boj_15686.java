package boj15686;
import java.io.*;
import java.util.*;

/* boj 15686 ġŲ���
 * ���� �̿��ؼ� ġŲ�� ������ ã�� �� ��ǥ ��� �Ͽ� min�� ���� 
 * */

class Pair{ //��ǥ �����ϱ� 
	
	int x;
	int y;
	Pair(int x, int y){
		this.x=x;
		this.y=y;
	}
	
}

public class Main {

	private static Pair[] house; //������ ��ǥ �����ϱ� 
	private static Pair[] chiken; //ġŲ�� ��ǥ ���� 
	private static boolean[] select; //ġŲ�� ���� ���ϱ� ����
	static int chiken_index=0; //ġŲ�� ���� 
	static int house_index=0; //������ ���� 
	static int m; //�ִ� ġŲ�� ���� 
	static int answer=99999999;
	
	public static int min(int n, int m) { 
		if(n<m) return n;
		else return m;
	}
	
	public static void main(String[] args)throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		String[] s;
		house = new Pair[2*n];
		chiken = new Pair[13];
		for(int i=0; i<n; i++) 
		{
			s = br.readLine().split(" ");
			for(int j=0; j<n ; j++) 
			{
				int input = Integer.parseInt(s[j]);
				if(input == 1) { //������
					house[house_index]= new Pair(i+1,j+1);
					house_index++; // ������ ��ǥ pair�� ���·� ���� 
				}
				else if(input ==2) { //ġŲ��
					chiken[chiken_index]= new Pair(i+1, j+1);
					chiken_index++;
				}
			}
		}
		select = new boolean[chiken_index]; 
		Select_chiken(0,0);
		System.out.println(answer);
	}
	
	
	public static int calculate() { // �Ÿ� ���ϱ� 
		int sum =0;
		for(int i =0; i< house_index; i++) { //��� �������� ���ؼ�
			int x = house[i].x;
			int y = house[i].y;
			int d = 99999999;
			for(int j=0; j<chiken_index;j++) { //���� ������ ��� ġŲ������ �Ÿ� ���ϱ�. min������ �ּ� �� ���ϴ� ��. 
				if(select[j]==true) { //���� ���õ� ġŲ��
					int xx = chiken[j].x; 
					int yy = chiken[j].y;
					int Dist = Math.abs(xx-x) + Math.abs(yy-y);
					d = min(d,Dist);
				}
			}
			sum += d; //�ּҰ����� �� 
		}
		return sum;
	} 
	
	public static void Select_chiken(int idx, int cnt)  //���ձ��ϱ� 
	{
		if(cnt == m) { 
			answer = min(answer , calculate());
			return;
		}
		for(int i= idx ; i <chiken_index;i++ ) {
			if(select[i]==true) continue;
			select[i]=true;	//�� �Ŀ������þȵǵ���
			Select_chiken(i, cnt+1);
			select[i]=false;// �� �� ��Ϳ��� ���õǵ��� 
		}
	}
}
