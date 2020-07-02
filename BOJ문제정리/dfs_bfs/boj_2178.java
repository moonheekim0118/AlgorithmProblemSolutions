package boj2178;
import java.util.*;
import java.io.*;

/* boj 2178 �̷�Ž�� 
 * BFS ���
 * BFS�� ���ؼ� �θ����� �Ÿ� + 1 �� �ϸ�, ���� ������ �Ÿ��� ���´�. 
 * �Է��� ��ٷο��� ����
 * �ڹٿ��� �ƹ��� split ���� �Է��� �޾Ƽ� ������ �Ľ��Ϸ���
 * ��Ʈ�� �迭�� �ƴ϶� ��Ʈ������ ���� �Ŀ�, charAt -> �ٽ� ��Ʈ������ ��ȯ�� �� parseInt �������
 * */

class pair //ť�� 2���� �迭 ������ �ֱ� ���� Ŭ������ ���� �����ߴ�. 
{
	int n;
	int m;
}
public class Main {
	private static int N;
	private static int M;
	private static int[][] graph; //�׷��� �����迭 
	private static int[][] distance;  //visited ��� distance�� ���� �ȴ�. �ᱹ distance�� ���ŵǴ°� visited�� ���⶧����. 
	private static Queue<pair> queue = new LinkedList<>(); //ť 
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
		System.out.println(distance[N][M]); //������ �迭 N M �� �Ÿ� ��� 
	}
	
	private static void BFS() 
	{
		pair tmp= new pair();
		tmp.n=1;
		tmp.m=1;
		queue.offer(tmp); //ť�� �־��ش� .
		distance[1][1]=1; //������ 1�� ǥ�����ش�. 
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
				distance[in-1][im]=distance[in][im]+1; //�ڽĳ���� distance = �θ����� distance + 1 
				if(in-1 == N && im ==M ) break; //������ ��尡 ���ٸ� ��! 
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
