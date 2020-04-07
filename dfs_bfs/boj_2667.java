package boj2667;
import java.util.*;
import java.io.IOException;
import java.util.Arrays;

/*
 boj 2667 ������ȣ ���̱�
 dfs ���
*/
public class Main {
	private static int count=0;
	private static int [][]graph= new int[25][25];
	private static boolean[][]visited; //visted üũ  
	private static int index=0;
	private static int n;
	private static void dfs(int i,int j) {
		count++; //��� ���̸�ŭ 
		visited[i][j]=true;
		if(i!=0&& visited[i-1][j]!=true && graph[i-1][j]==1) {
			dfs(i-1,j);
		}
		if(i!=n-1&&visited[i+1][j]!=true && graph[i+1][j]==1)
			dfs(i+1,j);
		
		if(j!=0&&visited[i][j-1]!=true && graph[i][j-1]==1)
			dfs(i,j-1);
		if(j!=n-1&&visited[i][j+1]!=true && graph[i][j+1]==1)
			dfs(i,j+1);
		}
		
	public static void main(String[] args) throws IOException 
	{
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		graph= new int[n][n];
		visited = new boolean[n][n];
		int[] ans = new int[n*n];
		
		for(int i=0; i<n; i++){ //�� ���� �ޱ� 
            String input = in.next(); //������� �ԷµǹǷ� String���� ������ 
            for(int j=0; j<n; j++){
                graph[i][j] = input.charAt(j)-'0'; //char�� - '0' �ؼ� int�� �ֱ� 
            }
        }
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(graph[i][j]==1 && visited[i][j]==false) { 
					dfs(i,j);
					ans[index]=count; //����� �迭�� ���� 
					index++; //�迭�ε��� 
				 	count=0; //���� ������ ���� count�� 0���� 
				}
			}
		}
		
		Arrays.sort(ans); //���� 
		System.out.println(index); //�� ���� �� 
		for(int element : ans ) { 
			if(element!=0) {//���õǾ 0�� ��ҵ��� �տ� ä�������� 
			System.out.println(element);}
			
		}	
		
	}
}
