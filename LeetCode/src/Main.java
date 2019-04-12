import entity.ListNode;

public class Main {

    public static void main(String[] args) {
        String[] ops=new String[]{"5","-2","4","C","D","9","+","+"};
        BaseballGame baseballGame=new BaseballGame();
        System.out.println(baseballGame.calPoints(ops));
    }
}
