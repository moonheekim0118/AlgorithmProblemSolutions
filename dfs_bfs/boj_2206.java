package boj2206_;
import java.util.*;

/*
 * boj 2206 ���μ��� �̵� 
 * bfs ���, ť�� distance�� �� �μ� ���θ� �־��ش�.
 * visit�� ���� �־� �������ش�.
 * visit ���� ť���� ���� ����� wall������ ũ�� -> �ڽ��� ���� �վ ��ο� �����ְ�, �θ�� ���� �վ�� ���� ��ο� ��������
 * �� ��, ��� �ߺ��� ����ϱ� ���ؼ�, �ڽ��� ���� �վ�� ���� ��ο� ���Խ�Ų��.
 * visit ���� ť���� ���� ����� wall������ �۴ٸ� -> �ڽ��� ���� �վ�� ���� ��ο� �����ְ�, �θ�� ���� ���� ��ο� �����ִ�. 
 * �� ��, �� �� ��� �ߺ��� ����ϱ� ����, �ڽ��� �׳� ���� �վ�� ���� ��ο��� ���Խ�Ű�� ����. 
 * 
 * 
 * */

class pair
{
	int x;
	int y;
	int dis; //�Ÿ�
	int wall; //�� �վ��°� 
	
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
				//���� ���� ��ο��� ���Դٸ�, ���� ���� ����� wall Ƚ���� �����صд�.
				//���߿� ������ ��ο� ���ִ� ����� �ߺ��� ����ϱ� ���ؼ� 
				//�ݴ�� �����ϸ�, ���� �ִ� ��ο��� ���� ���� ���� ���� ����� wallȽ���� �ٽ� ����� �� ����.
				
				
				if(map[nx][ny]==0) { //���� �ƴ� �� , ���� ���� ��ο��� ����ģ�� ���� �� 
					q.add(new pair(nx, ny, tmp.dis+1, tmp.wall));  //distance�� �÷��ش�.
					visit[nx][ny]=tmp.wall; //���� 
				}
				else { //���� �� 
					if(tmp.wall==0) { //���� �̹� ������ ���� ��, ������ �ִٸ� �ٽ�  ���� �ʾ��ش�. (ť�� ���� X) 
						q.add(new pair(nx, ny, tmp.dis+1, tmp.wall+1)); //distance�� wall�� ���� 
						visit[nx][ny]=tmp.wall+1;
					}
				}
			}
			
		}
	}
}
