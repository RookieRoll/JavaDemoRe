package easy;

import entity.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

	public static List<Interval> merge(List<Interval> intervals) {
		List<Interval> ans = new ArrayList<>();
		if (intervals == null) {
			return ans;
		}

		intervals.sort(Comparator.comparing(i -> i.getStart()));  //lambda 匿名函数：输入i  返回i.start

		Interval last = null;
		for (Interval item : intervals) {
			if (last == null || last.getEnd() < item.getStart()) {
				ans.add(item);
				last = item;
			} else {
				last.setEnd(Math.max(last.getEnd(), item.getEnd()));  // Modify the element already in list
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		//先根据start进行排序
		var list = new ArrayList<Interval>();
		list.add(new Interval(1, 3));
		list.add(new Interval(2, 6));
		list.add(new Interval(6, 10));
		list.add(new Interval(15, 18));
		var result = merge(list);
		result.forEach(m -> {
			System.out.println("[" + m.getStart() + "," + m.getEnd() + "]");
		});

	}
}
