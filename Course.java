
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             Course.java
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
 * Class to represent every Course in our Course Registration environment
 * 
 * @author CS367
 * @param <E>
 *
 */

public class Course {

	private String courseCode;
	private String name;

	// Number of students allowed in the course
	private int maxCapacity;
	// Number of students enrolled in the course
	private int classCount;

	// The PriorityQueue structure
	private PriorityQueue<Student> registrationQueue;

	// List of students who are finally enrolled in the course
	private List<Student> courseRoster;

	public Course(String classCode, String name, int maxCapacity) {
		this.courseCode = classCode;
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.registrationQueue = new PriorityQueue<Student>();
		this.courseRoster = new ArrayList<Student>();
		this.classCount = 0;

	}

	/**
	 * Creates a new PriorityqueueItem - with appropriate priority(coins) and
	 * this student in the item's queue. Add this item to the registrationQueue.
	 * 
	 * @param student
	 *            the student
	 * @param coins
	 *            the number of coins the student has
	 */
	public void addStudent(Student student, int coins) {
		// This method is called from Studentcenter.java

		// Enqueue a newly created PQItem.
		// Checking if a PriorityQueueItem with the same priority already exists
		// is done in the enqueue method.

		// TODO : see function header

		if (student.deductCoins(coins)) {

			PriorityQueueItem<Student> newPQI = new PriorityQueueItem<Student>(
					coins);

			newPQI.add(student);
			getRegistrationQueue().enqueue(newPQI);

		}

	}

	/**
	 * Populates the courseRoster from the registration list. Use the
	 * PriorityQueueIterator for this task.
	 */
	public void processRegistrationList() {
		// TODO : populate courseRoster from registrationQueue
		// Use the PriorityQueueIterator for this task.

		PriorityQueueIterator<Student> itr = registrationQueue.iterator();

		PriorityQueueItem<Student> nextPQI;
		// temporary priority queue item for iterator output below

		boolean done = false;
		// local control variable for while loop below

		int diff;
		// "diff" represents remaining spots available

		// As long as there are open slots in the class roster
		// and there are students in the registration queue
		// ...

		while (itr.hasNext() && !done) {

			nextPQI = itr.next();
			diff = maxCapacity - classCount;

			if (diff > 0) {

				// ...if number of students in Queue under current nextPQI is
				// less than or equal to the number of remaining spots add
				// all the students from that Queue and increment classCount
				// after adding them to the class roster or else...

				if (!(nextPQI.getList().size() > diff)) {

					while (!nextPQI.getList().isEmpty()) {

						getCourseRegister().add(nextPQI.getList().dequeue());

						classCount++;

					}

				} else {

					// ...if the number of students in Queue under current
					// nextPQI are greater than the number of remaining
					// spots add number of students in Queue equal to
					// number of remaining spots only and increment
					// classCount or else...

					while (diff > 0) {

						getCourseRegister().add(nextPQI.getList().dequeue());

						diff--;

						classCount++;
					}
				}

				// ...if there are no remaining spots in the class roster
				// then do not add any more students by setting "done"
				// to "true" to terminate the while loop

			} else {
				done = true;
			}

		}

	}

	public String getCourseName() {
		// TODO
		return this.name;
	}

	public String getCourseCode() {
		// TODO
		return this.courseCode;
	}

	public List<Student> getCourseRegister() {
		// TODO
		return this.courseRoster;
	}

	public PriorityQueue<Student> getRegistrationQueue() {

		return this.registrationQueue;

	}

}