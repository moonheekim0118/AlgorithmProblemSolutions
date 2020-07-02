package timemachine;
import java.io.*;
import java.util.*;

/* boj 11657 Ÿ�Ӹӽ�
 * �������� �˰��� ���
 * �����÷� ������ ����� ���� ����Ŭ ������ �ȵ� �� �����Ƿ� �Ÿ� ���� �迭 Ÿ���� long
 * */

class Graph //edge ���� ��ü�迭�� ���� 
{
	Graph(int src, int dest ,int weight){
		this.src=src;
		this.dest=dest;
		this.weight=weight;
	}
	int src; //from
	int dest; //to 
	int weight;  //����ġ 
}


public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static int N; //���� ���� 
	private static int M; //���� �뼱 ���� 
	private static Graph[] edge; //���� ���� 
	private static long[] dist; //��� 1�κ��� ������ ��� ������ �ִܰ�� ����
   public static void main(String[] args) throws IOException{		
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer stk = new StringTokenizer(br.readLine());
	   N = Integer.parseInt(stk.nextToken());
	   M = Integer.parseInt(stk.nextToken());
		dist = new long[N+1]; //�ʱ�ȭ 
		edge = new Graph[M]; //�ʱ�ȭ 
		for(int i=0; i<M;i++) { //���� �Է� 
			stk = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(stk.nextToken());
			int dest = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());
			edge[i]=new Graph(src,dest,weight);
		}
		Arrays.fill(dist, INF); //�ʱ�ȭ 
		dist[1]=0; //��� ��� �ʱ�ȭ 
		if(bellmanFord()==false) { //���� ����Ŭ ���� 
			System.out.println("-1");
		}
		else { //���� ����Ŭ ���� 
			for(int i = 2; i<=N; i++) {
				if(dist[i]==INF) {
					System.out.println("-1"); //���� �Ǿ� ���� ���� 
				}
				else {
					System.out.println(dist[i]); //�ִܰ�� ��� 
				}
			}
		}
	}
	
	
	public static boolean bellmanFord() {
		for(int i = 1; i<=N-1; i++) { // ���� �� -1 
			for(int j=0;j<M; j++) { //������ M 
				int u =edge[j].src; //from
				int v=edge[j].dest; //to 
				int w=edge[j].weight; //����ġ
				//dist[u]�� �������� && dist[v]�� dist[u] + v�� ����ġ���� ũ�� 
				if(dist[u]!=INF && dist[v] > dist[u]+w) {
					dist[v] = dist[u]+w; //���� 
				}
			 }
			} 
		
		for(int j=0; j<M; j++) { //��������Ŭ �ٽ��ѹ� Ȯ�� 	
			int u = edge[j].src; //from
			int v= edge[j].dest; //to 
			int w = edge[j].weight; //����ġ 
			if( dist[u]<INF && dist[v] > dist[u]+w) {
				return false; //���� �� ���� ���� �� �ִٸ� ��� ���ϴ� ��������Ŭ�� �ִٴ� ��. ���� false ���� 
			}
		}
		return true; //��������Ŭ ���� 
		
	}
}
