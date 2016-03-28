///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             QueueADT.java
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
 * An ordered collection of items, where items are added to the rear and removed
 * from the front.
 * 
 * @author CS367
 *
 */
public interface QueueADT<E> {
	/**
	 * Adds an item to the rear of the queue.
	 * 
	 * @param item
	 *            the item to add to the queue.
	 * @throws IllegalArgumentException
	 *             if item is null.
	 */
	void enqueue(E item);

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	E dequeue();

	/**
	 * Returns the item at front of the Queue without removing it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	E peek();

	/**
	 * Returns true iff the Queue is empty.
	 * 
	 * @return true if queue is empty; otherwise false.
	 */
	boolean isEmpty();

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	void clear();

	/**
	 * Returns the number of items in the Queue.
	 * 
	 * @return the size of the queue.
	 */
	int size();
}