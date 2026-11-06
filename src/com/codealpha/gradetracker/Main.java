package com.codealpha.gradetracker;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.codealpha.gradetracker.model.Student;
import com.codealpha.gradetracker.service.GradeCalculator;
import com.codealpha.gradetracker.service.StudentService;

public class Main {
	
	public static void generateSummaryReport(List<Student> students) {
		
		if(students.isEmpty()) {
			System.out.println("No students found.");
			return;
		}
		
		System.out.println("========== STUDENT SUMMARY REPORT ==========");
		System.out.println();
		
		System.out.println("Total Students: " + students.size());
		System.out.println("Average Marks: " + GradeCalculator.calculateAverage(students));
		System.out.println("Highest Marks: " + GradeCalculator.findHighestMarks(students));
		System.out.println("Lowest Marks: " + GradeCalculator.findLowestMarks(students));
		
		System.out.println();
		System.out.println("============================================");
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StudentService service = new StudentService();
		
		int choice;
		
		do {
			System.out.println("\n===== STUDENT GRADE TRACKER =====");
			System.out.println("1. Add Student");
			System.out.println("2. Display Students");
			System.out.println("3. Calculate Average Marks");
			System.out.println("4. Find Highest Marks");
			System.out.println("5. Find Lowest Marks");
			System.out.println("6. Generate Student Summary Report");
			System.out.println("7. Exit");
			
			while(true) {
				try {
					System.out.println("Enter your choice: ");
					
					choice = sc.nextInt();
					sc.nextLine();
					
					break;
					
				} catch (InputMismatchException e) {
					System.out.println("Invalid input! Enter a numeric choice.");
			        sc.nextLine();
				}
			}
			
			List<Student> students = service.getAllStudents();
			
			switch(choice) {
				case 1: 
					int id;

					while (true) {
					    try {
					        System.out.print("Enter Student ID: ");
					        id = sc.nextInt();
					        sc.nextLine();
					        
					        if (id <= 0) {
					            System.out.println("ID must be positive!");
					            continue;
					        }
					        
					        if(service.isStudentIdExists(id)) {
					        	System.out.println("ID already exists!");
					        	continue;
					        }

					        break;

					    } catch (InputMismatchException e) {
					        System.out.println("Invalid input! Enter a numeric ID.");
					        sc.nextLine();
					    }
					}
					
					String name;
					
					do {
						System.out.println("Enter Student Name: ");
						name = sc.nextLine();
						
						if(name.isBlank()) {
							System.out.println("Name cannot be empty!");
						}
					} while(name.isBlank());
					
					double marks = -1;

					do {
					    try {
					        System.out.print("Enter Marks (0-100): ");
					        marks = sc.nextDouble();

					        if (!Double.isFinite(marks) || marks < 0 || marks > 100) {
					            System.out.println("Marks must be a valid number between 0 and 100!");
					        }
					    } catch (InputMismatchException e) {
					        System.out.println("Invalid input! Please enter a number.");
					        sc.nextLine();
					    }
					} while (!Double.isFinite(marks) || marks < 0 || marks > 100);
					
					Student student = new Student(id, name, marks);
					
					if(service.addStudent(student)) {
						System.out.println("Student added successfully!");
					} else {
						System.out.println("ID already exists!");
					}
					
					break;
					
				case 2: 
					if(students.isEmpty()) {
						System.out.println("No students found.");
					} else {
						for (Student s : students) {
							System.out.println("--------------------");
							s.displayDetails();
						}
					}
					
					break;
					
				case 3:
				    if (students.isEmpty()) {
				        System.out.println("No students found.");
				    } else {
				    	System.out.println("Average Marks: " + GradeCalculator.calculateAverage(students));
				    }
				    
				    break;
					
				case 4:
					if (students.isEmpty()) {
				        System.out.println("No students found.");
				    } else {
				    	System.out.println("Highest Marks: " + GradeCalculator.findHighestMarks(students));
				    }
					
					break;
					
				case 5:
					if (students.isEmpty()) {
				        System.out.println("No students found.");
				    } else {
				    	System.out.println("Lowest Marks: " + GradeCalculator.findLowestMarks(students));
				    }
					
					break;
					
				case 6:
					generateSummaryReport(students);
					break;
					
				case 7:
					System.out.println("Exiting application...");
					
					break;
					
				default:
					System.out.println("Invalid choice!");
					
			}
		} while (choice != 7);
		
		sc.close();
	}
}
