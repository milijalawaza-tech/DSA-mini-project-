public class Queue {

    private Student[] students;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        students = new Student[capacity];

        front = 0;
        rear = -1;
        size = 0;
    }

    // Add student to the rear
    public void enqueue(Student student) {

        if (size == capacity) {
            System.out.println("Queue is full.");
            return;
        }

        rear++;

        if (rear == capacity) {
            rear = 0;
        }

        students[rear] = student;
        size++;

        System.out.println("Student added to waiting queue.");
    }

    // Remove student from the front
    public Student dequeue() {

        if (isEmpty()) {
            System.out.println("No students are waiting.");
            return null;
        }

        Student student = students[front];

        students[front] = null;

        front++;

        if (front == capacity) {
            front = 0;
        }

        size--;

        return student;
    }

    // View the next student
    public Student peek() {

        if (isEmpty()) {
            return null;
        }

        return students[front];
    }

    // Check if queue is empty
    public boolean isEmpty() {

        return size == 0;
    }

    // Display all waiting students
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("No students are waiting.");
            return;
        }

        System.out.println("\nWaiting Students:");

        int index = front;

        for (int i = 0; i < size; i++) {

            System.out.println(students[index]);

            index++;

            if (index == capacity) {
                index = 0;
            }
        }
    }
}