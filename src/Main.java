import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Queue waitingQueue = new Queue(50);

        int choice;

        do {

            System.out.println("\n==============================");
            System.out.println(" STUDENT SERVICE SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student");
            System.out.println("3. Display waiting students");
            System.out.println("0. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {

                System.out.print("Enter student number: ");
                int studentNumber = input.nextInt();

                input.nextLine();

                System.out.print("Enter student name: ");
                String name = input.nextLine();

                Student student =
                        new Student(studentNumber, name);

                waitingQueue.enqueue(student);

            }

            else if (choice == 2) {

                Student servedStudent =
                        waitingQueue.dequeue();

                if (servedStudent != null) {

                    System.out.println(
                            "Student served: "
                                    + servedStudent
                    );
                }

            }

            else if (choice == 3) {

                waitingQueue.displayQueue();

            }

            else if (choice == 0) {

                System.out.println("Exiting system...");

            }

            else {

                System.out.println("Invalid option.");

            }

        } while (choice != 0);

        input.close();
    }
}