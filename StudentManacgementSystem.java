import java.io.*;
import java.util.*;

// ==========================
// 1. STUDENT CLASS
// ==========================
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters & Setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Roll: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }
}


// ==========================
// 2. STUDENT MANAGEMENT SYSTEM
// ==========================
class StudentManagementSystem {

    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        loadFromFile();
    }

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove student
    public boolean removeStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                students.remove(s);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Search student
    public Student searchStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll)
                return s;
        }
        return null;
    }

    // Display all
    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("student is not found");
            return;
        }
        for (Student s : students)
            System.out.println(s);
    }

    // Save to file
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Load from file
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}


// ==========================
// 3. MAIN USER INTERFACE
// ==========================
public class StudentManacgementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM   ");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;

            // Input Validation for menu
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {

                case 1:  // Add student
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Roll Number: ");
                    int roll;
                    try {
                        roll = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid roll number!");
                        break;
                    }

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if (grade.isEmpty()) {
                        System.out.println("Grade cannot be empty!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    System.out.println("Student added successfully!");
                    break;


                case 2:  // Edit student
                    System.out.print("Enter Roll Number to Edit: ");
                    int rollEdit = Integer.parseInt(sc.nextLine());

                    Student sEdit = sms.searchStudent(rollEdit);
                    if (sEdit == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    if (!newName.isEmpty()) sEdit.setName(newName);

                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();
                    if (!newGrade.isEmpty()) sEdit.setGrade(newGrade);

                    sms.saveToFile();
                    System.out.println("Student updated successfully!");
                    break;


                case 3:  // Remove student
                    System.out.print("Enter Roll Number to Remove: ");
                    int rollRemove = Integer.parseInt(sc.nextLine());
                    if (sms.removeStudent(rollRemove))
                        System.out.println("Student removed.");
                    else
                        System.out.println("Student not found!");
                    break;


                case 4:  // Search
                    System.out.print("Enter Roll Number to Search: ");
                    int rollSearch = Integer.parseInt(sc.nextLine());

                    Student result = sms.searchStudent(rollSearch);
                    if (result != null)
                        System.out.println("Found: " + result);
                    else
                        System.out.println("Student not found!");
                    break;


                case 5:  // Display all
                    sms.displayAll();
                    break;


                case 6:  // Exit
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;


                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}