#include <iostream>
using namespace std;
int p[101][100001];
/*
boj 12865 평범한 배낭
점화식 사용 - 냅색 문제 
*/
int max(int n, int m) {
	if (n > m) return n;
	else return m;
}
int Knapsack_Problem(int wt[], int val[] ,int n, int W) {
	for (int i = 0; i <= n; i++) {
		for (int w = 0; w <= W; w++) {
			if (i == 0 || w == 0) p[i][w] = 0; //첫번째 행/렬은 모두 0으로 채워준다 
			else if (wt[i - 1] <= w) { //넣을 수 있다면 이전 최적해와 , 현재 최적해 구해서 비교 
				p[i][w] = max(val[i - 1] + p[i - 1][w - wt[i - 1]], p[i - 1][w]);
			}
			else { //넣을 수 없다면 이전 최적해 복사 
				p[i][w] = p[i - 1][w];
			}
		}
	}
	return p[n][W];
}


int main(void) {
	int n, k;
	int val[100];
	int wt[100];
	cin >> n;
	cin >> k;
	for (int i = 0; i < n; i++) {
		cin >> wt[i];
		cin >> val[i];
	}
	cout << Knapsack_Problem(wt, val, n, k);

}