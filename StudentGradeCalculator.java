import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Invalid number of subjects.");
            scanner.close();
            return;
        }

        int[] marks = new int[n];
        int total = 0;
        System.out.println("Enter marks obtained (out of 100) for each subject:");
        for (int i = 0; i < n; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            int m = scanner.nextInt();
            if (m < 0 || m > 100) {
                System.out.println("Invalid mark. Please enter between 0 and 100.");
                i--;
                continue;
            }
            marks[i] = m;
            total += m;
        }

        double average = (double) total / n; // average percentage
        char grade;

        // Example grading scale: you can adjust thresholds as needed
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else if (average >= 40) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        System.out.println("\n--- Result ---");
        System.out.println("Total marks = " + total + " / " + (n * 100));
        System.out.printf("Average (percentage) = %.2f%%\n", average);
        System.out.println("Grade = " + grade);

        scanner.close();
    }
}