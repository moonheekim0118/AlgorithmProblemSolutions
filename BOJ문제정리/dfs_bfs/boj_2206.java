package boj2206_;
import java.util.*;

/*
 * boj 2206 벽부수고 이동 
 * bfs 사용, 큐에 distance와 벽 부순 여부를 넣어준다.
 * visit에 값을 넣어 갱신해준다.
 * visit 값이 큐에서 나온 요소의 wall값보다 크면 -> 자식은 벽을 뚫어본 경로에 속해있고, 부모는 벽을 뚫어보지 않은 경로에 속해있음
 * 이 때, 경로 중복을 허용하기 위해서, 자식을 벽을 뚫어보지 않은 경로에 포함시킨다.
 * visit 값이 큐에서 나온 요소의 wall값보다 작다면 -> 자식은 벽을 뚫어보지 않은 경로에 속해있고, 부모는 벽을 뚫은 경로에 속해있다. 
 * 이 때, 추 후 경로 중복을 허용하기 위해, 자식을 그냥 벽을 뚫어보지 않은 경로에만 포함시키고 만다. 
 * 
 * 
 * */

class pair
{
	int x;
	int y;
	int dis; //거리
	int wall; //벽 뚫었는가 
	
	pair(int x,int y , int dis, int wall){
		this.x=x;
		this.y=y;
		this.dis=dis;
		this.wall=wall;
	}
	
}


public class Main {
	static int N;
	static int M;
	static int[]dx = {-1,1,0,0};
	static int[]dy= {0,0,-1,1};
	static int[][]map;
	static int[][]visit;
	static int ans;
	public static void main(String[] args)
	{
        Scanner sc = new Scanner(System.in);	
		N = sc.nextInt();
		M = sc.nextInt();
		String s;
		map=new int[N][M];
		visit=new int[N][M];
		for(int i=0; i<N; i++) {
			s=sc.next();
			for(int j =0; j<M; j++) {
			map[i][j]=s.charAt(j)-'0';
			visit[i][j]=Integer.MAX_VALUE;
			}
		}
		ans=Integer.MAX_VALUE;
		bfs();
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	
	}
	
	public static void bfs() {
		Queue<pair>q = new LinkedList<>();
		q.add(new pair(0,0,1,0));
		visit[0][0]=0;
		while(!q.isEmpty()) {
			pair tmp = q.poll();
			int x = tmp.x;
			int y =tmp.y;
			if(x==N-1 && y==M-1) {
				ans = tmp.dis;
				break;
			}
			for(int i=0; i<4 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || ny <0 || nx >= N || ny >= M) continue;
				
				if(tmp.wall >= visit[nx][ny])continue;
				//벽이 없는 경로에서 나왔다면, 벽이 없는 경로의 wall 횟수로 저장해둔다.
				//나중에 벽없는 경로와 벽있는 경로의 중복을 허용하기 위해서 
				//반대로 생각하면, 벽이 있는 경로에서 나온 노드는 벽이 없는 경로의 wall횟수로 다시 저장될 수 있음.
				
				
				if(map[nx][ny]==0) { //벽이 아닐 때 , 벽이 없는 경로에서 지나친적 없을 때 
					q.add(new pair(nx, ny, tmp.dis+1, tmp.wall));  //distance만 늘려준다.
					visit[nx][ny]=tmp.wall; //저장 
				}
				else { //벽일 때 
					if(tmp.wall==0) { //벽을 이미 뚫은적 없을 때, 뚫은적 있다면 다시  뚫지 않아준다. (큐에 저장 X) 
						q.add(new pair(nx, ny, tmp.dis+1, tmp.wall+1)); //distance와 wall만 저장 
						visit[nx][ny]=tmp.wall+1;
					}
				}
			}
			
		}
	}
}
