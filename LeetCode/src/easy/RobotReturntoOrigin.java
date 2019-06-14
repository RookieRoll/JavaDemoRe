package easy;

public class RobotReturntoOrigin {
	public boolean judgeCircle(String moves) {
		int upDown = 0;
		int leftRight = 0;
		for (char cha : moves.toCharArray()) {
			switch (cha){
				case 'L':leftRight-=1;break;
				case  'R':leftRight+=1;break;
				case 'U':upDown+=1;break;
				case 'D':upDown-=1;break;
			}
		}
		return upDown==0&&leftRight==0;
	}
}
