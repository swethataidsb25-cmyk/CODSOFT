package task4;

import java.util.*;
import java.io.*;

// class to represent a student
class Student {
    int rollNo;
    String name;
    String grade;

    Student(int rollNo, String name, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
    }

    public String toString() {
        return rollNo + "," + name + "," + grade;
    }
}

// main system class
class StudentManager {
    private ArrayList<Student> list = new ArrayList<>();
    private final String fileName = "students.txt";

    // add student
    public void addStudent(Student s) {
        list.add(s);
        System.out.println("Student added.");
    }

    // view all students
    public void displayStudents() {
        if (list.size() == 0) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : list) {
            System.out.println("Roll No: " + s.rollNo + 
                               " | Name: " + s.name + 
                               " | Grade: " + s.grade);
        }
    }

    // search student
    public Student searchStudent(int roll) {
        for (Student s : list) {
            if (s.rollNo == roll) {
                return s;
            }
        }
        return null;
    }

    // edit student
    public void editStudent(int roll, String newName, String newGrade) {
        Student s = searchStudent(roll);
        if (s != null) {
            s.name = newName;
            s.grade = newGrade;
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // delete student
    public void deleteStudent(int roll) {
        Student s = searchStudent(roll);
        if (s != null) {
            list.remove(s);
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // save to file
    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Student s : list) {
                bw.write(s.toString());
                bw.newLine();
            }
            bw.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    // load from file
    public void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int roll = Integer.parseInt(data[0]);
                String name = data[1];
                String grade = data[2];

                list.add(new Student(roll, name, grade));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}

// main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager sm = new StudentManager();

        sm.loadFromFile();

        int choice;

        do {
            System.out.println("\n====== STUDENT MANAGEMENT ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save & Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Invalid input.");
                    } else {
                        sm.addStudent(new Student(roll, name, grade));
                    }
                    break;

                case 2:
                    sm.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll No to search: ");
                    int searchRoll = sc.nextInt();

                    Student found = sm.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println("Found: " + found.name + " | Grade: " + found.grade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to edit: ");
                    int editRoll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine();

                    sm.editStudent(editRoll, newName, newGrade);
                    break;

                case 5:
                    System.out.print("Enter Roll No to delete: ");
                    int delRoll = sc.nextInt();
                    sm.deleteStudent(delRoll);
                    break;

                case 6:
                    sm.saveToFile();
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}