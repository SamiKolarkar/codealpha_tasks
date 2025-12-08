import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student grades (comma separated): ");
        String input = sc.nextLine();

        // Split and store grades
        String[] parts = input.split(",");
        ArrayList<Integer> grades = new ArrayList<>();

        for (String p : parts) {
            grades.add(Integer.parseInt(p.trim()));
        }

        // Calculate average, highest, lowest
        int sum = 0;
        int highest = grades.get(0);
        int lowest = grades.get(0);

        for (int g : grades) {
            sum += g;
            if (g > highest) highest = g;
            if (g < lowest) lowest = g;
        }

        double average = (double) sum / grades.size();

        // Display report
        System.out.println("\n=== Student Grade Summary ===");
        System.out.println("Grades: " + grades);
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
    }
}

