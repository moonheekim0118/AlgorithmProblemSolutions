package boj15686;
import java.io.*;
import java.util.*;

/* boj 15686 치킨배달
 * 조합 이용해서 치킨집 조합을 찾은 후 좌표 계산 하여 min값 저장 
 * */

class Pair{ //좌표 저장하기 
	
	int x;
	int y;
	Pair(int x, int y){
		this.x=x;
		this.y=y;
	}
	
}

public class Main {

	private static Pair[] house; //가정집 좌표 저장하기 
	private static Pair[] chiken; //치킨집 좌표 저장 
	private static boolean[] select; //치킨집 조합 구하기 위해
	static int chiken_index=0; //치킨집 개수 
	static int house_index=0; //가정집 개수 
	static int m; //최대 치킨집 개수 
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
				if(input == 1) { //가정집
					house[house_index]= new Pair(i+1,j+1);
					house_index++; // 각각의 좌표 pair의 형태로 저장 
				}
				else if(input ==2) { //치킨집
					chiken[chiken_index]= new Pair(i+1, j+1);
					chiken_index++;
				}
			}
		}
		select = new boolean[chiken_index]; 
		Select_chiken(0,0);
		System.out.println(answer);
	}
	
	
	public static int calculate() { // 거리 구하기 
		int sum =0;
		for(int i =0; i< house_index; i++) { //모든 가정집에 대해서
			int x = house[i].x;
			int y = house[i].y;
			int d = 99999999;
			for(int j=0; j<chiken_index;j++) { //현재 조합의 모든 치킨집과의 거리 구하기. min값으로 최소 값 구하는 것. 
				if(select[j]==true) { //현재 선택된 치킨집
					int xx = chiken[j].x; 
					int yy = chiken[j].y;
					int Dist = Math.abs(xx-x) + Math.abs(yy-y);
					d = min(d,Dist);
				}
			}
			sum += d; //최소값들의 합 
		}
		return sum;
	} 
	
	public static void Select_chiken(int idx, int cnt)  //조합구하기 
	{
		if(cnt == m) { 
			answer = min(answer , calculate());
			return;
		}
		for(int i= idx ; i <chiken_index;i++ ) {
			if(select[i]==true) continue;
			select[i]=true;	//이 후에서선택안되도록
			Select_chiken(i, cnt+1);
			select[i]=false;// 이 전 재귀에서 선택되도록 
		}
	}
}
