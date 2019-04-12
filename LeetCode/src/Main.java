import entity.ListNode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String A = "this apple is sweet", B = "this apple is sour";
        UncommonWordsfromTwoSentences uncommonWordsfromTwoSentences=new UncommonWordsfromTwoSentences();
        var arr=uncommonWordsfromTwoSentences.uncommonFromSentences(A,B);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
