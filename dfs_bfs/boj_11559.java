package boj_11559;
import java.util.*;
import java.io.*;

/*
 * boj 11559 puyo puyo
 * dfs + 시뮬레이션
 * 뿌요를 중력에 따라 움직이는 게 좀 까다로웠음
 * 
 * */

class pair //큐에 저장 
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
				if(!s[j].equals(".")) { //문자라면 큐에 저장해준다. 
					queue.add(new pair(i,j));
					queue_num++; //큐에 저장된 데이터 수 
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
	
	private static void update()  //맵 업데이트, 중력에 의해 뿌요들이 떨어진다.
	{
		for(int j=0; j<6; j++) { //자리이동 
			for(int i=11; i>=0; i--) 
			{
				if(visit[i][j] && !map[i][j].equals(".")) map[i][j]="."; //visit된 값들을 .으로 바꾸어준다. 
			}
			
			for(int i=11; i>=0; i--) 
			{
				if(map[i][j].equals(".") && visit[i][j]) 
				{
					for(int k = i-1 ; k>=0; k--) 
					{   //.으로 바꾼 값을 위에서 visited 안된 값으로 변경  (가장 가까운 값으로 변경..)
						if(!visit[k][j] && !map[k][j].equals(".")) {
							map[i][j]=map[k][j];
							map[k][j]=".";
							visit[i][j]=false;
							visit[k][j]=true; //이 공간에도 다른 요소가 내려올 수 있도록!
							break;
						}
					}
				}
			}
		}
	}
	
	private static void enqueue()  //업데이트 된 맵을 기준으로 큐에 다시 넣어주는 메서드 
	{
		boolean flag=false;
		for(int i = 0; i<=11; i++)  //큐에 다시 넣어준다. 
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
		int n =0; // 연쇄 
		int count=0; //큐에 들어간만큼 
		boolean done=false; // 뿌요가 터졌는가? 
		while(!queue.isEmpty()) {  
			pair tmp = queue.poll(); //큐에서 꺼내준다. 
			count ++; //큐에서 꺼내준 만큼 카운팅 
			if(!visit[tmp.x][tmp.y]) {  //아직 visit이 안되었다면 dfs돌려준다. 
				dfs(tmp.x,tmp.y);
				if(puyo>=4) done=true; //puyo가 4이상이면 연쇄작용이 한번 이상 일어난 것으로 체크 
				puyo=0;  //puyo 초기화 
				fill(); //check 초기화 (dfs탐색을 위해) 
			}
			
			if(count == queue_num) { //모든 큐를 다 꺼냈다면 

				
				update(); //map을 업데이트해준다. 
				if(done) n++; //한번이라도 터졌다면 n++
				if(!done) return n; //안터졌다면 더 이상터질 수없으므로 끝 
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
				dfs(nx,ny); //같은 색깔만 dfs를 해준다. 
			}
		}
		
		if(puyo>=4) {
			visit[x][y]=true;
		}
	}
}

