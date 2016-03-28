///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             PriorityQueueItem.java
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

/**
 * 
 * Class to represent object stored at every entry in the PriorityQueue. ie, The
 * internal node structure of {@link PriorityQueue}
 * 
 * @author CS367
 *
 * @param <E>
 *            the generic type of the data content stored in the list
 */
public class PriorityQueueItem<E> implements Comparable<PriorityQueueItem<E>> {

	private int priority;
	private Queue<E> queue;

	public PriorityQueueItem(int priority) {

		this.priority = priority;
		this.queue = new Queue<E>();

	}

	public int getPriority() {
		// TODO
		return this.priority;
	}

	public Queue<E> getList() {
		// TODO
		return this.queue;
	}

	/**
	 * Add an item to the queue of this PriorityQueueItem object
	 * @param  
	 * 
	 * @param student
	 *            item to add to the queue
	 */
	public void add(E item) {
		
		
		getList().enqueue(item);

	}

	/**
	 * Compares this Node to another node on basis of priority
	 * 
	 * @param o
	 *            other node to compare to
	 * @return -1 if this node's priority is lesser, +1 if this nodes priority
	 *         is higher after, else 0 if priorities are the same.
	 */
	@Override
	public int compareTo(PriorityQueueItem<E> o) {

		int x = 0;

		if (this.getPriority() > o.getPriority())

			x = 1;

		if (this.getPriority() == o.getPriority())

			x = 0;

		if (this.getPriority() < o.getPriority())

			x = -1;

		return x;

	}

}
