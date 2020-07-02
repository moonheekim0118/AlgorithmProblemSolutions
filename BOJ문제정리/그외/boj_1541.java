package boj1541;
import java.util.*;
import java.io.*;

/* boj 1541 잃어버린 괄호 (그리디)
 * 로직 : - 연산자를 만나면 또 다시 -연산자를 만날때까지 숫자를 +해주고,
 * 현재 구해진 숫자에다가, 괄호쳐서 더한 숫자를 빼준다.
 * +연산자가 먼저나오면 그냥 +를 먼저 해준다. 
 * */

class pair{
	int sum; //숫자 
	int idx; //바뀐 인덱스 
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
		pair p= parseToInt(0,s);//첫숫자를 숫자로 바꾸어서 저장한다. 
		int sum = p.sum; 
		int idx=p.idx; //첫번째 숫자 이후의 인덱스 
		
		
		for(int i = idx ; i < s.length;i++) { //첫번째 연산자부터시작 
			if(s[i].equals("-")) { //연산자가  - 라면, 괄호치기 
				pair p2 = operation(i+1,s);
				i = p2.idx;  // 인덱스변경 
				sum-=p2.sum; // 다 더해준 연산자를 -에서 빼준다.
			}
			else if(s[i].equals("+")) { //연산자가 +라면 -가 나올때까지 그냥 더해주기 
				pair p3 = operation(i+1,s);
				i=p3.idx;
				sum+=p3.sum;
				
			}
		}
		System.out.println(sum);
	}
	
	private static pair parseToInt(int idx, String[]s) { //스트링을 숫자로 바꾸어주는 메서드
		String number="";
		while(idx<s.length && !s[idx].equals("+") && !s[idx].equals("-")) {
			number+=s[idx];
			idx++; 
		}
		pair p = new pair(Integer.parseInt(number),idx);
		return p;
	}
	
	
	private static pair operation(int idx, String[]s) { //괄호쳐서 더해주는 메서드 
		int sum = 0;
		while(idx<s.length&&!s[idx].equals("-")) { 
			if(!s[idx].equals("+") && !s[idx].equals("-")) { //숫자일 때 
				pair tmp = parseToInt(idx,s); //일단 숫자로 바꾸어주기 
				sum+=tmp.sum; // 바꾼 숫자 더해주기
				idx=tmp.idx; //인덱스 변경 
			}
			else { idx++; }
		}
		pair p = new pair(sum,idx-1);
		return p;
	}
	
}
