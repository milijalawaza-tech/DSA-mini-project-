package DataStructures;

import restOfCodes.Student;

// ---------- DataStructures.Node: data part + next pointer ----------
public class Node {
    public Student data;
    Node    next;

    Node(Student data) {
        this.data = data;
        this.next = null;
    }
}