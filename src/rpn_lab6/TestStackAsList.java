package rpn_lab6;

public class TestStackAsList {

	private StackAsList myList;
	
	public TestStackAsList() throws StackOverflow, StackUnderflow
	{
		testAdd();
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
	 */

	
	private void testAdd() throws StackOverflow, StackUnderflow
	{
		// create the list
		myList = new StackAsList();
		
		// create Objects
		int i = 42;
		int j = 4711;
		String foo = "foo";
		String bar = "bar";
		
		// printing put the empty list
		System.out.println(myList.toString());

		myList.push(i);
		myList.push(j);
		
		System.out.println(myList.toString());
		System.out.println("Top Element: " + myList.top());
		
		myList.push(foo);
		myList.push(bar);
		
		System.out.println(myList.toString());
		System.out.println("Top Element: " + myList.top());
		
		// pop an element from the list
		myList.pop();
		
		System.out.println(myList.toString());
		System.out.println("Top Element: " + myList.top());
		
		myList.pop();
		
		System.out.println(myList.toString());
		System.out.println("Top Element: " + myList.top());
		
	}
	

}
