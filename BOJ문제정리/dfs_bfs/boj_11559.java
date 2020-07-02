package boj_11559;
import java.util.*;
import java.io.*;

/*
 * boj 11559 puyo puyo
 * dfs + �ùķ��̼�
 * �ѿ並 �߷¿� ���� �����̴� �� �� ��ٷο���
 * 
 * */

class pair //ť�� ���� 
{
	int x;
	int y;
	pair(int x, int y){
		this.x=x;
		this.y=y;
	}
}


public class Main {
	private static String[][] map;
	private static boolean[][] visit;
	private static boolean[][] check;
	private static int []dx = {-1,1,0,0};
	private static int[]dy= {0,0,-1,1};
	private static int queue_num=0;
	private static Queue<pair>queue = new LinkedList<>();
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new String[12][6];
		visit= new boolean[12][6];
		check = new boolean[12][6];
		String[] s;
		for(int i= 0; i< 12; i++) {
			s = br.readLine().split("");
			for(int j=0; j<6; j++) {
				map[i][j]=s[j];
				if(!s[j].equals(".")) { //���ڶ�� ť�� �������ش�. 
					queue.add(new pair(i,j));
					queue_num++; //ť�� ����� ������ �� 
				}
			}
		}
		System.out.println(solution());
	}
	static int puyo=0;
	private static void fill() {
		for(boolean a[]:check) {
			Arrays.fill(a, false);
		}
	}
	
	private static void update()  //�� ������Ʈ, �߷¿� ���� �ѿ���� ��������.
	{
		for(int j=0; j<6; j++) { //�ڸ��̵� 
			for(int i=11; i>=0; i--) 
			{
				if(visit[i][j] && !map[i][j].equals(".")) map[i][j]="."; //visit�� ������ .���� �ٲپ��ش�. 
			}
			
			for(int i=11; i>=0; i--) 
			{
				if(map[i][j].equals(".") && visit[i][j]) 
				{
					for(int k = i-1 ; k>=0; k--) 
					{   //.���� �ٲ� ���� ������ visited �ȵ� ������ ����  (���� ����� ������ ����..)
						if(!visit[k][j] && !map[k][j].equals(".")) {
							map[i][j]=map[k][j];
							map[k][j]=".";
							visit[i][j]=false;
							visit[k][j]=true; //�� �������� �ٸ� ��Ұ� ������ �� �ֵ���!
							break;
						}
					}
				}
			}
		}
	}
	
	private static void enqueue()  //������Ʈ �� ���� �������� ť�� �ٽ� �־��ִ� �޼��� 
	{
		boolean flag=false;
		for(int i = 0; i<=11; i++)  //ť�� �ٽ� �־��ش�. 
		{
			for(int j = 0; j<6 ; j++) 
			{
				if(!visit[i][j] && !map[i][j].equals(".")) {
					queue.add(new pair(i,j));
					queue_num++;
				}
			}
		}
	}
	
	private static int solution() {
		int n =0; // ���� 
		int count=0; //ť�� ����ŭ 
		boolean done=false; // �ѿ䰡 �����°�? 
		while(!queue.isEmpty()) {  
			pair tmp = queue.poll(); //ť���� �����ش�. 
			count ++; //ť���� ������ ��ŭ ī���� 
			if(!visit[tmp.x][tmp.y]) {  //���� visit�� �ȵǾ��ٸ� dfs�����ش�. 
				dfs(tmp.x,tmp.y);
				if(puyo>=4) done=true; //puyo�� 4�̻��̸� �����ۿ��� �ѹ� �̻� �Ͼ ������ üũ 
				puyo=0;  //puyo �ʱ�ȭ 
				fill(); //check �ʱ�ȭ (dfsŽ���� ����) 
			}
			
			if(count == queue_num) { //��� ť�� �� ���´ٸ� 

				
				update(); //map�� ������Ʈ���ش�. 
				if(done) n++; //�ѹ��̶� �����ٸ� n++
				if(!done) return n; //�������ٸ� �� �̻����� �������Ƿ� �� 
				queue_num=0;
				enqueue();
				if(queue_num==0) return n; 
				done=false; 
				count =0;

			}
			
		}
		return n;
	}
	private static void dfs(int x , int y) 
	{
		String color=map[x][y];
		puyo++;
		check[x][y]=true;
		for(int i=0; i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			if(nx < 0 || nx > 11 || ny <0 || ny >=6) continue;
			if(map[nx][ny].equals(color) && !check[nx][ny]) {
				dfs(nx,ny); //���� ���� dfs�� ���ش�. 
			}
		}
		
		if(puyo>=4) {
			visit[x][y]=true;
		}
	}
}

