
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             Student.java
// Semester:         CS 367 Spring 2016
//
// Author:           Charles B. Small III/csmall3@wisc.edu
// CS Login:         small-iii
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     N/A
// Email:            N/A
// CS Login:         N/A
// Lecturer's Name:  N/A
// Lab Section:      N/A
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to represent every Student in our Course Registration environment.
 * 
 * @author CS367
 *
 */
public class Student {

	// Do not modify this file!

	private String name;
	private String id;
	private int myCourseCoins;
	private List<Course> myCourseCart;
	private List<Course> myEnrolledCourses;

	public Student(String name, String id, int courseCoins) {
		this.name = name;
		this.id = id;
		this.myCourseCoins = courseCoins;
		this.myEnrolledCourses = new ArrayList<Course>();
		this.myCourseCart = new ArrayList<Course>();
	}

	public String getName() {
		return this.name;
	}

	public String getid() {
		return this.id;
	}

	public int getPoints() {
		return this.myCourseCoins;
	}

	public List<Course> getEnrolledCourses() {
		return this.myEnrolledCourses;
	}

	public boolean deductCoins(int numCoins) {
		if (numCoins > this.myCourseCoins) {
			return false;
		}
		this.myCourseCoins -= numCoins;
		return true;

	}

	/**
	 * Adds the course to the list of courses the student is interested in. Is
	 * consistent with the registration queue.
	 * 
	 * @param course
	 */
	public void addToCart(Course course) {
		myCourseCart.add(course);
	}

	/**
	 * After the registration list is processed, adds successfully enrolled
	 * courses to Student's list
	 * 
	 * @param course
	 */
	public void enrollCourse(Course course) {
		myEnrolledCourses.add(course);
	}

}