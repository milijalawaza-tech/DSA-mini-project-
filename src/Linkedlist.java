/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


class Student {
    int    studentNumber;
    String studentName;
    String serviceType;
    int    estimatedServiceTime; 

    Student(int studentNumber, String studentName, String serviceType, int estimatedServiceTime) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }
}

// ---------- Node: data part + next pointer ----------
class Node {
    Student data;
    Node    next;

    Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

// ---------- Singly linked list ----------
class StudentList {
    private Node head;
    private int  count;

    public StudentList() {
        head = null;
        count = 0;
    }

    public int  size()    { return count; }
    public boolean isEmpty() { return head == null; }
    public Node getHead() { return head; }   

  
    public boolean insertStudent(Student s, int position) {
        if (position < 1) {
            System.out.println("  [!] Invalid position " + position + ".");
            return false;
        }
        if (searchStudent(s.studentNumber) != null) {
            System.out.println("  [!] Student number " + s.studentNumber
                    + " already exists. Insert cancelled.");
            return false;
        }

        Node newNode = new Node(s);

        if (position == 1 || head == null) {            
            newNode.next = head;
            head = newNode;
        } else {                                       
            Node prev = head;
            int i = 1;
            while (i < position - 1 && prev.next != null) {
                prev = prev.next;
                i++;
            }
            newNode.next = prev.next;                
            prev.next = newNode;                 
        }
        count++;
        return true;
    }

    // Convenience wrappers
    public boolean insertAtBeginning(Student s) { return insertStudent(s, 1); }
    public boolean insertAtEnd(Student s)       { return insertStudent(s, count + 1); }

    // Delete by student number. Returns true if deleted.
    public boolean deleteStudent(int studentNumber) {
        if (head == null) {
            System.out.println("  [!] List is empty.");
            return false;
        }
        Node cur = head;
        Node prev = null;
        while (cur != null && cur.data.studentNumber != studentNumber) {
            prev = cur;
            cur = cur.next;
        }
        if (cur == null) {
            System.out.println("  [!] Student " + studentNumber + " not found.");
            return false;
        }
        if (prev == null) head = cur.next;              
        else              prev.next = cur.next;        
        count--;                                        
        return true;
    }

    // Search by student number. Returns the node or null.
    public Node searchStudent(int studentNumber) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.studentNumber == studentNumber) return cur;
            cur = cur.next;
        }
        return null;
    }

    // Traverse and print every record.
    public void displayStudents() {
        if (head == null) {
            System.out.println("  (list is empty)");
            return;
        }
        System.out.printf("  %-5s%-12s%-16s%-20s%s%n",
                "Pos", "Student No", "Name", "Service Type", "Est. Time (min)");
        System.out.println("  " + "-".repeat(68));
        int pos = 1;
        for (Node cur = head; cur != null; cur = cur.next, pos++) {
            System.out.printf("  %-5d%-12d%-16s%-20s%d%n",
                    pos, cur.data.studentNumber, cur.data.studentName,
                    cur.data.serviceType, cur.data.estimatedServiceTime);
        }
    }

    // Prints the list as a chain of nodes (used for before/after drawings).
    public void drawList() {
        StringBuilder sb = new StringBuilder("  head -> ");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append("[").append(cur.data.studentNumber).append(" | ")
              .append(cur.data.studentName).append(" | next] -> ");
        }
        sb.append("NULL");
        System.out.println(sb);
    }
}



    
    

