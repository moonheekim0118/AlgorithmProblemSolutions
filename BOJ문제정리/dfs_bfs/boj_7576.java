package boj7576;
import java.util.*;
import java.io.*;
/*
 * boj 7576 토마토 
 * BFS 레벨  구하기 
 * segment 와 count 변수로 구하기
 * 이미 익은 토마토는 먼저 enqueue 해놓는다. 
 * */

class pair{
	int n ;
	int m; 
}
public class Main {
	private static int N;
	private static int M;
	private static int segment=0;
	private static int[][]graph;
	private static boolean[][]visited;
	private static int level = 0 ;
	private static Queue<pair>queue= new LinkedList<pair>();
	private static void BFS() 
	{
		int count=0;
		boolean done=false;
		while(!queue.isEmpty()) 
		{
			if(segment==0) { //하루가 지나면 
				if(done==true) level++; //익은 토마토가 있었다면
				                        //토마토가 익지 않을 수 도 있기때문에 꼭 (이미 익었다거나, 없거나) 
				segment=count;  //익은토마토만큼으로 바꿔준다. 
				count = 0;
				done=false;
			}
			
			segment--; 
			pair deq = new pair();
			deq=queue.poll();
			int dn=deq.n;
			int dm=deq.m;
			if(dn!=0 && graph[dn-1][dm]==0 && visited[dn-1][dm]==false) {
				pair en = new pair();
				visited[dn-1][dm]=true;
				graph[dn-1][dm]=1;
				en.n=dn-1;
				en.m=dm;
				queue.offer(en);
				count ++;
				done=true;
			}
			if(dn!=N-1 && graph[dn+1][dm]==0&& visited[dn+1][dm]==false) {
				pair en = new pair();
				visited[dn+1][dm]=true;
				graph[dn+1][dm]=1;
				en.n=dn+1;
				en.m=dm;
				queue.offer(en);
				count++;
				done=true;
			}
			
			if(dm!=0 && graph[dn][dm-1]==0 && visited[dn][dm-1]==false) {
				pair en = new pair();
				visited[dn][dm-1]=true;
				graph[dn][dm-1]=1;
				en.n=dn;
				en.m=dm-1;
				queue.offer(en);
				count++;
				done=true;
			}
			
			if(dm!=M-1 && graph[dn][dm+1]==0 && visited[dn][dm+1]==false) {
				pair en = new pair();
				visited[dn][dm+1]=true;
				graph[dn][dm+1]=1;
				en.n=dn;
				en.m=dm+1;
				queue.offer(en);
				count++;
				done=true;
			}
		}	
			
		
		
	}
	public static void main(String[] args)throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s1;
		s1=br.readLine().split(" ");
		M =Integer.parseInt(s1[0]);
		N =Integer.parseInt(s1[1]);
		graph = new int[N][M]; 
		visited=new boolean[N][M];
		String[] s2;
		
		for(int i= 0 ; i < N; i++) {
			s2=br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				graph[i][j]=Integer.parseInt(s2[j]);
				if(graph[i][j]==1) { //익어서 나온 토마토는 미리 queue에 넣어준다. 
					visited[i][j]=true;
					pair p= new pair();
					p.n=i; 
					p.m=j;
					queue.offer(p);
					segment++; //이미 익어서 들어간 토마토의 수
					           //같은 날부터 시작해야하므로 
				}
			}
		}
		
		BFS();
		boolean done = true; 
		for(int i=0; i<N; i++) { //익지 못한 토마토가 있는지 체크 
			for(int j=0; j<M ; j++) {
				if(graph[i][j]==0) {
					done =false;
					break;
				}
			}
		}
		if(!done) { 
			System.out.println(-1); }
		else {
		System.out.println(level);
		}
	}
}
