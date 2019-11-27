package rpn_lab6;

public class Postfix {

	private StackAsList postFixStack;
	private StackAsList expression;

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
		expression = new StackAsList();
		int n = 0;
		int i = 0;
		int length = pfx.length();

		for (n = length; n > 0; n--) {
			int end = (length - n) + 1;

			String h = pfx.substring(i, end);

			if (!(h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/"))) {
				int value = Integer.parseInt(h);

			}
		}

		return 0;
	}

	public String infixToPostfix(String ifx) throws StackUnderflow, StackOverflow {
		postFixStack = new StackAsList();
		String postFix = "";
		int n = 0;
		int i = 0;
		int length = ifx.length();

		for (n = length; n > 0; n--) {
			int end = (length - n) + 1;

			String h = ifx.substring(i, end);

			if (!(h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/"))) {
				postFix += h;
			}
			if (postFixStack.isEmpty() == false) {
				if (postFixStack.top().equals("") || postFixStack.top().equals("-")|| postFixStack.top().equals("*")
						|| postFixStack.top().equals("/")) {
					if (h.equals("+") || h.equals("-")) {
						postFix += postFixStack.top();
						postFixStack.pop();
						postFixStack.push(h);
					}
				}
			} else if (h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/")) {
				postFixStack.push(h);
			}

			i++;
		}
		return postFix;
	}

}
