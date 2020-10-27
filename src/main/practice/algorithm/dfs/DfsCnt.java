package algorithm.dfs;

public class DfsCnt {
	static int row=5, column=5;
	static char[][] arr = new char[][] {
		{'@','*','*','*','@'},
		{'*','*','@','*','@'},
		{'*','*','*','*','@'},
		{'@','@','*','*','@'},
		{'@','@','*','*','@'},
	};
	static int[][] cnt = new int[row][column];
	public static void main(String[] args) {	
		
	}	
	public static void dfs(int x, int y, int idx) {
		
	}
 
}


//answer:
//public static void main(String[] args) {	
//	int idx = 0;
//	for(int i = 0; i < row; i++) 
//		for(int j = 0; j < column; j++) 
//			if(arr[i][j] == '@' && cnt[i][j] == 0)
//				algorithm.dfs(i, j, ++idx);
//	System.out.print(idx);
//}
//public static void algorithm.dfs(int x, int y, int idx) {
//	if(x < 0 || y < 0 || x >= row || y >= column) return ;
//	if(arr[x][y] == '*' || cnt[x][y] != 0) return;
//	cnt[x][y] = idx;
//	for(int dx = -1; dx <= 1; dx++) 
//		for(int dy = -1; dy <= 1; dy++) 
//			algorithm.dfs(x+dx, y+dy, idx);
//}