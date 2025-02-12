package easy;

/**
 * A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.
 * <p>
 * The bus goes along both directions i.e. clockwise and counterclockwise.
 * <p>
 * Return the shortest distance between the given start and destination stops.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: distance = [1,2,3,4], start = 0, destination = 1
 * Output: 1
 * Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: distance = [1,2,3,4], start = 0, destination = 2
 * Output: 3
 * Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: distance = [1,2,3,4], start = 0, destination = 3
 * Output: 4
 * Explanation: Distance between 0 and 3 is 6 or 4, minimum is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^4
 * distance.length == n
 * 0 <= start, destination < n
 * 0 <= distance[i] <= 10^4
 */
public class DistanceBetweenBusStops1184 {
    public static void main(String[] args) {
        int[] distance = {1, 2, 3, 4};
        DistanceBetweenBusStops1184 busStop = new DistanceBetweenBusStops1184();
        System.out.println(busStop.distanceBetweenBusStops(distance, 0, 3));
    }

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {

        int sum = 0;
        int index = Math.min(start, destination);
        int endMax = Math.max(start, destination);
        int subSum = 0;
        for (int i = 0; i < distance.length; i++) {
            sum += distance[i];
            if (i >= index && i < endMax) {
                subSum += distance[i];
            }
        }
        return Math.min(subSum, sum - subSum);
    }
}
