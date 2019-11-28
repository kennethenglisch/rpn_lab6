package rpn_lab6;

public class Postfix {

	private StackAsList postFixStack;
	private StackAsList expression;
	final private Character[] operator = { '+', '-', '*', '/', '^' };

	public static void main(String[] args) throws StackUnderflow, StackOverflow {
		String ifx = "1+2*3";
		String rpn = "";
		Postfix fix = new Postfix();
		rpn = fix.infixToPostfix(ifx);
		System.out.println(rpn);
		System.out.println(fix.evaluate(rpn));
	}

	public int evaluate(String pfx) throws StackOverflow, StackUnderflow {
		expression = new StackAsList();
		int a = 0;
		char op = Character.MIN_VALUE;
		int result = 0;
		String lhs = "";
		String rhs = "";

		for (int n = pfx.length(); n > 0; n--) {

			for (int i = 0; i < operator.length; i++) {
				Character c = pfx.charAt(a);

				if (Character.isLetterOrDigit(c)) {
					expression.push(c);
				} else if (c == operator[i]) {
					switch (c) {
					case '+':
						op = '+';
						break;
					case '-':
						op = '-';
						break;
					case '*':
						op = '*';
						break;
					case '/':
						op = '/';
						break;
					case '^':
						op = '^';
						break;

					}
					if (!postFixStack.isEmpty()) {
						lhs = lhs + popTop(expression);
						if (!postFixStack.isEmpty())
							rhs = rhs + popTop(expression);
						result = calculate(op, Integer.parseInt(lhs), Integer.parseInt(rhs));
						expression.push((char) (result + '0'));
					}
				}
				a++;
			}
		}
		return 0;
	}

	public int calculate(char c, int a, int b) {
		int eva = 0;
		switch (c) {
		case '+':
			eva = a + b;
		case '-':
			eva = a - b;
		case '*':
			eva = a * b;
		case '/':
			eva = a / b;
		case '^':
			double base = a;
			double expo = b;
			eva = (int) Math.pow(base, expo);

		}
		return eva;
	}

	public String infixToPostfix(String ifx) throws StackUnderflow, StackOverflow {
		postFixStack = new StackAsList();
		String postFix = "";
		int i = 0;

		for (int n = ifx.length(); n > 0; n--) {

			Character c = ifx.charAt(i);

			if (Character.isLetterOrDigit(c)) {
				postFix += c;
			} else if (c.equals('('))
				postFixStack.push(c);
			else if (c == ')') {
				while (!postFixStack.isEmpty() && !postFixStack.top().equals('('))
					postFix += popTop(postFixStack);
				if (!postFixStack.isEmpty() && !postFixStack.top().equals('('))
					return "Invalid Expression";
				else
					postFixStack.pop();
			} else {
				while (!postFixStack.isEmpty() && checkOperator(c) <= checkOperator((char) postFixStack.top())) {
					if (postFixStack.top().equals('('))
						return "Invalid Expression";
					postFix += popTop(postFixStack);
				}

				postFixStack.push(c);
			}

			i++;
		}

		if (!postFixStack.isEmpty()) {
			postFix += popTop(postFixStack);
		}
		return postFix;
	}

	private int checkOperator(Character c) {
		for (int i = 0; i < operator.length; i++)
			if (c == operator[i]) {
				if (c == '+' || c == '-')
					return 0;
				if (c == '*' || c == '-')
					return 1;
				if (c == '^')
					return 2;
			}
		return -1;
	}

	private Object popTop(StackAsList stack) throws StackUnderflow {
		Object o = stack.top();
		stack.pop();
		return o;
	}

}
