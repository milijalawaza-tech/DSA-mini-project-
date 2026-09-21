public class Student {

    private int studentNumber;
    private String name;

    public Student(int studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Student Number: " + studentNumber
                + ", Name: " + name;
    }
}