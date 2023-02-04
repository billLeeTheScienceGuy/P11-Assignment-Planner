//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Assignment Queue
// Course:   CS 300 Fall 2021
//
// Author:   Bill Lee
// Email:    blee266@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NA
// Partner Email:   NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NA
// Online Sources:  NA
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Assignments.
 * Guarantees the min-heap invariant, so that the Assignment at the root should
 * have the earliest due date, and children always have a due date after or at
 * the same time as their parent. The root of a non-empty queue is always at
 * index 0 of this array-heap.
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
	private Assignment[] queue; // array min-heap of assignments representing this priority queue
	private int size; // size of this priority queue

	/**
	 * Creates a new empty AssignmentQueue with the given capacity
	 * 
	 * @param capacity Capacity of this AssignmentQueue
	 * @throws IllegalArgumentException with a descriptive error message if the
	 *                                  capacity is not a positive integer
	 */
	public AssignmentQueue(int capacity) {
		// TODO complete this implementation
		if (capacity <= 0) {
			throw new IllegalArgumentException("The input is invalid.");
		}
		this.queue = new Assignment[capacity];
		this.size = 0;

	}

	/**
	 * Checks whether this AssignmentQueue is empty
	 * 
	 * @return {@code true} if this AssignmentQueue is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO complete this implementation
		if (size == 0) {
			return true;
		}
		return false; // default return statement added to resolve compile errors
	}

	/**
	 * Returns the size of this AssignmentQueue
	 * 
	 * @return the size of this AssignmentQueue
	 */
	@Override
	public int size() {
		// TODO complete this implementation
		return this.size;
	}

	/**
	 * Returns the capacity of this AssignmentQueue
	 * 
	 * @return the capacity of this AssignmentQueue
	 */
	public int capacity() {
		return queue.length;
	}

	/**
	 * Removes all elements from this AssignmentQueue
	 */
	@Override
	public void clear() {
		for (int i = 0; i < queue.length; i++) {
			this.queue[i] = null;
		}
		this.size = 0;

	}

	/**
	 * Returns the Assignment at the root of this AssignmentQueue, i.e. the
	 * Assignment with the earliest due date.
	 * 
	 * @return the Assignment in this AssignmentQueue with the earliest due date
	 * @throws NoSuchElementException if this AssignmentQueue is empty
	 */
	@Override
	public Assignment peek() {
		if (queue[0] == null) {
			throw new NoSuchElementException("The queue is empty");
		}
		return queue[0];
	}

	/**
	 * Adds the given Assignment to this AssignmentQueue at the correct position
	 * based on the min-heap ordering. This queue should maintain the min-heap
	 * invariant, so that the Assignment at each index has an earlier or equivalent
	 * due-date than the Assignments in its child nodes. Assignments should be
	 * compared using the Assignment.compareTo() method.
	 * 
	 * @param e Assignment to add to this AssignmentQueue
	 * @throws NullPointerException  if the given Assignment is null
	 * @throws IllegalStateException with a descriptive error message if this
	 *                               AssignmentQueue is full
	 */
	@Override
	public void enqueue(Assignment e) {
		if (e == null) {
			throw new NullPointerException("The element is null");

		}
		if (size == capacity()) {
			throw new IllegalStateException("The queue is full");
		}
		queue[size] = e;
		
		size++;
		percolateUp(size-1);
		

	}

	/**
	 * Removes and returns the Assignment at the root of this AssignmentQueue, i.e.
	 * the Assignment with the earliest due date.
	 * 
	 * @return the Assignment in this AssignmentQueue with the earliest due date
	 * @throws NoSuchElementException with a descriptive error message if this
	 *                                AssignmentQueue is empty
	 */
	@Override
	public Assignment dequeue() {
		// throws IllegalStateException when the  AssignmentQueue is empty.
		if (size() == 0) {
			throw new IllegalStateException("The queue is empty");
		}
		// swap.
		Assignment temp = queue[0];
		queue[0] = queue[size -1];
		queue[size -1] = null;
		// percolate down with index size-1.
		percolateDown(0);
		size--;
		return temp;

	}

	/**
	 * Recursive implementation of percolateDown() method. Restores the min-heap
	 * invariant of a given subtree by percolating its root down the tree. If the
	 * element at the given index does not violate the min-heap invariant (it is due
	 * before its children), then this method does not modify the heap. Otherwise,
	 * if there is a heap violation, then swap the element with the correct child
	 * and continue percolating the element down the heap.
	 * 
	 * @param i index of the element in the heap to percolate downwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	protected void percolateDown(int i) {
		// provide the worst-case runtime complexity of this method assuming that
		// the problem size
		// N is the size of this queue
		// Time complexity: O(log N)
		// if there's only one element, no need to percolate.
		if(size() == 1) {
			return;
		}
		// index needs to be less than size.
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException();
		}

		// - get the child indexes
		int left = (2 * i) + 1;
		int right = (2 * i) + 2;
		if (queue[left] == null || queue[right] == null ) {
			return;
		}



		if (left >= size()) {
			// currentIndex has no children - is a leaf, we're done
			return;
		} else if (queue[left].compareTo(queue[i]) < 0) {
			
			// will need to swap with greater of left and right children if right
			// does right exist
			if (right < this.size()) {
				// if it does, swap with greater of left and right
				int larger = (queue[right].compareTo(queue[left]) > 0 ? left : right);
				Assignment temp = queue[larger];
				queue[larger] = queue[i];
				queue[i] = temp;
				percolateDown(larger);
			} else {
				// otherwise swap with left, there is no right child
				Assignment temp = queue[left];
				queue[left] = queue[i];
				queue[i] = temp;
				percolateDown(left);
			}
		} else if (right < this.size() && queue[right].compareTo(queue[i]) < 0) {
			// will need to swap with RIGHT child
			Assignment temp = queue[right];
			queue[right] = queue[i];
			queue[i] = temp;
			percolateDown(right);
		}
		// no swap, we're done
		return;
	}

	// TODO complete this implementation. This method MUST be implemented
	// recursively

	/**
	 * Recursive implementation of percolateUp() method. Restores the min-heap
	 * invariant of the tree by percolating a leaf up the tree. If the element at
	 * the given index does not violate the min-heap invariant (it occurs after its
	 * parent), then this method does not modify the heap. Otherwise, if there is a
	 * heap violation, swap the element with its parent and continue percolating the
	 * element up the heap.
	 * 
	 * @param i index of the element in the heap to percolate upwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	protected void percolateUp(int i) throws IndexOutOfBoundsException {
		// TODO provide the worst-case runtime complexity of this method assuming that
		// the problem size
		// N is the size of this queue
		// Time complexity: O(log N)

		
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException("Index is not within the right range.");
		}
		// if size is 0, no need to percolate.
		if (size == 0) {
			return;
		}
		// if i is 0, no need to percolate.
		if (i == 0) {
			return;
		}
		
		int parentIndex = (i - 1) / 2;
		
		// check Data against parent's value
		if (queue[i].compareTo(queue[parentIndex]) < 0) {

			// if Data < parent, swap
			Assignment temp = queue[parentIndex];
			queue[parentIndex] = queue[i];
			queue[i] = temp;

			percolateUp(parentIndex);
		}
		return;
	}

	/**
	 * Returns a deep copy of this AssignmentQueue containing all of its elements in
	 * the same order. This method does not return the deepest copy, meaning that
	 * you do not need to duplicate assignments. Only the instance of the heap
	 * (including the array and its size) will be duplicated.
	 * 
	 * @return a deep copy of this AssignmentQueue. The returned new assignment
	 *         queue has the same length and size as this queue.
	 */
	public AssignmentQueue deepCopy() {
		AssignmentQueue copy = new AssignmentQueue(size);
		// for-loop and copy all the indexes.
		for (int i = 0; i < this.size(); i++) {
			copy.enqueue(queue[i]);
		}
		return copy;
	}

	/**
	 * Returns a String representing this AssignmentQueue, where each element
	 * (assignment) of the queue is listed on a separate line, in order from
	 * earliest to latest.
	 * 
	 * @see Assignment#toString()
	 * @see AssignmentIterator
	 * @return a String representing this AssignmentQueue
	 */
	public String toString() {
		StringBuilder val = new StringBuilder();

		for (Assignment a : this) {
			val.append(a).append("\n");
		}

		return val.toString();
	}

	/**
	 * Returns an Iterator for this AssignmentQueue which proceeds from the earliest
	 * to the latest Assignment in the queue.
	 * 
	 * @see AssignmentIterator
	 * @return an Iterator for this AssignmentQueue
	 */
	@Override
	public Iterator<Assignment> iterator() {
		return new AssignmentIterator(this);
	}
}
