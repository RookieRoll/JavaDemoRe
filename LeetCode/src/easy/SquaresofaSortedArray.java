package easy;

public class SquaresofaSortedArray {
	public int[] sortedSquares(int[] A) {
		int[] array=new int[A.length];
		int begin=0;
		int end=A.length-1;
		for(int i=A.length-1;i>=0;i--){
			array[i]= (int) (Math.pow(A[begin],2)>Math.pow(A[end],2)?Math.pow(A[begin++],2):Math.pow(A[end--],2));
		}
		return array;
	}
}
