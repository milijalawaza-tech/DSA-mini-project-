import DataStructures.Node;
import DataStructures.Queue;
import DataStructures.StudentList;
import SortingAlgorithms.InsertionSort;
import restOfCodes.Student;
import DataStructures.Node;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Queue waitingQueue = new Queue(50);
        StudentList records = new StudentList();

        int[] servedTimes = new int[1000];
        int servedCount = 0;
        int choice = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("CAMPUS SERVICE CENTRE");
            System.out.println("========================================");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student (remove from queue)");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record (Linked List - insertStudent())");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics");
            System.out.println("9. Sort service times");
            System.out.println("10. Run sorting experiment");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Please enter a number from 1 to 11.");
                input.nextLine();
                choice = 0;
            }

            if (choice == 1) {
                System.out.print("Enter student number: ");
                int studentNumber = input.nextInt();
                input.nextLine();

                System.out.print("Enter student name: ");
                String studentName = input.nextLine();

                System.out.print("Enter service type: ");
                String service = input.nextLine();

                System.out.print("Enter estimated service time in minutes: ");
                int time = input.nextInt();
                input.nextLine();

                Student student =
                        new Student(studentNumber, studentName, service, time);

                waitingQueue.enqueue(student);

            } else {
                if (choice == 2) {
                    Student servedStudent = waitingQueue.dequeue();

                    if (servedStudent != null) {
                        System.out.println("Student served: " + servedStudent);

                        if (servedCount < servedTimes.length) {
                            servedTimes[servedCount] =
                                    servedStudent.getEstimatedServiceTime();
                            servedCount++;
                        } else {
                            System.out.println("Daily service-time storage is full.");
                        }
                    }

                } else {
                    if (choice == 3) {
                        waitingQueue.displayQueue();

                    } else {
                        if (choice == 4) {
                            System.out.print("Enter student number: ");
                            int studentNumber = input.nextInt();
                            input.nextLine();

                            System.out.print("Enter student name: ");
                            String studentName = input.nextLine();

                            System.out.print("Enter service type: ");
                            String service = input.nextLine();

                            System.out.print("Enter service time in minutes: ");
                            int time = input.nextInt();
                            input.nextLine();

                            System.out.print("Enter insertion position: ");
                            int position = input.nextInt();
                            input.nextLine();

                            Student student = new Student(studentNumber, studentName, service, time);
                            student.serviceType = service;
                            student.estimatedServiceTime = time;

                            records.insertStudent(student, position);

                        } else {
                            if (choice == 5) {
                                records.displayStudents();

                            } else {
                                if (choice == 6) {
                                    System.out.print(
                                            "Enter student number to search: ");
                                    int studentNumber = input.nextInt();
                                    input.nextLine();

                                    Node found =
                                            records.searchStudent(studentNumber);

                                    if (found == null) {
                                        System.out.println("Record not found.");
                                    } else {
                                        System.out.println(
                                                "Record found: " + found.data);
                                    }

                                } else {
                                    if (choice == 7) {
                                        System.out.print(
                                                "Enter student number to remove: ");
                                        int studentNumber = input.nextInt();
                                        input.nextLine();

                                        records.deleteStudent(studentNumber);

                                    } else {
                                        if (choice == 8) {
                                            int[] statisticsTimes =
                                                    new int[servedCount];

                                            for (int i = 0;
                                                 i < servedCount;
                                                 i++) {
                                                statisticsTimes[i] =
                                                        servedTimes[i];
                                            }

                                            ArrayStatistics
                                                    .displayDailyStatistics(
                                                            statisticsTimes);

                                        } else {
                                            if (choice == 9) {
                                                if (servedCount == 0) {
                                                    System.out.println(
                                                            "No service times to sort.");
                                                } else {
                                                    int[] sortedTimes =
                                                            new int[servedCount];

                                                    for (int i = 0;
                                                         i < servedCount;
                                                         i++) {
                                                        sortedTimes[i] =
                                                                servedTimes[i];
                                                    }

                                                    InsertionSort.sort(
                                                            sortedTimes);

                                                    System.out.print(
                                                            "Sorted service times: ");

                                                    for (int i = 0;
                                                         i < sortedTimes.length;
                                                         i++) {
                                                        System.out.print(
                                                                sortedTimes[i]
                                                                        + " ");
                                                    }

                                                    System.out.println();
                                                }

                                            } else {
                                                if (choice == 10) {
                                                    experiment.main(
                                                            new String[0]);

                                                } else {
                                                    if (choice == 11) {
                                                        System.out.println(
                                                                "Exiting Campus Service Centre.");

                                                    } else {
                                                        System.out.println(
                                                                "Invalid choice. Select 1 to 11.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } while (choice != 11);

        input.close();
    }
}