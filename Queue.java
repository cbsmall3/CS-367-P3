import java.lang.reflect.Array;

///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  StudentCenter
//File:             Queue.java
//Semester:         CS 367 Spring 2016
//
//Author:           Charles B. Small III/csmall3@wisc.edu
//CS Login:         small-iii
//Lecturer's Name:  Debra Deppeler
//Lab Section:      N/A
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//Pair Partner:     N/A
//Email:            N/A
//CS Login:         N/A
//Lecturer's Name:  N/A
//Lab Section:      N/A
//
////////////////////STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//fully acknowledge and credit all sources of help,
//other than Instructors and TAs.
//
//Persons:          Identify persons by name, relationship to you, and email.
//Describe in detail the the ideas and help they provided.
//
//Online sources:   avoid web searches to solve your problems, but if you do
//search, be sure to include Web URLs and description of 
//of any information you find.
////////////////////////////80 columns wide //////////////////////////////////

/**
 * An ordered collection of items, where items are added to the rear and removed
 * from the front.
 */
public class Queue<E> implements QueueADT<E> {

	private static final int MAX_CAPACITY = 100;

	private E[] queue;
	private int size;
	private int front;
	private int rear;

	// TODO
	// You may use a naive expandable circular array or a chain of listnodes.
	// You may NOT use Java's predefined classes such as ArrayList or
	// LinkedList.

	@SuppressWarnings("unchecked")
	public Queue() {

		this.queue = (E[]) new Object[MAX_CAPACITY + 1];
		this.size = 0;
		this.front = 0;
		this.rear = 0;

	}

	/**
	 * Adds an item to the rear of the queue.
	 * 
	 * @param item
	 *            the item to add to the queue.
	 * @throws IllegalArgumentException
	 *             if item is null.
	 */

	public void enqueue(E item) {

		if (item == null) {
			throw new IllegalArgumentException();
		}

		// if number of items exceeds size limits of the array
		// then expand the array

		if (!(size < queue.length - 1)) {

			this.expandCapacity();
		}

		queue[rear] = item;

		if (rear < queue.length - 1) {

			rear++;

		} else {
			rear = 0;
		}

		// rear index is incremented by one if not at end otherwise reset
		// to zero after item added

		size++;

		// size of queue is incremented
	}

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E dequeue() {

		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		E next = queue[front];

		// item with highest priority loaded into variable for
		// return at end of method

		if (front < queue.length - 1) {

			front++;

		} else {
			front = 0;
		}

		// front index is incremented by one if not at end otherwise reset
		// to zero after item added

		size--;

		// size of queue decremented by one

		return next;

		// item with highest priority returned

	}

	/**
	 * Returns the item at front of the Queue without removing it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E peek() {

		return queue[front];

	}

	/**
	 * Returns true iff the Queue is empty.
	 * 
	 * @return true if queue is empty; otherwise false.
	 */
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	public void clear() {

		while (!isEmpty()) {

			this.dequeue();

		}

	}

	/**
	 * Returns the number of items in the Queue.
	 * 
	 * @return the size of the queue.
	 */
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	private void expandCapacity() {

		E[] newQueue;

		newQueue = (E[]) Array.newInstance(Queue.class,
				queue.length + MAX_CAPACITY);
		
		//new queue gets length of old array plus what the max capacity is

		for (int i = 0; i <= size(); i++) {

			newQueue[i] = queue[i];

		}

		this.queue = newQueue;

	}

}
