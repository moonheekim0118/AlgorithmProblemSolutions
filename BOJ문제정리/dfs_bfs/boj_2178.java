package boj2178;
import java.util.*;
import java.io.*;

/* boj 2178 미로탐색 
 * BFS 사용
 * BFS를 통해서 부모노드의 거리 + 1 을 하면, 현재 까지의 거리가 나온다. 
 * 입력이 까다로웠던 문제
 * 자바에서 아무런 split 없이 입력을 받아서 정수로 파싱하려면
 * 스트링 배열이 아니라 스트링으로 받은 후에, charAt -> 다시 스트링으로 변환한 후 parseInt 해줘야함
 * */

class pair //큐에 2차원 배열 정보를 넣기 위한 클래스를 따로 선언했다. 
{
	int n;
	int m;
}
public class Main {
	private static int N;
	private static int M;
	private static int[][] graph; //그래프 인접배열 
	private static int[][] distance;  //visited 대신 distance를 쓰면 된다. 결국 distance가 갱신되는게 visited랑 같기때문에. 
	private static Queue<pair> queue = new LinkedList<>(); //큐 
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s1;
		s1=br.readLine().split(" ");
		N =Integer.parseInt(s1[0]);
		M =Integer.parseInt(s1[1]);
		graph = new int[N+1][M+1];
		distance= new int[N+1][M+1];
		String s2;
		for(int i = 1 ; i<= N; i++) 
		{
			s2 = br.readLine();
			for(int j =1; j<=M; j++) {
				graph[i][j]=Integer.parseInt(s2.valueOf(s2.charAt(j-1)));
			}
		}
		BFS();
		System.out.println(distance[N][M]); //마지막 배열 N M 의 거리 출력 
	}
	
	private static void BFS() 
	{
		pair tmp= new pair();
		tmp.n=1;
		tmp.m=1;
		queue.offer(tmp); //큐에 넣어준다 .
		distance[1][1]=1; //시작점 1로 표시해준다. 
		while(!queue.isEmpty()) 
		{
			pair deq=queue.peek(); 
			queue.poll();
			int in, im;
			in = deq.n;
			im = deq.m;
			if(in!=0 && graph[in-1][im]==1 && distance[in-1][im]==0) {
				pair enq = new pair();
				enq.n=in-1;
				enq.m=im;
				queue.offer(enq);
				distance[in-1][im]=distance[in][im]+1; //자식노드의 distance = 부모노드의 distance + 1 
				if(in-1 == N && im ==M ) break; //마지막 노드가 들어갔다면 끝! 
			}
			if(in!=N && graph[in+1][im]==1 && distance[in+1][im]==0) {
				pair enq = new pair();
				enq.n=in+1;
				enq.m=im;
				queue.offer(enq);
				distance[in+1][im]=distance[in][im]+1;
				if(in+1 == N && im ==M ) break;
			}
			if(im!=0 && graph[in][im-1]==1 && distance[in][im-1]==0) {
				pair enq = new pair();
				enq.n=in;
				enq.m=im-1;
				queue.offer(enq);
				distance[in][im-1]=distance[in][im]+1;
				if(in == N && im-1 ==M ) break;
			}
			if(im!=M && graph[in][im+1]==1 && distance[in][im+1]==0) {
				pair enq = new pair();
				enq.n=in;
				enq.m=im+1;
				queue.offer(enq);
				distance[in][im+1]=distance[in][im]+1;
				if(in == N && im+1 ==M ) break;
			}
		}
	}	
}
