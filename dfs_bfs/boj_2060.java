package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
/*
 boj ���̷���
 bfs ����ؼ� bfs�� Ž�� ��� ++
  */

public class Main {
	static int v,e,n;
	static boolean[]visited;
	static ArrayList<ArrayList<Integer>> list;
	static Queue<Integer> que;
	static int comp;
	public static void bfs() {
		que.offer(1); //1������ ����
		visited[1]=true;
		comp++;
		int tmp;
		while(!que.isEmpty()) {
			tmp=que.peek();
			que.poll();
			for(int i: list.get(tmp)) {
				if(!visited[i]) {
					que.offer(i);
					visited[i]=true;
					comp++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();//��� ����
		e = in.nextInt();  // �������� 
		comp=0; 
		list = new ArrayList<>(); //����Ʈ ����
		que = new LinkedList<>(); //ť ����
		visited = new boolean[n+1]; //visited�迭 ����
		for (int i=0; i<=n; i++) 
		{
			list.add(new ArrayList<>()); //����Ʈ ����-�ʱ�ȭ 
		}
		for (int i=0; i<e; i++) { //�����Է�
			int t= in.nextInt();
			int f =in.nextInt();
			list.get(f).add(t); //����� 
			list.get(t).add(f);
		}
		
		bfs();
		System.out.println(comp-1);
		in.close();
	}
}

