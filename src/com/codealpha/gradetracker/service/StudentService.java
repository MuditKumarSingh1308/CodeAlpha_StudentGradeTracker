package com.codealpha.gradetracker.service;

import java.util.ArrayList;
import java.util.List;

import com.codealpha.gradetracker.model.Student;

public class StudentService {
	
	private ArrayList<Student> students = new ArrayList<>();
	
	public List<Student> getAllStudents() {
		return List.copyOf(students);
	}
	
	public boolean isStudentIdExists(int id) {
		for(Student s : students) {
			if(s.getId() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean addStudent(Student student) {
		if(student == null) {
		    return false;
		}
		
		if(isStudentIdExists(student.getId())) {
			return false;
		}
	
		students.add(student);
		return true;
	}
}
