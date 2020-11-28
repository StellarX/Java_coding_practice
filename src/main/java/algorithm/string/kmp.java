package algorithm.string;

public class kmp {

	public static void main(String[] args) {
		String str = new String("abababca");
		String substr = new String("abc");
		int[] next = getNext(substr);
//		System.out.println(Arrays.toString(next));
		int ans = implKmp(str, substr, next);

		System.out.println(ans);
	}
	public static int implKmp(String str, String substr, int[] next) {
		int i = 0, j = 0;
		while(i < str.length() && j < substr.length()) {
			if(j == -1 || str.charAt(i) == substr.charAt(j)) {
				i++;
				j++;
			}
			else j = next[j];
		}
		if(j == substr.length()) return i - j;
		else return -1;
	}
	public static int[] getNext(String substr) {
		int[] next = new int[substr.length()+1];
		next[0] = -1;
		int i = 0, j = -1;
		while(i < substr.length()) {
			if(j == -1 || substr.charAt(i) == substr.charAt(j)) {
				i++;
				j++;
				next[i] = j;
			}
			else j = next[j];
		}
		return next;
	}
}
