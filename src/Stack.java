1.	Stack.java
java
package person3;

import java.util.EmptyStackException;

public class Stack {
    private String[] elements;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.top = -1;
    }

    public void push(String value) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Stack capacity reached. Cannot push: " + value);
        }
        elements[++top] = value;
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top--];
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

2.	PostfixEvaluator.java
java
package person3;

public class PostfixEvaluator {

    public static double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        String[] tokens = expression.trim().split("\\s+");
        Stack stack = new Stack(tokens.length);

        for (String token : tokens) {
            if (isOperator(token)) {
                if (stack.isEmpty()) throw new IllegalArgumentException("Invalid postfix expression.");
                String val2 = stack.pop();
                if (stack.isEmpty()) throw new IllegalArgumentException("Invalid postfix expression.");
                String val1 = stack.pop();
                
                double operand2 = Double.parseDouble(val2);
                double operand1 = Double.parseDouble(val1);
                
                double result = performOperation(operand1, operand2, token);
                stack.push(String.valueOf(result));
            } else {
                try {
                    Double.parseDouble(token);
                    stack.push(token);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token encountered: " + token);
                }
            }
        }

        String finalResult = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("The expression is malformed. Extra operands remaining.");
        }
        return Double.parseDouble(finalResult);
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("×") || token.equals("÷") || 
               token.equals("*") || token.equals("/");
    }

    private static double performOperation(double op1, double op2, String operator) {
        switch (operator) {
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "×":
            case "*": return op1 * op2;
            case "÷":
            case "/": 
                if (op2 == 0) throw new ArithmeticException("Division by zero error");
                return op1 / op2;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

3.	ArrayStatistics.java
javapackage person3;

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
4.	SortingVerifier.java
java
package person3;

public class SortingVerifier {

    // Part C Framework: Isolates the sort algorithm runtime from outside overheads
    public static void profileMergeSort(int[] array) {
        if (array == null || array.length == 0) return;
        
        long startTime = System.nanoTime(); // Timer start
        mergeSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();   // Timer stop
        
        long durationNano = endTime - startTime;
        System.out.printf("Pure Sort Time Execution: %d ns (~%.4f ms)\n", 
            durationNano, (durationNano / 1_000_000.0));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Part B Verification Logic
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
            k++;
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

Part E: Pseudocode Documentation
text
PROCEDURE push(value)
    IF top EQUALS capacity - 1 THEN
        THROW StackOverflowError
    ENDIF
    INCREMENT top
    elements[top] = value
ENDPROCEDURE

PROCEDURE pop()
    IF top EQUALS -1 THEN
        THROW EmptyStackException
    ENDIF
    value = elements[top]
    DECREMENT top
    RETURN value
ENDPROCEDURE

FUNCTION evaluatePostfix(expression)
    tokens = split expression by whitespace
    Initialize custom Stack
    FOR EACH token IN tokens
        IF token is an operator THEN
            operand2 = stack.pop()
            operand1 = stack.pop()
            result = calculate(operand1, operand2, token)
            stack.push(result)
        ELSE
            stack.push(token)
        ENDIF
    ENDFOR
    RETURN stack.pop()
ENDFUNCTION

Part F: Report Content & Traces
1. Trace of Postfix Expression: "12 5 + 3 ×"
Current Token	Operation Completed	Current Stack State [bottom -> top]
12	Push element	["12"]
5	Push element	["12", "5"]
+	Pop 5, pop 12. Compute 12 + 5 = 17. Push result	["17"]
3	Push element	["17", "3"]
×	Pop 3, pop 17. Compute 17 * 3 = 51. Push result	["51"]
•	Final Return Value: 51.0
2. Expected Text Outputs (For Report Verification)
text
=============================================
   OPTION 8: DAILY SERVICE CENTRE STATISTICS 
=============================================
 Total Students Served             : 7
 Total Accumulated Service Time   : 72 mins
 Average Service Time              : 10.29 mins
 Highest Service Time Registered   : 22 mins
 Lowest Service Time Registered    : 3 mins
 Students Delayed (> 10 mins)      : 3
=============================================

Integration Guide for Person 1
Person 1 to put inside their master execution class (Main.java):
java
// 1. Tell Person 1 to keep this running array list tracker or primitive instance inside Main.java:
int[] dailyServiceTimes = {12, 5, 8, 22, 15, 7, 3}; 

// 2. Inside their dynamic switch case block, they drop this linkage:
case 8:
    person3.ArrayStatistics.displayDailyStatistics(dailyServiceTimes);
    break;


