package rpn_lab6;

public class Postfix {

	private StackAsList postFixStack;

	public Postfix() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws StackUnderflow, StackOverflow {
		String ifx = "1*2+3";
		String rpn = "";
		Postfix fix = new Postfix();
		rpn = fix.infixToPostfix(ifx);
		System.out.println(rpn);
	}

	public int evaluate(String pfx) {

		return 0;
	}

	public String infixToPostfix(String ifx) throws StackUnderflow, StackOverflow {
		postFixStack = new StackAsList();
		String postFix = "";
		String expression = ifx;
		int n = 0;
		int i = 0;
		int length = expression.length();

		String[] operator = { "+", "-", "*", "/" };

		for (n = length; n > 0; n--) {
			int end = (length - n) + 1;

			String h = expression.substring(i, end);

			if (!postFixStack.isEmpty()) {
				if (postFixStack.top() == "*" || postFixStack.top() == "/" || postFixStack.top() == "+"
						|| postFixStack.top() == "-" && h.equals("+") || h.equals("-")) {
					postFix += postFixStack.top();
					postFixStack.pop();
					postFixStack.push(h);
				} else {
					postFixStack.push(h);
				}
			} else {
				if (!(h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/"))) {
					postFix += h;
				}

			}

			i++;
		}
		return postFix;
	}

}
