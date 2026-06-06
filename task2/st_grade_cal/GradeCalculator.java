import java.util.*;

public class GradeCalculator {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // ================================
        // WELCOME MESSAGE
        // ================================
        System.out.println("================================");
        System.out.println("   STUDENT GRADE CALCULATOR    ");
        System.out.println("================================");

        // ================================
        // STEP 1 - TAKE STUDENT NAME
        // ================================
        System.out.print("Enter Student Name : ");
        String name = scan.nextLine();

        // ================================
        // STEP 2 - TAKE NUMBER OF SUBJECTS
        // ================================
        System.out.print("Enter Number of Subjects : ");
        int numSubjects = scan.nextInt();

        String[] subjectNames = new String[numSubjects];
        int[] marks = new int[numSubjects];
        int total = 0;

        scan.nextLine(); // clear newline

        // ================================
        // STEP 3 - TAKE SUBJECT NAMES AND MARKS
        // ================================
        for(int i = 0; i < numSubjects; i++) {

            System.out.print("Enter Subject " + (i+1) + " Name : ");
            subjectNames[i] = scan.nextLine();

            System.out.print("Enter Marks for " + subjectNames[i] + " (out of 100) : ");
            marks[i] = scan.nextInt();
            scan.nextLine();

            // Validate marks
            while(marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid! Enter marks between 0 and 100.");
                System.out.print("Enter Marks again : ");
                marks[i] = scan.nextInt();
                scan.nextLine();
            }

            total = total + marks[i];
        }

        // ================================
        // STEP 4 - CALCULATE AVERAGE
        // ================================
        double average = (double) total / numSubjects;

        // ================================
        // STEP 5 - CALCULATE GRADE
        // ================================
        String grade;
        String remark;

        if(average >= 90) {
            grade  = "A+";
            remark = "Outstanding!";
        } else if(average >= 80) {
            grade  = "A";
            remark = "Excellent!";
        } else if(average >= 70) {
            grade  = "B";
            remark = "Very Good!";
        } else if(average >= 60) {
            grade  = "C";
            remark = "Good!";
        } else if(average >= 50) {
            grade  = "D";
            remark = "Pass!";
        } else {
            grade  = "F";
            remark = "Fail! Work Harder!";
        }

        // ================================
        // STEP 6 - DISPLAY RESULT CARD
        // ================================
        System.out.println("\n================================");
        System.out.println("         RESULT CARD            ");
        System.out.println("================================");
        System.out.println("Student Name  : " + name);
        System.out.println("--------------------------------");

        // Print each subject and marks
        for(int i = 0; i < numSubjects; i++) {
            System.out.println(subjectNames[i] + "  :  " + marks[i] + " / 100");
        }

        System.out.println("--------------------------------");
        System.out.println("Total Marks   : " + total + " / " + (numSubjects * 100));
        System.out.printf ("Average       : %.2f%%\n", average);
        System.out.println("Grade         : " + grade);
        System.out.println("Remark        : " + remark);
        System.out.println("================================");

        if(grade.equals("F")) {
            System.out.println("Result        : FAIL ❌");
        } else {
            System.out.println("Result        : PASS ✅");
        }
        System.out.println("================================");

        scan.close();
    }
}