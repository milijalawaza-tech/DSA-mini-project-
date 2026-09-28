public class ArrayStatistics {

    public static void displayDailyStatistics(int[] serviceTimes) {
        if (serviceTimes == null || serviceTimes.length == 0) {
            System.out.println("\n=============================================");
            System.out.println("   OPTION 8: DAILY SERVICE CENTRE STATISTICS ");
            System.out.println("=============================================");
            System.out.println(" No student tracking records found for today. ");
            System.out.println("=============================================");
            return;
        }

        int totalStudents = serviceTimes.length;
        int totalServiceTime = 0;
        int highestServiceTime = serviceTimes[0]; // FIXED: Accessing index 0 correctly
        int lowestServiceTime = serviceTimes[0];  // FIXED: Accessing index 0 correctly
        int studentsOver10Min = 0;

        for (int i = 0; i < serviceTimes.length; i++) {
            int time = serviceTimes[i];
            totalServiceTime += time;

            if (time > highestServiceTime) {
                highestServiceTime = time;
            }
            if (time < lowestServiceTime) {
                lowestServiceTime = time;
            }
            if (time > 10) {
                studentsOver10Min++;
            }
        }

        double averageServiceTime = (double) totalServiceTime / totalStudents;

        System.out.println("\n=============================================");
        System.out.println("   OPTION 8: DAILY SERVICE CENTRE STATISTICS ");
        System.out.println("=============================================");
        System.out.printf(" Total Students Served             : %d\n", totalStudents);
        System.out.printf(" Total Accumulated Service Time   : %d mins\n", totalServiceTime);
        System.out.printf(" Average Service Time              : %.2f mins\n", averageServiceTime);
        System.out.printf(" Highest Service Time Registered   : %d mins\n", highestServiceTime);
        System.out.printf(" Lowest Service Time Registered    : %d mins\n", lowestServiceTime);
        System.out.printf(" Students Delayed (> 10 mins)      : %d\n", studentsOver10Min);
        System.out.println("=============================================");
    }
}
