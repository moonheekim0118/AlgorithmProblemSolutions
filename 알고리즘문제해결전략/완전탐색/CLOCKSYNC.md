# 시계 맞추기 CLOCKSYNC 난이도 중
[문제로 가기](https://www.algospot.com/judge/problem/read/CLOCKSYNC)

- 해결 X
- 완전탐색 문제
- 16개의 시계가 각각 9개의 스위치를 누르는 모든 경우를 구현했지만, 스택 오버 플로
- 왜냐하면 , 각 스위치를 누르는 횟수에 제한이 없었기 때문

### 앞으로 주의해야 할 점
- 경우가 무한대일 때.. 즉 문제 자체에 '몇번 해라!' 혹은 '몇개만 골라라!'라는 제한이 없는 경우는 그대로 구현하면 스택오버플로가 난다.
- 따라서 이럴 때는 제한을 나 스스로가 찾아야 한다.
- 또한 문제에서 경우를 선택하는 **순서** 는 상관 없을 수 있음을 유의하라. **즉 마지막 경우부터 타고 올라와도 될 수 있다.**

### 책의 풀이 
- 스위치를 4번 누르면 시계는 원래 상태로 돌아온다. 따라서 하나의 스위치를 누를 수 있는 최대 수는 3번이다.
- 이를 이용해서 1번 스위치를 0번 누를때 1번 누를때 2번 누를 때 3번 누를 때마다 경우를 만들어주도록 한다.
- 현재 스위치의 번호를 함수의 인자로 넘겨줌으로써, 모든 스위치를 다 눌렀는지 확인 할 수 있다.
- 모든 스위치를 다 눌렀으면, 모든 시계가 12시를 가리키고 있는지 판단한다.
- 모든 시계가 12시를 가리키고 있다면 0을 반환하고, 아니면 INF의 수를 반환한다. 
- 여기서 0을 반환하는 이유는 호출된 재귀함수 값에 현재 스위치를 누른 번호를 더해주고 ,그것을 다시 반환하면 되기 때문이다.
- 스위치를 누르는 경우가 3가지 이기 때문에 이렇게 처리하도록 한다. 

```java
int solve(int[]clocks , int switch_num){
    if(switch_num == 10) return allAligned(clocks) ? 0 : INF;
    int ret=INF:
    for(int i =0; i<4 ; i++ ){
        ret=Math.min(ret, i+solve(clocks, switch_num+1));
        clocks=rotationClock(clocks, swtich_num); // 시계 돌려주기 
        // 총 네번 호출하므로 네번째에는 원래상태로 되돌아가서 다음 경우를 정상적으로 셀 수 있도록 한다.
    }
    return ret;
}
```

- 나의 스택 오버플로 난 풀이 .. 지금 보면 말도안된다 
```java
 private static int solution(int[]clock){
        if(checking(clock)) return 1;
        int min = Integer.MAX_VALUE;
        for(int i =0; i<10; i++){ // 모든 10개의 버튼을 눌러본다..
            pushCnt[i]++; 
            int[]rotatedClock = rotation(clock, i);
            int n = solution(rotatedClock);
            if(n < Integer.MAX_VALUE) n++;
            min = Math.min(min,n);
        }
        return min;
    }
```


