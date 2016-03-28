///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             PriorityQueueIterator.java
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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueueIterator<T> implements Iterator<PriorityQueueItem<T>> {

	private PriorityQueue<T> priorityQueue;
	
	//number of items in copy of priority queue. Will equal number of
	//items in the original priority queue and be set in constructor
	
	int pos;

	/**
	 * Constructs a PriorityQueueIterator by utilizing a copy of the
	 * PriorityQueue. Hint : The local priorityQueue object need not be
	 * preserved, and hence you can use the dequeue() operation.
	 * 
	 * @param pq
	 */
	public PriorityQueueIterator(PriorityQueue<T> pq)

	{

		this.priorityQueue = new PriorityQueue<T>(pq);
		this.pos = this.priorityQueue.size();

		// TODO
		// This copies the contents of the passed parameter to the local object.
		// Hint : see copy constructor in PriorityQueue
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true/false
	 */
	@Override
	public boolean hasNext() {
		
		// returns true as long as count of items ("pos") is greater
		// than zero
		
		return this.pos > 0;
	}

	/**
	 * Returns the next element in the iteration. The iterator should return the
	 * PriorityQueueItems in order of decreasing priority.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public PriorityQueueItem<T> next() {

		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		//iterator queue items are dequeued until count of items
		//("pos") is zero

		pos--;

		return this.priorityQueue.dequeue();

	}

	/**
	 * Unsupported in this iterator for this assignment
	 */
	@Override
	public void remove() {
		// Do not implement
		throw new UnsupportedOperationException();
	}

}
