import java.util.*;

public class Project1 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("\n>Enter grades in comma separated manner : ");
		String input=sc.next();
		String[] inptArray=input.split(",");
		double[] grades=new double[inptArray.length];
		for (int i=0;i<inptArray.length;i++) grades[i]=Double.parseDouble(inptArray[i]);
		double avg=0;
		double highest=0;
		double lowest=0;
		double sum=0;
		for (double grade:grades) {
			sum+=grade;
			highest=Math.max(highest,grade);
			lowest=Math.min(lowest,grade);
		}
		avg=sum/(double)grades.length;
		System.out.println(">The average of the grades are : "+avg);
		System.out.println(">The highest grade is : "+highest);
		System.out.println(">The lowest grade is : "+lowest);
		System.out.print(">The grades are : ");
		for (double grade:grades) System.out.print(grade+" ");
        System.out.println();
	}
}
