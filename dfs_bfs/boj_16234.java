package boj16234;
import java.io.*;
import java.util.*;

/* boj 16234 �α��̵�
 * bfs ���� 
 * 1.������ ������ ��� �������� ���� ǥ�����ش�.
 * 2.�ش� ������ ���� bfsŽ���� �ϸ� LR������ ��Ű�� ����+������ ������ ������ bfsť�� �α��̵� ť�� �־��ְ�, �� �α����� �� �������� ���Ѵ�.
 * 3.bfsŽ���� ������ �α��̵� ť�� �ִ� �������� �α��� �������ش�. 
 * 4.������ ������ ������ ���� �� ���� �ݺ����ش�. 
 * */

class location
{
	public int x;
	public int y;
	
	public location(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class Main {
	private static int[][]country;
	private static boolean[][]visit;
	private static boolean[][]check;
	private static Queue<location>q= new LinkedList<location>();
	private static Queue<location>store= new LinkedList<location>();
	private static boolean flag;
	static int[]dx = {-1,1,0,0};
	static int[]dy= {0,0,-1,1};
	
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		country = new int[n][n];
		visit = new boolean[n][n];
		check = new boolean[n][n];
		String[]s;
		for(int i=0; i<n;i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				country[i][j]=Integer.parseInt(s[j]);
			}
		}
		int cnt=0;
		while(true) {
			flag=false;
			checking(n,L,R); //Ž�� �� ��� üũ���ֱ�! ���Ʒ��¿쿡 ���� ������ �ִ� ������ ��� üũ 
			if(!flag) break; // ������ �ϳ��� ��üũ�Ǿ������� �� 
			for(int i = 0; i<n;i++) { // üũ�� ���� bfs���� 
				for(int j=0; j<n; j++) {
					if(check[i][j] && visit[i][j]!=true) { 
						bfs(n,i,j,L,R);
					}
				}
			}
			for(boolean i[] : visit) {
				Arrays.fill(i, false);}
			for(boolean j[]: check) {
				Arrays.fill(j, false); 
			}
			
			cnt++; //�̵� Ƚ�� ���� 
		
		}
		System.out.println(cnt);
	}
	
	
	private static void checking(int N, int L, int R) { //���� ���� �� üũ���ִ� �Լ� 
		for(int i=0; i<N ;i++) {
			for(int j=0; j<N; j++) {
				boolean flag2=false;
				for(int m=0; m<4;m++) {
					int nx=i+dx[m];
					int ny=j+dy[m];
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
					if(check[nx][ny]) continue;
					int num =Math.abs(country[nx][ny]-country[i][j]);
					if(num<=R && num>=L) {
						check[nx][ny]=true;
						flag2=true;
						flag=true;
					}
				}
				if(flag2) check[i][j]=true;
			}
		}
	}
	
	private static void change_poupluation(int num) { //�α� �̵����ִ� �Լ� 
		while(!store.isEmpty()) {
			location peekNum=store.poll();
			int x = peekNum.x; 
			int y = peekNum.y;
			country[x][y]=num; 
		}
	}
	
	private static void bfs(int N , int mx, int my, int L, int R) {
		int count=1;
		int pop_sum=country[mx][my];
		visit[mx][my]=true; 
		store.add(new location(mx,my));
		q.add(new location(mx,my));
		while(!q.isEmpty()) {
			location peekNum=q.poll();
			int x = peekNum.x;
			int y = peekNum.y;
			for(int i=0; i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				int num = Math.abs(country[nx][ny]-country[x][y]);
				if(visit[nx][ny]!=true && (num>=L && num <=R)) {
					//visit�� �ȵǾ��ְ�, ���� bfs Ž������ ����� country[x][y] ���̿��� ������ �������� �� 
					q.add(new location(nx,ny)); // bfsť�� �ֱ� 
					store.add(new location(nx,ny)); //�α� �� ���� �� ť�� �ֱ� 
					visit[nx][ny]=true;
					count++; //���� �� 
					pop_sum+=country[nx][ny]; //�α� �� 
				}
			}

		}
		change_poupluation(pop_sum/count);
	}  
}
