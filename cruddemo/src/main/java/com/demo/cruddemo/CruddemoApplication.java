package com.demo.cruddemo;

import com.demo.cruddemo.dao.StudentDAO;
import com.demo.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);



	}
// loaded after springboot applicatinon is loaded....
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);

		//	readStudent(studentDAO);

			//Query for all Student data
			//queryStudentData(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);

		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all the students");
		int numsDeleted = studentDAO.deleteAll();
		System.out.println("Number of Rows Deleted" + numsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentID=5;
		System.out.println("Deleting Id" + studentID + "data");
		studentDAO.delete(studentID);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//create a query to retrieve the student baseed on ID
		int studentId=1;
		System.out.println("Getting student with ID"+studentId);
		Student myStudent = studentDAO.findById(studentId);

		//Change the firstName
		System.out.println("Updating the student name");
		myStudent.setFirstName("Chetana");

		//update the student
		studentDAO.update(myStudent);
		//Display the updated student
		System.out.println("Update the student"+myStudent);


	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> myData=studentDAO.findByLastName("Mathad");

		//display the list of students
		for(Student tempStudent:myData){
			System.out.println(tempStudent);
		}
	}

	private void queryStudentData(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findAll();


		//displays the list of students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		Student tempStudent=new Student("Sahana","Mathad","sahana1098@gmail.com");

		//Save the student object
		studentDAO.save(tempStudent);

		//Display ID of the saved student

		int theId= tempStudent.getId();
		System.out.println("Student of ID"+ theId + "is added the DB");

		//retrieve student based on the primary key
		System.out.println("Retrieving the data...");
		Student myStudent = studentDAO.findById(theId);

		//display the student
		System.out.println("Found the student"+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating a new object...");
		Student tempStudent1 = new Student("Chetana","Mathad","chetana1098@gmail.com");
		Student tempStudent2 = new Student("Praful","Mathad","praful1098@gmail.com");
		Student tempStudent3 = new Student("Arnav","C","arnav1098@gmail.com");
		Student tempStudent4 = new Student("Aarushi","C","aarushi1098@gmail.com");

		//save the student object
		System.out.println("Saving the student object");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
		studentDAO.save(tempStudent4);


	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating a new object...");
		Student tempStudent = new Student("Sahana","Mathad","sahana1098@gmail.com");

		//save the student object
		System.out.println("Saving the data...");
		studentDAO.save(tempStudent);

		//display the ID of the saved student
		System.out.println("Data saved for student ID:" + tempStudent.getId());

	}

}
