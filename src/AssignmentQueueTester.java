//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Assignment Queue Tester.
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
	/**
	 * Tests the functionality of the constructor for AssignmentQueue Should
	 * implement at least the following tests:
	 *
	 * - Calling the AssignmentQueue with an invalid capacity should throw an
	 * IllegalArgumentException - Calling the AssignmentQueue with a valid capacity
	 * should not throw any errors, and should result in a new AssignmentQueue which
	 * is empty, and has size 0
	 *
	 * @return true if the constructor of AssignmentQueue functions properly
	 * @see AssignmentQueue#AssignmentQueue(int)
	 */
	public static boolean testConstructor() {
		// scenario 1: Calling the AssignmentQueue with an invalid capacity should throw
		// an IllegalArgumentException.

		try {
			AssignmentQueue tester = new AssignmentQueue(0);
			return false;
		} catch (IllegalArgumentException e) {
		}
		// scenario 2:Calling the AssignmentQueue with a valid capacity.
		try {
			AssignmentQueue tester = new AssignmentQueue(1);
		} catch (IllegalArgumentException e) {
			return false;
		}
		AssignmentQueue tester = new AssignmentQueue(1);
		if (tester.size() != 0 || tester.isEmpty() != true) {
			return false;
		}
		return true;
	}

	/**
	 * Tests the functionality of the enqueue() and peek() methods Should implement
	 * at least the following tests:
	 *
	 * - Calling peek on an empty queue should throw a NoSuchElementException -
	 * Calling enqueue on a queue which is empty should add the Assignment, and
	 * increment the size of the queue - Calling enqueue on a non-empty queue should
	 * add the Assignment at the proper position, and increment the size of the
	 * queue. Try add at least 5 assignments - Calling peek on a non-empty queue
	 * should always return the Assignment with the earliest due date - Calling
	 * enqueue on a full queue should throw an IllegalStateException - Calling
	 * enqueue with a null Assignment should throw a NullPointerException
	 *
	 * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function
	 *         properly
	 */
	public static boolean testEnqueue() {
		// s1: Calling peek on an empty queue.
		boolean result = true;
		try {
			try {
				AssignmentQueue emptyTester = new AssignmentQueue(6);
				emptyTester.peek();
				System.out.println("e1");
				result = false;
			} catch (NoSuchElementException e) {
			}

			// s2: Calling enqueue on a queue which is empty.
			AssignmentQueue tester = new AssignmentQueue(6);
			Assignment P1 = new Assignment("P1", 10, 19, 10);
			
			tester.enqueue(P1);
			if (!tester.peek().equals(P1) || tester.size() != 1) {
				System.out.println("e2");
				result = false;
			}

			// s3: Calling enqueue on a non-empty queue && Calling peek on a non-empty queue.
			Assignment P2 = new Assignment("P2", 7, 20, 10);
			Assignment P3 = new Assignment("P3", 7, 21, 9);
			Assignment P4 = new Assignment("P4", 7, 21, 10);
			Assignment P5 = new Assignment("P5", 8, 1, 10);
			Assignment P6 = new Assignment("P6", 9, 1, 10);
			tester.enqueue(P6);
			if (!tester.peek().equals(P6) && tester.size() != 1){
				System.out.println("e1");
				result = false;
			}
			tester.enqueue(P5);
			if (!tester.peek().equals(P5) && tester.size() != 2){
				System.out.println("e2");
				result = false;
			}
			tester.enqueue(P4);
			if (!tester.peek().equals(P4) && tester.size() != 3){
				System.out.println("e3");
				result = false;
			}
			tester.enqueue(P2);
			if (!tester.peek().equals(P2) && tester.size() != 4){
				System.out.println("e4");
				result = false;
			}
			tester.enqueue(P3);
			if (!tester.peek().equals(P2) && tester.size() != 5){
				System.out.println("e5");
				result = false;
			}
			if (!tester.peek().equals(P1) && tester.size() != 6){
				System.out.println("e6");
				result = false;
			}
			// s5: Calling enqueue on a full queue.
			Assignment P7 = new Assignment("P6", 9, 1, 7);
			try {
				tester.enqueue(P7);
				System.out.println("e7");
				result = false;
			} catch (IllegalStateException e) {
			}
			// s6: Calling enqueue with a null Assignment.
			AssignmentQueue tester1 = new AssignmentQueue(5);
			Assignment P8 = null;
			try {
				tester1.enqueue(P8);
				System.out.println("e8");
				result = false;
			} catch (NullPointerException e) {
			}
		} catch (Exception e) {
			System.out.println("e9");
			System.out.println(e.getMessage());
			result = false;
		}
		return result; // default return statement added to resolve compiler errors
	}

	/**
	 * Tests the functionality of dequeue() and peek() methods. The peek() method
	 * must return without removing the assignment with the highest priority in the
	 * queue. The dequeue() method must remove, and return the assignment with the
	 * highest priority in the queue. The size must be decremented by one, each time
	 * the dequeue() method is successfully called. Try to check the edge cases
	 * (calling peek and dequeue on an empty queue, and calling dequeue on a queue
	 * of size one). For normal cases, try to consider dequeuing from a queue whose
	 * size is at least 6. Try to consider cases where percolate-down recurses on
	 * left and right.
	 * 
	 * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function
	 *         properly
	 */
	public static boolean testDequeuePeek() {
		boolean result = true;
		try {
			// s1: peek() method must return without removing the assignment.
			AssignmentQueue tester = new AssignmentQueue(6);
			Assignment P1 = new Assignment("P1", 6, 19, 10);
			Assignment P2 = new Assignment("P2", 7, 20, 10);
			Assignment P3 = new Assignment("P4", 7, 21, 10);
			Assignment P4 = new Assignment("P3", 7, 21, 9);
			
			Assignment P5 = new Assignment("P5", 8, 1, 10);
			Assignment P6 = new Assignment("P6", 9, 1, 10);
			tester.enqueue(P6);
			tester.enqueue(P5);

			tester.enqueue(P4);
			tester.enqueue(P2);
			tester.enqueue(P3);
			tester.enqueue(P1);
			// s2: dequeue() method must remove, and return the assignment with the highest
						// priority.
			if (!tester.peek().equals(P1) && tester.size() != 5){
				System.out.println("d1");
				result = false;
			}
			if (!(tester.dequeue().equals(P1)) && tester.size() != 5) {
				System.out.println("d2");
				result = false;
			}
			if (!(tester.dequeue().equals(P2)) && tester.size() != 4) {
				System.out.println("d3");
				result = false;
			}
			if (!(tester.dequeue().equals(P4)) && tester.size() != 3) {
				System.out.println("d4");
				result = false;
			}
			if (!(tester.dequeue().equals(P3)) && tester.size() != 2) {
				System.out.println("d5");
				result = false;
			}
			if (!(tester.dequeue().equals(P5)) && tester.size() != 1) {
				System.out.println("d6");
				result = false;
			}
			if (!(tester.dequeue().equals(P6)) && tester.size() != 0) {
				System.out.println("d6");
				result = false;
			}
			

			// s3: Edge case, calling peek and dequeue on an empty queue
			try {
				tester.peek();
				System.out.println("d7");
				result = false;
			} catch (NoSuchElementException e) {
			}
			try {
				tester.dequeue();
				System.out.println("d8");
				result = false;
			} catch (IllegalStateException e) {
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result; // default return statement added to resolve compiler errors
	}

	/**
	 * Tests the functionality of the clear() method Should implement at least the
	 * following scenarios: - clear can be called on an empty queue with no errors -
	 * clear can be called on a non-empty queue with no errors - After calling
	 * clear, the queue should contain no Assignments
	 *
	 * @return true if AssignmentQueue.clear() functions properly
	 */
	public static boolean testClear() {
		// s1: clear can be called on an empty queue with no errors.
		try {
			AssignmentQueue emptyTester = new AssignmentQueue(5);
			try {
				emptyTester.clear();
			} catch (Exception e) {
				return false;
			}
			// s2: clear can be called on a non-empty queue with no errors.
			AssignmentQueue tester = new AssignmentQueue(6);
			Assignment P1 = new Assignment("P1", 7, 19, 10);
			Assignment P2 = new Assignment("P2", 6, 20, 10);
			Assignment P3 = new Assignment("P3", 7, 21, 9);
			Assignment P4 = new Assignment("P4", 7, 21, 10);
			Assignment P5 = new Assignment("P5", 8, 1, 10);
			Assignment P6 = new Assignment("P6", 9, 1, 10);
			tester.enqueue(P1);
			tester.enqueue(P2);
			tester.enqueue(P3);
			tester.enqueue(P4);
			tester.enqueue(P5);
			tester.enqueue(P6);
			try {
				tester.clear();
			} catch (Exception e) {
				return false;
			}
			// s3: After calling clear, the queue should contain no Assignments
			tester.clear();
			if (tester.isEmpty() != true || tester.size() != 0) {
				return false;
			}
			try {
				tester.peek();
				return false;
			} catch (NoSuchElementException e) {

			}

		} catch (Exception e) {

		}

		return true; // default return statement added to resolve compiler errors
	}

	/**
	 * Tests all the methods of the AssignmentQueue class
	 * 
	 */
	public static boolean runAllTests() {
		boolean passed = true;
		if (testConstructor() == false) {
			System.out.println("testConstructor Failed");
			passed = false;
		}
		if (testEnqueue() == false) {
			System.out.println("testEnqueue Failed");
			passed = false;
		}
		if (testDequeuePeek() == false) {
			System.out.println("testDequeuePeek Failed");
			passed = false;
		}
		if (testClear() == false) {
			System.out.println("testClear Failed");
			passed = false;

		}
		if (passed == true) {
			System.out.println("All test passed");
		}
		return passed; // default return statement added to resolve compiler errors
	}

	/**
	 * Main method
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		runAllTests();

	}
}
