package com.codealpha.gradetracker.service;

import java.util.List;

import com.codealpha.gradetracker.model.Student;

public class GradeCalculator {
	
	public static double calculateAverage(List<Student> students) {
		
		double totalMarks = 0;
		
		for(Student s : students) {
			totalMarks += s.getMarks(); 
		}
		
		return totalMarks / students.size();
	}
	
	public static double findHighestMarks(List<Student> students) {
		
		double highest = students.get(0).getMarks();
		
		for(Student st : students) {
			if(st.getMarks() > highest) {
				highest = st.getMarks();
			}
		}
		
		return highest;
	}
	
	public static double findLowestMarks(List<Student> students) {
		
		double lowest = students.get(0).getMarks();
		
		for(Student st : students) {
			if(st.getMarks() < lowest) {
				lowest = st.getMarks();
			}
		}
		
		return lowest;
	}
}
