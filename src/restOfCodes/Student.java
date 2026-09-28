package restOfCodes;

public class Student {
    private int studentNumber;
    private String studentName;
    public String serviceType;
    public int estimatedServiceTime;

    public Student(int studentNumber, String name) {
        this(studentNumber, name, "Not specified", 0);
    }

    public Student(int studentNumber, String name,
                   String serviceType, int estimatedServiceTime) {
        this.studentNumber = studentNumber;
        this.studentName = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return studentName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getEstimatedServiceTime() {
        return estimatedServiceTime;
    }

    @Override
    public String toString() {
        return "Student Number: " + studentNumber
                + ", Name: " + studentName
                + ", Service: " + serviceType
                + ", Time: " + estimatedServiceTime + " minutes";
    }
}