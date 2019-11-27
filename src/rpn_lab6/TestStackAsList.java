package rpn_lab6;

public class TestStackAsList {

	private StackAsList myList;
	
	private Node n1, n2, n3, n4;
	
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
		
		// create the nodes
		createNodes();
		
		// printing put the empty list
		System.out.println(myList.toString());
//		System.out.println(myList.top());
		
		// adding nodes to it
		myList.push(n1);
		myList.push(n2);
		
		System.out.println(myList.toString());
		System.out.println(myList.top());
		
		myList.push(n3);
		myList.push(n4);
		
		System.out.println(myList.toString());
		System.out.println(myList.top());
	}
	
	private void createNodes() 
	{
		n1 = new Node("test");
		n2 = new Node("bla");
		n3 = new Node("foo");
		n4 = new Node("bar");
	}
}
