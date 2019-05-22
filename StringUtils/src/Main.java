import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		List<Student> students = new ArrayList<>();
		Student student1 = new Student("zhangsan", 23);
		Student student2 = new Student("23", 23);
		Student student3 = new Student("34", 23);
		Student student4 = new Student("45", 23);
		Student student5 = new Student("56", 23);
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		var result = SqlUtil.ConvertToSqlUpdate(student1);
		System.out.println(result);
	}
}
