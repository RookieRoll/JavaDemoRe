/**
 * 计算两个数字之间所有数字的与运算值
 */
public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) {
            right = right & (right - 1);
        }
        return right;
    }
}
