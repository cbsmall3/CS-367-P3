
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter
// File:             PriorityQueue.java
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * PriorityQueue implemented as a Binary Heap backed by an array. Implements
 * QueueADT. Each entry in the PriorityQueue is of type PriorityQueueNode<E>.
 * First element is array[1]
 * 
 * @author CS367
 *
 * @param <E>
 */
public class PriorityQueue<E> implements QueueADT<PriorityQueueItem<E>> {
	private final int DEFAULT_CAPACITY = 100;

	// Number of elements in heap
	private int currentSize;

	/**
	 * The heap array. First element is array[1]
	 */

	private PriorityQueueItem<E>[] array;

	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		this.currentSize = 0;

		// the below code initializes the array.. similar code used for
		// expanding.

		this.array = (PriorityQueueItem<E>[]) Array
				.newInstance(PriorityQueueItem.class, DEFAULT_CAPACITY + 1);
	}

	/**
	 * Copy constructor
	 * 
	 * @param pq
	 *            PriorityQueue object to be copied
	 */
	public PriorityQueue(PriorityQueue<E> pq) {
		this.currentSize = pq.currentSize;
		this.array = Arrays.copyOf(pq.array, currentSize + 1);
	}

	/**
	 * Adds an item to this PriorityQueue. If array is full, double the array
	 * size.
	 * 
	 * @param item
	 *            object of type PriorityQueueItem<E>.
	 */
	public void enqueue(PriorityQueueItem<E> item) {
		// TODO write appropriate code
		// Check if array is full - double if necessary

		// Check all nodes and find if one with equal priority exists.
		// Add to the existing node's list if it does

		// Else create new node with value added to list and percolate it up

		// expands array when number of items is filled from index 1 to end of
		// priority queue

		// expands array size if max number of items added

		if (!(size() < array.length - 1)) {

			this.doubleArray();
		}

		if (isEmpty()) {

			currentSize++;
			array[currentSize] = item;

		} else {

			int count = 1;
			boolean done = false;

			// iterator used to check for a match between current priority queue
			// items and new item

			PriorityQueueIterator<E> itr = iterator();

			while (itr.hasNext() && !done) {

				if (itr.next().compareTo(item) == 0) {

					done = true;

				} else {
					count++;
				}

			}

			// If priority match found, the item at that position has the new
			// item
			// queue contents copied into it else...

			if (done) {

				while (!item.getList().isEmpty()) {

					array[count].add(item.getList().dequeue());

				}

				// ...new item is added to the end of the priority queue and
				// percolated up

			} else {

				currentSize++;
				array[currentSize] = item;
				this.percolateUp();

			}

		}

	}

	/**
	 * Returns the number of items in this PriorityQueue.
	 * 
	 * @return the number of items in this PriorityQueue.
	 */
	public int size() {

		return currentSize;
	}

	/**
	 * Returns a PriorityQueueIterator. The iterator should return the
	 * PriorityQueueItems in order of decreasing priority.
	 * 
	 * @return iterator over the elements in this PriorityQueue
	 */
	public PriorityQueueIterator<E> iterator() {

		return new PriorityQueueIterator<E>(this);
	}

	/**
	 * Returns the largest item in the priority queue.
	 * 
	 * @return the largest priority item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	@Override
	public PriorityQueueItem<E> peek() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return array[1];
	}

	/**
	 * Removes and returns the largest item in the priority queue. Switch last
	 * element with removed element, and percolate down.
	 * 
	 * @return the largest item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	@Override
	public PriorityQueueItem<E> dequeue() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		PriorityQueueItem<E> item = array[1];

		// last item moved to the front and last
		// set to null and current number of items
		// decremented just prior to reordering

		// Percolate down from top

		this.percolateDown(1);

		currentSize--;

		return item;
	}

	/**
	 * Heapify Establish heap order property from an arbitrary arrangement of
	 * items. ie, initial array that does not satisfy heap property. Runs in
	 * linear time.
	 */
	private void buildHeap() {

		int i = 1;
		// starting position

		int count = 0;
		// used to count number of no-swaps below

		int max = (size() - (size() % 2)) / 2;
		// "max" is index of highest parent node based on number of items
		// on the list

		boolean done = false;
		// "done' is used to determine whether heapification needs
		// repeat or not

		if (max > 0) {

			while (!done) {

				while (!(i > max)) {

					// checks left child if node index is not greater than max

					if (!(2 * i + 1 > size())) {

						// swap occurs if right child has higher priority
						// than current parent node

						if (array[2 * i + 1].compareTo(array[i]) == 1) {

							array[0] = array[i];
							array[i] = array[2 * i + 1];
							array[2 * i + 1] = array[0];

						} else {
							count++; // if no=swap, then increment count by one
						}
					}

					// checks left child if node index is not greater than max

					if (!(2 * i > size())) {

						// swap occurs if left child has higher priority
						// than current parent node

						if (array[2 * i].compareTo(array[i]) == 1) {

							array[0] = array[i];
							array[i] = array[2 * i];
							array[2 * i] = array[0];

						} else {
							count++; // if no=swap, then increment count by one
						}
					}

					i++;

				}

				// if no-swap count is equal or greater to number of items minus
				// one
				// percolate down stops otherwise continues

				if (!(count < size() - 1)) {
					done = true;
				} else {
					i = 1;
				}

			}

		}

	}

	/**
	 * Make this PriorityQueue empty.
	 */

	public void clear() {

		while (this.size() > 0) {
			this.dequeue();
		}

	}

	/**
	 * Internal method to percolate down in the heap.
	 * <a href="https://en.wikipedia.org/wiki/Binary_heap#Extract">Wiki</a>}
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {

		int i = hole;

		int max = (size() + 1 - hole - ((size() + 1 - hole) % 2)) / 2;

		if (max > 0) {

			array[1] = array[size()];

			while (i < max) {

				// if left and right child are equal or less to max...

				if (!(2 * i > size()) && !(2 * i + 1 > size())) {

					// ...current node is swapped with left child if has equal
					// or
					// higher priority than right child or...

					if (array[2 * i].compareTo(array[2 * i + 1]) >= 0) {

						array[0] = array[i];
						array[i] = array[2 * i];
						array[2 * i] = array[0];
						i = 2 * i;

						// ...current node is swapped with right child if it has
						// higher priority than the left node

					} else if (array[2 * i].compareTo(array[2 * i + 1]) == -1) {

						array[0] = array[i];
						array[i] = array[2 * i + 1];
						array[2 * i + 1] = array[0];
						i = 2 * i + 1;

					}

					// if only left child is equal or less to the number of
					// items
					// on the list...

				} else if (!(2 * i > size()) && (2 * i + 1 > size())) {

					// ...current node is swapped with left child if latter
					// priority is higher

					if (array[2 * i].compareTo(array[i]) == 1) {

						array[0] = array[i];
						array[i] = array[2 * i];
						array[2 * i] = array[0];
						i = 2 * i;

					}

				}

				i++;

			}

			buildHeap();

		}

	}

	/**
	 * Internal method to expand array.
	 */

	@SuppressWarnings("unchecked")
	private void doubleArray() {
		PriorityQueueItem<E>[] newArray;

		newArray = (PriorityQueueItem<E>[]) Array.newInstance(
				PriorityQueueItem.class, array.length + DEFAULT_CAPACITY);

		for (int i = 1; i <= size(); i++) {

			newArray[i] = array[i];

		}

		array = newArray;
	}

	@Override
	public boolean isEmpty() {

		return size() == 0;

	}

	private void percolateUp() {

		int curr = currentSize; // starting node
		int next = (curr - (curr % 2)) / 2; // next node to compare calculated
											// from current node
		boolean done = false;

		while (!(next < 1) && !done) {

			if (array[curr].compareTo(array[next]) == 1) {

				// swap occurs if current node has higher priority than next
				// node else...

				array[0] = array[next];
				array[next] = array[curr];
				array[curr] = array[0];
				curr = next;
				next = (curr - (curr) % 2) / 2;

			} else {
				done = true; // ...loop terminated
			}

		}

	}

}
