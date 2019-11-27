package rpn_lab6;

public class StackAsList implements Stack {

	private List stackList;
	
	public StackAsList() {
		this.Empty();
	}

	public void push(Object o) throws StackOverflow {

	}

	public void pop() throws StackUnderflow {

	}

	public Object top() throws StackUnderflow {
		return null;
	}

	public boolean isEmpty() {
		return true;
	}

	public void Empty() {
		stackList = new List();
	}

}
