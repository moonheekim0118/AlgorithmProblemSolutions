#include <iostream>
using namespace std;
int value[3] = { 6,3,5 };
int weight[3] = { 3,4,5 };
int knapsack[3][13];
/*
knapsack problem without recurrence 
��ȭ�� ���� Ǭ ���� ���� 
*/

int max(int n, int m) {
	if (n > m) return n;
	else return m;
}
void knapsack_solve() {
	int max_val=0;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j <= 12; j++) {
			if (weight[i] > j) {
				if (i != 0) { knapsack[i][j] = knapsack[i - 1][j]; }
				else { knapsack[i][j] = 0; }
			}
			else {
				if (i == 0) knapsack[i][j] = value[i]; //��� 
				
				else if (i == 1) {  //���̾� 
					if (weight[i] + weight[i - 1] <= j) { knapsack[i][j] = max(value[i] + value[i - 1], knapsack[i - 1][j]); }
					else knapsack[i][j] = knapsack[i - 1][j];
				}
				else { //�����̾� 
					if (weight[i] + weight[i - 1] + weight[i - 2] <= j) { //���+���̾�+�����̾� ���� 
						max_val = value[i] + value[i - 1] + value[i - 2];
					}
					else if (weight[i] + weight[i - 1] <= j && weight[i] + weight[i - 2] <= j) { //���+�����̾� / ���̾�+�����̾� ���� 
						max_val = max(value[i] + value[i - 1], value[i] + value[i - 2]);
					}
					else if(weight[i]+weight[i-1]<= j && weight[i]+weight[i-2]>j) //���̾�+�����̾� ���� 
						max_val = value[i] + value[i - 1];

					else if (weight[i]+weight[i-2]<=j && weight[i]+weight[i-1]>j) //��� + �����̾� ���� 
						max_val = value[i] + value[i - 2];
					cout << max_val << endl;
					knapsack[i][j] = max(max_val, knapsack[i - 1][j]);
				}
			}

		}
	}
}

int main(void) {
	knapsack_solve();
	cout << "����" << endl;
	for (int i = 0; i < 13; i++) {
		cout << i << " ";
	}
	cout << endl;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 13; j++) {
			cout <<knapsack[i][j]<<" ";
		}
		if (i == 0) cout << "-���";
		if (i == 1) cout << "-���̾�";
		if (i == 2) cout << "-�����̾�";
		cout << endl;
	}
}