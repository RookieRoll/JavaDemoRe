package easy;

public class Atoi {
	public int myAtoi(String str) {
		String temp = str.trim();
		String tempResult = GetNum(temp);
		return ConvertToInt(tempResult);
	}

	public String GetNum(String str) {
		if (str.isEmpty())
			return "0";

		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				result += str.charAt(i);
			} else if ((str.charAt(i) == '-' || str.charAt(i) == '+') && i == 0) {
				result += str.charAt(i);
			} else {
				if (i == 0)
					return "0";
				return result;
			}
		}
		return str;
	}

	public int ConvertToInt(String str) {
		try {
			if (str.length() == 1 && (str.charAt(0) == '+' | str.charAt(0) == '-'))
				return 0;
			return Integer.parseInt(str);
		} catch (Exception ex) {
			if (str.charAt(0) == '-')
				return Integer.MIN_VALUE;
			else
				return Integer.MAX_VALUE;
		}
	}
}
