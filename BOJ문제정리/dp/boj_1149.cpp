#include <iostream>
/*
boj 1149 RGB °Å¸®
*/
using namespace std;
int dp[1001][3] = { 0 };
int solve[1001][3] = { 0 };
int get_min(int n, int m) {
	if (n < m) return n;
	else return m;
}

int rgb(int n) {
	for (int i = 0; i < 3; i++)
		solve[0][i] = dp[0][i];
	for (int i = 1; i < n; i++) {
		solve[i][0] = dp[i][0] + get_min(solve[i - 1][1], solve[i - 1][2]);
		solve[i][1] = dp[i][1] + get_min(solve[i - 1][0], solve[i - 1][2]);
		solve[i][2] = dp[i][2] + get_min(solve[i - 1][0], solve[i - 1][1]);
	}
	return get_min(get_min(solve[n - 1][0], solve[n - 1][1]), solve[n - 1][2]);

}
int main(void) {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> dp[i][0];
		cin >> dp[i][1];
		cin >> dp[i][2];
	}
	cout << rgb(n) << endl;
}