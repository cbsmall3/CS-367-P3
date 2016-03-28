///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            StudentCenter
// Files:            N/A
// Semester:         CS 367 Spring 2016
//
// Author:           Charles B. Small III/csmall3@wisc.edu
// CS Login:         small-iii
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Student Center abstraction for our simulation. Execution starts here.
 * 
 * @author CS367
 *
 */
public class StudentCenter {

	@SuppressWarnings("unused")
	private static int DEFAULT_POINTS = 100;
	// Global lists of all courses and students
	private static List<Course> courseList = new ArrayList<Course>();
	private static List<Student> studentList = new ArrayList<Student>();

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Bad invocation! Correct usage: "
					+ "java StudentCentre <StudentCourseData file>"
					+ "<CourseRosters File> + <StudentCourseAssignments File>");
			System.exit(1);
		}

		boolean didInitialize = readData(args[0]);

		if (!didInitialize) {
			System.err.println("Failed to initialize the application!");
			System.exit(1);
		}

		generateAndWriteResults(args[1], args[2]);

	}

	/**
	 * 
	 * @param fileName
	 *            - input file. Has 3 sections - #Points/Student, #Courses, and
	 *            multiple #Student sections. See P3 page for more details.
	 * @return true on success, false on failure.
	 * 
	 */
	public static boolean readData(String fileName) {
		try {
			// TODO parse the input file as described in the P3 specification.
			// make sure to call course.addStudent() appropriately.

			String[] courseData = null;
			//stores course data for creating new Course object
			
			String[] courseRequest = null;
			//stores course request data before submitting
			//course request
			
			String studentName = null;
			String studentID = null;
			//store student ID info for creating a 
			//new Student object
			
			String newInput = null;
			//used to store header for use in parsing loops
			
			int maxPoints = 0;
			//used to store maximum number of points
			//when creating Student objects

			Scanner input = new Scanner(new File(fileName));

			while (input.hasNextLine()) {

				newInput = input.nextLine();

				if (newInput.contains("#Points/Student") && newInput != null) {

					while (!input.hasNext("#Student") && input.hasNextLine()
							&& !input.hasNext("#Courses")) {

						maxPoints = Math.abs(Integer.parseInt(input.nextLine()
								.trim()));

					} //reads the max number of points alloted to each student

				} else if (newInput.contains("#Courses") && newInput != null) {

					while (!input.hasNext("#Student") && input.hasNextLine()) {

						courseData = input.nextLine().split(" ");
						Course course = new Course(courseData[0].trim(),
								courseData[1].trim(), Math.abs(Integer
										.parseInt(courseData[2].trim())));
						courseList.add(course);

					} //reads course data and then uses it to make a Course object

				} else if (newInput.contains("#Student") && newInput != null) {

					studentName = input.nextLine().trim();
					studentID = input.nextLine().trim();
					Student student = new Student(studentName, studentID,
							maxPoints);

					studentList.add(student); //reads student demographic data 
											  //and then creates a Student object
											  

					while (!input.hasNext("#Student") && input.hasNextLine()) {

						courseRequest = input.nextLine().split(" ");
						student.addToCart(getCourseFromCourseList(courseRequest
								[0]
								.trim()));

						getCourseFromCourseList(courseRequest[0].trim())
								.addStudent(
										student,
										Math.abs(Integer
												.parseInt(courseRequest[1]
														.trim())));
					} //read student course request data and then adds that student
					  //to the registration priority queue and their course cart 
					  //for each course requested
				}

			}

			input.close();

		} catch (Exception e)

		{
			e.printStackTrace();
			System.out.println("File Parse Error");
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param fileName1
	 *            - output file with a line for each course
	 * @param fileName2
	 *            - output file with a line for each student
	 */
	public static void generateAndWriteResults(String fileName1,
			String fileName2) {
		try {
			// List Students enrolled in each course
			PrintWriter writer = new PrintWriter(new File(fileName1));
			for (Course c : courseList) {
				writer.println("-----" + c.getCourseCode() + " "
						+ c.getCourseName() + "-----");

				// Core functionality
				c.processRegistrationList();

				// List students enrolled in each course
				int count = 1;
				for (Student s : c.getCourseRegister()) {
					writer.println(count + ". " + s.getid() + "\t"
							+ s.getName());
					s.enrollCourse(c);
					count++;
				}
				writer.println();
			}
			writer.close();

			// List courses each student gets
			writer = new PrintWriter(new File(fileName2));
			for (Student s : studentList) {
				writer.println("-----" + s.getid() + " " + s.getName()
						+ "-----");
				int count = 1;
				for (Course c : s.getEnrolledCourses()) {
					writer.println(count + ". " + c.getCourseCode() + "\t"
							+ c.getCourseName());
					count++;
				}
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Look up Course from classCode
	 * 
	 * @param courseCode
	 * @return Course object
	 */
	private static Course getCourseFromCourseList(String courseCode) {
		for (Course c : courseList) {
			if (c.getCourseCode().equals(courseCode)) {
				return c;
			}
		}
		return null;
	}

}