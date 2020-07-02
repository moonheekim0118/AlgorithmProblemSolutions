package boj16234;
import java.io.*;
import java.util.*;

/* boj 16234 인구이동
 * bfs 문제 
 * 1.국경이 열리는 모든 국가들을 먼저 표시해준다.
 * 2.해당 국가에 대해 bfs탐색을 하며 LR조건을 지키는 국가+국경이 열리는 국가를 bfs큐와 인구이동 큐에 넣어주고, 총 인구수와 총 국가수를 구한다.
 * 3.bfs탐색이 끝나면 인구이동 큐에 있는 국가들의 인구를 조정해준다. 
 * 4.국경이 열리는 국가가 없을 때 까지 반복해준다. 
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
			checking(n,L,R); //탐색 할 노드 체크해주기! 위아래좌우에 열린 국경이 있는 국가들 모두 체크 
			if(!flag) break; // 국가가 하나도 안체크되어있으면 끝 
			for(int i = 0; i<n;i++) { // 체크된 국가 bfs돌기 
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
			
			cnt++; //이동 횟수 증가 
		
		}
		System.out.println(cnt);
	}
	
	
	private static void checking(int N, int L, int R) { //국경 열린 것 체크해주는 함수 
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
	
	private static void change_poupluation(int num) { //인구 이동해주는 함수 
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
					//visit이 안되어있고, 현재 bfs 탐색중인 노드인 country[x][y] 사이와의 국경이 열려있을 때 
					q.add(new location(nx,ny)); // bfs큐에 넣기 
					store.add(new location(nx,ny)); //인구 수 변동 될 큐에 넣기 
					visit[nx][ny]=true;
					count++; //국가 수 
					pop_sum+=country[nx][ny]; //인구 수 
				}
			}

		}
		change_poupluation(pop_sum/count);
	}  
}
