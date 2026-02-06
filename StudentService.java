import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(int id, String name) {
        students.add(new Student(id, name));
    }

    public List<Student> getStudents() {
        return students;
    }
}
