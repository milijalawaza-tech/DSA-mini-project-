// ---------- Demonstration ----------
public class StudentServiceRecord {

    private static void printSearch(StudentList list, int no) {
        Node n = list.searchStudent(no);
        if (n != null)
            System.out.println("  FOUND: " + n.data.studentNumber + ", " + n.data.studentName
                    + ", " + n.data.serviceType + ", " + n.data.estimatedServiceTime + " min");
        else
            System.out.println("  NOT FOUND: student number " + no);
    }

    public static void main(String[] args) {
        StudentList list = new StudentList();

        System.out.println("=== 1. Insert at the END (list starts empty) ===");
        list.insertAtEnd(new Student(2024001, "PLACATOR", "Registration", 15));
        list.insertAtEnd(new Student(2024002, "WAZA", "Fee Payment", 10));
        list.insertAtEnd(new Student(225176130, "KASHANGO", "Transcript", 20));
        list.displayStudents();

        System.out.println("\n=== 2. Insert at the BEGINNING ===");
        System.out.println("BEFORE:");
        list.drawList();
        list.insertAtBeginning(new Student(2024004, "David", "ID Card", 5));
        System.out.println("AFTER inserting 2024004 (David) at beginning:");
        list.drawList();
        list.displayStudents();

        System.out.println("\n=== 3. Insert at a SPECIFIED POSITION (3rd) ===");
        System.out.println("BEFORE:");
        list.drawList();
        list.insertStudent(new Student(2024005, "Esther", "Course Advice", 25), 3);
        System.out.println("AFTER inserting 2024005 (SIGMA) at position 3:");
        list.drawList();

        System.out.println("\n=== 4. Insert at a SPECIFIED POSITION (4th) ===");
        list.insertStudent(new Student(2024006, "NGWE37", "Accommodation", 30), 4);
        list.drawList();
        list.displayStudents();

        System.out.println("\n=== 5. Duplicate / invalid insertions ===");
        list.insertStudent(new Student(2024002, "Duplicate", "Fee Payment", 10), 2);
        list.insertStudent(new Student(2024099, "Nobody", "None", 1), 0);

        System.out.println("\n=== 6. SEARCH ===");
        printSearch(list, 2024003);
        printSearch(list, 2024999);

        System.out.println("\n=== 7. DELETE a middle node (2024005) ===");
        System.out.println("BEFORE:");
        list.drawList();
        list.deleteStudent(2024005);
        System.out.println("AFTER:");
        list.drawList();

        System.out.println("\n=== 8. DELETE the first node (2024004) ===");
        list.deleteStudent(2024004);
        list.drawList();

        System.out.println("\n=== 9. DELETE the last node (2024003) ===");
        list.deleteStudent(2024003);
        list.drawList();

        System.out.println("\n=== 10. DELETE a non-existent student ===");
        list.deleteStudent(2024777);

        System.out.println("\n=== 11. Final TRAVERSAL ===");
        list.displayStudents();
        System.out.println("  Total records: " + list.size());
    }
}
