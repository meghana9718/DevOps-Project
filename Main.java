import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    service.addStudent(id, name);
                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    System.out.println("Student List:");
                    for (Student s : service.getStudents()) {
                        System.out.println(s.getId() + " - " + s.getName());
                    }
                    break;

                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
}
