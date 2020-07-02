package boj1541;
import java.util.*;
import java.io.*;

/* boj 1541 �Ҿ���� ��ȣ (�׸���)
 * ���� : - �����ڸ� ������ �� �ٽ� -�����ڸ� ���������� ���ڸ� +���ְ�,
 * ���� ������ ���ڿ��ٰ�, ��ȣ�ļ� ���� ���ڸ� ���ش�.
 * +�����ڰ� ���������� �׳� +�� ���� ���ش�. 
 * */

class pair{
	int sum; //���� 
	int idx; //�ٲ� �ε��� 
	pair(int sum, int idx){
		this.sum=sum;
		this.idx=idx;
	}
}

public class Main {
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[]s;
		s=br.readLine().split("");
		pair p= parseToInt(0,s);//ù���ڸ� ���ڷ� �ٲپ �����Ѵ�. 
		int sum = p.sum; 
		int idx=p.idx; //ù��° ���� ������ �ε��� 
		
		
		for(int i = idx ; i < s.length;i++) { //ù��° �����ں��ͽ��� 
			if(s[i].equals("-")) { //�����ڰ�  - ���, ��ȣġ�� 
				pair p2 = operation(i+1,s);
				i = p2.idx;  // �ε������� 
				sum-=p2.sum; // �� ������ �����ڸ� -���� ���ش�.
			}
			else if(s[i].equals("+")) { //�����ڰ� +��� -�� ���ö����� �׳� �����ֱ� 
				pair p3 = operation(i+1,s);
				i=p3.idx;
				sum+=p3.sum;
				
			}
		}
		System.out.println(sum);
	}
	
	private static pair parseToInt(int idx, String[]s) { //��Ʈ���� ���ڷ� �ٲپ��ִ� �޼���
		String number="";
		while(idx<s.length && !s[idx].equals("+") && !s[idx].equals("-")) {
			number+=s[idx];
			idx++; 
		}
		pair p = new pair(Integer.parseInt(number),idx);
		return p;
	}
	
	
	private static pair operation(int idx, String[]s) { //��ȣ�ļ� �����ִ� �޼��� 
		int sum = 0;
		while(idx<s.length&&!s[idx].equals("-")) { 
			if(!s[idx].equals("+") && !s[idx].equals("-")) { //������ �� 
				pair tmp = parseToInt(idx,s); //�ϴ� ���ڷ� �ٲپ��ֱ� 
				sum+=tmp.sum; // �ٲ� ���� �����ֱ�
				idx=tmp.idx; //�ε��� ���� 
			}
			else { idx++; }
		}
		pair p = new pair(sum,idx-1);
		return p;
	}
	
}
