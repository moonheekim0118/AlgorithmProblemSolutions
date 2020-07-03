# 게임판 덮기 BOARDCOVER 난이도 하
[문제로 가기](https://www.algospot.com/judge/problem/read/BOARDCOVER)

- 완전탐색 문제 중 경우의 수를 찾는 문제에서는 중복을 막기 위해, 가장 먼저 오는 경우를 기준으로 잡고 그에 맞춰 경우들을 탐색한다.
- 해당 문제의 경우 가장 왼쪽 - 위쪽에 오는 하얀 블록을 기준으로 잡고 해당 블록의 방향에 따라 뒤에 올 수 있는 블록의 경우를 센다.
- 선택된 블록을 boolean 배열에 check해줌으로써, 현재 덮어진 블록과 사용가능한 블록을 구분해준다.
- 이 문제는 소풍문제와 달리 '모든 블록'을 사용해야 하므로, 맨 첫 기준점을 옮기지 않아도 된다.

### 내가 푼 방법
1. 주어진 하얀 블록의 수가 3의 배수가 아니라면 0을 반환하고, 3의 배수이면 계속한다.
2. solution 함수를 통해서 가장 왼쪽 위에 있는 아직 덮어지지 않은 블록을 찾는다.
3. 해당 블록을 기준으로 총 8가지의 블록 모양을 만들 수 있는지 살펴보고, 게임판의 범위와 아직 덮여지지 않았는지의 여부를 확인한다.
4. 블록을 덮을 수 있는 경우, 해당 블록을 덮은 상태의 게임판으로 solution 함수를 실행한다.
5. 기저케이스는 모든 하얀 블록들이 덮여진 경우로, 기저케이스에 오면 1을 반환한다. (하나의 경우가 생겼으므로)
6. 재귀함수가 끝나면 덮었던 블록을 다시 열어두어서 , 해당 블록들이 다른 경우에 사용될 수 있도록 해준다. 

```java
public class Main {
    private static String[][]grid;
    private static boolean[][]visited;
    private static int w;
    private static int h;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        int [] answer = new int[tc];
        int idxTc=0;
        for(int i=0; i< tc ; i++){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            grid = new String[w][h];
            visited=new boolean[w][h];
            int cnt = 0;
            for(int j = 0; j < w;  j++){
                grid[j]=br.readLine().split("");
                for(int k =0; k < h; k++){
                    if(grid[j][k].equals(".")) cnt ++;
                }
            }
            if(cnt%3!=0){
                answer[idxTc++]=0;
            }
            else{
                answer[idxTc++]=solution();

            }
        }
        for(int i=0; i<tc; i++){
            System.out.println(answer[i]);
        }
    }

    private static int solution(){
        int firstfreeW=-1;
        int firstfreeH=-1;
        boolean flag = false;
        for(int i = 0 ; i < w; i ++){
            for(int j = 0 ; j < h; j ++){
                if(grid[i][j].equals(".") && visited[i][j]==false) {
                    firstfreeW = i;
                    firstfreeH = j;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        int sum = 0 ;
        if(firstfreeH==-1 && firstfreeW==-1) return 1;
        // 왼쪽
        // 범위 검사 해주어야한다!!
        if(firstfreeH>0) {
            if (grid[firstfreeW][firstfreeH - 1].equals(".") && visited[firstfreeW][firstfreeH - 1] == false) {
                if (firstfreeW < w-1 && grid[firstfreeW + 1][firstfreeH].equals(".") && visited[firstfreeW + 1][firstfreeH] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH] = true;
                    visited[firstfreeW][firstfreeH - 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH] = false;
                    visited[firstfreeW][firstfreeH - 1] = false;
                } // * *
                //     *
                if (firstfreeW < w-1 && grid[firstfreeW + 1][firstfreeH - 1].equals(".") && visited[firstfreeW + 1][firstfreeH - 1] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH - 1] = true;
                    visited[firstfreeW][firstfreeH - 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH - 1] = false;
                    visited[firstfreeW][firstfreeH - 1] = false;
                    // * *
                    // *
                }
            }
        }
        //오른쪽
        if(firstfreeH < h-1){
        if(grid[firstfreeW][firstfreeH+1].equals(".") && visited[firstfreeW][firstfreeH+1]==false){
            if(firstfreeW<w-1 && grid[firstfreeW+1][firstfreeH].equals(".")&&visited[firstfreeW+1][firstfreeH]==false){
                visited[firstfreeW][firstfreeH]=true;
                visited[firstfreeW+1][firstfreeH]=true;
                visited[firstfreeW][firstfreeH+1]=true;
                sum+=solution();
                visited[firstfreeW][firstfreeH]=false;
                visited[firstfreeW+1][firstfreeH]=false;
                visited[firstfreeW][firstfreeH+1]=false;
            } // * *
            //   *
            if(firstfreeW<w-1&& grid[firstfreeW+1][firstfreeH+1].equals(".")&&visited[firstfreeW+1][firstfreeH+1]==false){
                visited[firstfreeW][firstfreeH]=true;
                visited[firstfreeW+1][firstfreeH+1]=true;
                visited[firstfreeW][firstfreeH+1]=true;
                sum+=solution();
                visited[firstfreeW][firstfreeH]=false;
                visited[firstfreeW+1][firstfreeH+1]=false;
                visited[firstfreeW][firstfreeH+1]=false;
                // * *
                //   *
            }
        }
        }
        //위
        if(firstfreeW>0) {
            if (grid[firstfreeW - 1][firstfreeH].equals(".") && visited[firstfreeW - 1][firstfreeH] == false) {
                if (firstfreeH < h-1 && grid[firstfreeW - 1][firstfreeH + 1].equals(".") && visited[firstfreeW - 1][firstfreeH + 1] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW - 1][firstfreeH] = true;
                    visited[firstfreeW - 1][firstfreeH + 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW - 1][firstfreeH] = false;
                    visited[firstfreeW - 1][firstfreeH + 1] = false;
                } // * *
                //   *
                if (firstfreeH > 0 && grid[firstfreeW - 1][firstfreeH - 1].equals(".") && visited[firstfreeW - 1][firstfreeH - 1] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW - 1][firstfreeH] = true;
                    visited[firstfreeW - 1][firstfreeH - 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW - 1][firstfreeH] = false;
                    visited[firstfreeW - 1][firstfreeH - 1] = false;
                    // * *
                    //   *
                }
            }
        }
        //아래
        if(firstfreeW < w-1) {
            if (grid[firstfreeW + 1][firstfreeH].equals(".") && visited[firstfreeW + 1][firstfreeH] == false) {
                if (firstfreeH > 0 && grid[firstfreeW + 1][firstfreeH - 1].equals(".") && visited[firstfreeW + 1][firstfreeH - 1] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH - 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH - 1] = false;
                } // * *
                //   *
                if (firstfreeH < h-1 && grid[firstfreeW + 1][firstfreeH + 1].equals(".") && visited[firstfreeW + 1][firstfreeH + 1] == false) {
                    visited[firstfreeW][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH] = true;
                    visited[firstfreeW + 1][firstfreeH + 1] = true;
                    sum += solution();
                    visited[firstfreeW][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH] = false;
                    visited[firstfreeW + 1][firstfreeH + 1] = false;
                    // * *
                    //   *
                }
            }
        }

        return sum;
    }
}
```

#### 고쳐야 할 점 
* 반복되는 동작이 너무 많다. -> 블록의 모양을 잡아주는 if문이나, 블록을 덮었다가 다시 열어두는 true,false의 경우가 그러하다.

### 책에나온 해법
1. 블록의 경우인 4가지 타입, 그리고 해당 타입마다 3개 블록의 위치를 배열에 저장해둔다. 이로써 하나씩 접근할 필요 없이 1타입부터 4타입까지 구해진 firstfreeW와 fristfreeH값을 넣어봄으로써 어떤 블록이 생성 가능한지 체크해준다.  

```java
    static int coverType[][][]={ // 블록을 구성하는 세 칸의 위치
            {{0,0},{1,0},{0,1}}, // type 1
            {{0, 0}, {0, 1}, {1, 1}}, // type2
            {{0,0},{1,0},{1,1}}, //type3
            {{0,0},{1,0},{1,-1}} //type4
    };

```
2. 블록 범위 및 생성가능을 체크해 줄 때, 만약 생성 가능하다면 블록을 덮었다고 표시까지 해준다.

```java
private static boolean set(int y, int x , int type, int delta){
        boolean ok = true;
        for(int i=0; i< 3; i++){
            int ny = y + coverType[type][i][0];
            int nx= x+ coverType[type][i][1];
            if(ny <0 || ny >= w || nx < 0 || nx >=h ) // 블록 하나라도 범위 넘어가면 
                ok=false; //생성불가 
            else if(!grid[ny][nx].equals(".")) // 블록 하나라도 검은색 판이라면 
                ok=false; //생성불가 
            else if((board[ny][nx]+=delta) > 1) // 이미 블록이 덮어졌을 경우 ==> 블록을 다시 열어준다. 
                                                // 블록이 덮어지지 않았을 경우 ==> 블록을 덮어준다.
                                                // +=delta를 해줌으로써 
                ok=false; //생성불가 
        }
        return ok;
    }
```

3. 이를 위해 왼쪽 가장 위에 안덮여진 하얀 블록을 찾고, 해당 블록의 위치인 y x를 기준으로 다음 블록들의 위치 경우를 찾는다.

```java
private static int cover(){
        int y = -1;
        int x= -1;
        for(int i = 0 ; i< w; i++){
            for(int j =0; j< h; j++){
                if(board[i][j]==0 && grid[i][j].equals(".")){
                    y=i;
                    x=j;
                    break;
                }
            }
            if(y!=-1) break;
        }
        if(y==-1) return 1; //기저
        int ret=0;
        for(int type=0; type<4; type++){
            if(set(y,x,type,1)) // 각 타입마다 블록이 덮어있는지/ 생성가능한지 확인 , 생성 가능해서 열려있다면 delta값으로 덮어두기
                ret+=cover(); // 재귀
            set(y,x,type,-1); // 덮어두었던 블록 다시 열어두기--> delta 값으로 
        }
    }
```

4. board[y][x] > 1 이라면 블록이 덮여진 경우이다.  따라서 블록을 덮을 때에는 +1 을 해주도록 하고, 블록을 열 때에는 -1 을 해주어 board[y][x]를 0으로 만든다.



+ 앞으로 여러개의 위치들을 탐색해야 할 때에는, 배열을 선언해서 반복문으로 코드 효율성을 높여주도록 한다.
+ 여러번 반복되는 코드는 함께 반복 될 수 있는 함수에 포함시켜주면 좋다.
+ 소풍문제와 거의 흡사했고 다른점은, 모든 블록을 사용해야한다는 점과 2차원배열로 접근해야한다는 것이었다. 따라서 해법은 30분 내로 찾았고 구현은 1시간 내외로 할 수 있었다.  