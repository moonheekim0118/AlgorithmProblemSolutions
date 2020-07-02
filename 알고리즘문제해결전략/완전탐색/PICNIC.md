# 소풍 PICNIC 문제 난이도 하 
[문제로 가기](https://www.algospot.com/judge/problem/read/PICNIC)

- (1,0) (2,1)과 (0,1)(2,3)은 같은 조합으로 쳐서 카운트 하지 않는다.
- 조합의 중복을 없애기 위해서는 가장 먼저 있는 수를 고른 후, 그 뒤의 인덱스들로 조합을 구성하면 된다. 즉, 같은 답 중에서 사전순으로 가장 먼저 오는 답 하나만을 세는 것이다. 위의 예제로 치면 (1,0)(2,3)이나 (2,3)(0,1)은 세지 않고, (0,1)(2,3) 만 세도록 한다.

### 내가 푼 방법
1. 짝의 정보가 주어지면 2차원 배열에 서로 짝인 학생의 정보를 저장한다.
2. 반복문으로 첫번째 짝을 일단 골라 놓은 후, 첫번째 짝을 선택했을 시 고를 수 있는 나머지 짝의 경우를 재귀함수로 센다. 이 때 첫번째 짝 이후의 짝들 내에서만 경우의 수를 세도록 한다. 
3. 해당 재귀함수의 기저케이스는 '현재까지 고른 인원의 수 == 모든 인원의 수' 일 때이다. 이것을 만족할 시 1 을 반환한다. (경우1을 만족하므로)
4. 해당 재귀함수는 짝이 저장된 배열을 살펴 보면서, 짝 둘다 checked가 false이면 선택하고 아니면 건너뛰도록 한다. 선택시 true로 바꾸어서 중복이 일어나지 않게 한다.
5. 재귀함수가 종료되면 true를 false로 변경시켜줌으로써 해당 학생을 이용하여 또 다른 경우를 만들 수 있도록 한다.

```java
public class Main {
    static int[][]pair;
    static boolean[]checked;
    static int[]ans;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        int idxTx=0;
        ans=new int[tc];
        for(int i =0 ; i < tc; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 학생 수
            int m = Integer.parseInt(st.nextToken()); //친구 쌍
            pair= new int[m][2];
            checked=new boolean[n];
            int sum =0;
            st = new StringTokenizer(br.readLine());
            for(int j =0; j< m; j++){
                pair[j][0] = Integer.parseInt(st.nextToken());
                pair[j][1] = Integer.parseInt(st.nextToken());
            }
            for(int k=0 ; k< m; k++){
                checked[pair[k][0]]=true;
                checked[pair[k][1]]=true;
                sum+=solution(n,m,2,k);
                checked[pair[k][0]]=false;
                checked[pair[k][1]]=false;
            }
            ans[idxTx++]=sum;
        }
        for(int i= 0; i< tc; i++)
            System.out.println(ans[i]);
    }

    private static void show(int friendNum){
        for(int i =0; i<friendNum; i++){
            if(checked[i]){
                System.out.println(i);
            }
        }
    }
    private static int solution(int friendNum, int pairNum, int cnt, int idx){
        if(cnt == friendNum) { return 1;}
        int sum = 0 ;
        for(int i = idx +1 ; i< pairNum;i++){
            if(!checked[pair[i][0]] && !checked[pair[i][1]]){
                checked[pair[i][0]]=true;
                checked[pair[i][1]]=true;
                sum+=solution(friendNum,pairNum,cnt+2,i);
                checked[pair[i][0]]=false;
                checked[pair[i][1]]=false;
            }
        }
        return sum;
    }
}
```

### 책에 나온 해법
1. 해당 학생이 골라졌는지 판별해주는 boolean 배열을 만든다. 두 학생이 서로 짝 관계인지 아닌지를 판별해주는 boolean 2차원 배열을 만든다.
2. 학생들 목록을 살펴보며 남은 학생들 중에 가장 번호가 빠른 학생 N 을 찾도록 한다.
3. 기저케이스는 모든 학생이 짝을 찾은 경우이다. 이 때 1을 반환해준다.
4. 학생 N 과 짝 지을 학생 M 을 찾는데, 찾을 때 인덱스를 현재 학생N의 뒤에서부터 본다. 
5. 학생 M이 아직 checked되지 않았고 학생 N과 짝이라면 재귀함수를 호출하여 경우의 수를 더해준다.
6. 이 후의 학생N과 M을 이용해 다시 다른 경우를 만들 수 있도록 둘을 false로 해준다.