package rpn_lab6;

public class TestStackAsList {

	private StackAsList myList;
	
	public TestStackAsList() throws StackOverflow, StackUnderflow
	{
		// create the list
		myList = new StackAsList();
		
		test();
	}
	
	public static void main (String args[]) throws StackOverflow, StackUnderflow
	{
		new TestStackAsList();
	}
	
	/**
	 * Test cases:
	 * 
	 * -> add Objects to the list
	 * -> pop Objects from the list
	 * -> print out List
	 * -> get top element of the list
	 * @throws StackOverflow 
	 * 
	 * @note printing out will print out the memory address instead of the Object
	 * @note fixed the error above
	 */

	
	private void test() throws StackOverflow, StackUnderflow
	{
		// check if it is empty
		if (myList.isEmpty()) 
			System.out.println("Yeah, the list is empty!\n");
		else
			System.out.println("Oops, this list should be empty!\n");
		
		// create Objects
		int i = 42;
		int j = 4711;
		String foo = "foo";
		String bar = "bar";
		
		// printing out the empty list
		System.out.println("Expected Output -> [ Top:  :Bottom ]");
		System.out.println("Actual Output   -> " + myList.toString() + "\n");

		myList.push(i);
		myList.push(j);
		
		System.out.println("Expected Output -> [ Top: 4711, 42 :Bottom ]");
		System.out.println("Actual Output   -> " + myList.toString() + "\n");
		// the top element should be j = 4711
		System.out.println(topElement(j) + "\n");
		
		myList.push(foo);
		myList.push(bar);
		
		System.out.println("Expected Output -> [ Top: bar, foo, 4711, 42 :Bottom ]");
		System.out.println("Actual Output   -> " + myList.toString() + "\n");
		System.out.println(topElement(bar) + "\n");
		
		// pop an element from the list
		myList.pop();
		
		System.out.println("Expected Output -> [ Top: foo, 4711, 42 :Bottom ]");
		System.out.println("Actual Output   -> " + myList.toString() + "\n");
		System.out.println(topElement(foo) + "\n");
		
		myList.pop();
		
		System.out.println("Expected Output -> [ Top: 4711, 42 :Bottom ]");
		System.out.println("Actual Output   -> " + myList.toString() + "\n");
		System.out.println(topElement(j) + "\n");
		
		// check if it is empty
		if (myList.isEmpty()) 
			System.out.println("Oops, this list should not be empty!\n");
		else
			System.out.println("Yeah, the list is not empty!\n");
	}
	
	private String topElement(Object obj) throws StackUnderflow 
	{
		if (myList.top().equals(obj))
			return "Yay the top element is the element: " + obj.toString();
		else 
			return "Something went wrong. The actual top element is: " + obj.toString();
		
	}
	

}
