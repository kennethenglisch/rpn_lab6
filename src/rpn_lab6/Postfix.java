package rpn_lab6;

import java.util.Scanner;

public class Postfix {

	private StackAsList postFixStack;
	private StackAsList expression;
	final private Character[] operator = { '+', '-', '*', '/', '^' };
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws StackUnderflow, StackOverflow {
		Postfix fix = new Postfix();
		String post = fix.infixToPostfix(fix.readInfix());
		if (post == "Invalid Expression")
			System.out.println(post);
		else
			System.out.println(fix.evaluate(post));
	}

	public int evaluate(String pfx) throws StackOverflow, StackUnderflow {
		expression = new StackAsList();
		int a = -1;

		for (int n = pfx.length(); n > 0; n--) {
			Character c = pfx.charAt(++a);
			if (Character.isDigit(c)) {
				expression.push(Character.getNumericValue(c));
			} else if ((int) c == checkOperator(c) || (int) c + 6 == 48) {
				int lhs = 0;
				int rhs = 0;

				if (!expression.isEmpty()) {
					rhs = (int) popTop(expression);
					lhs = (int) popTop(expression);
					switch (c) {
					case '+':
						expression.push((lhs + rhs));
						break;
					case '-':
						expression.push((lhs - rhs));
						break;
					case '*':
						expression.push((lhs * rhs));
						break;
					case '/':
						expression.push((rhs / lhs));
						break;
					case '^':
						double base = lhs;
						double expo = rhs;
						expression.push((int) (Math.pow(base, expo)));
						break;
					}
				}
			}
		}
		System.out.println("Evaluation");
		return (int) popTop(expression);
	}

	public String infixToPostfix(String ifx) throws StackUnderflow, StackOverflow {
		postFixStack = new StackAsList();
		String postFix = "";
		int a = -1;
		boolean open = false;
		boolean close = false;

		for (int n = ifx.length(); n > 0; n--) {
			Character c = ifx.charAt(++a);

			if (Character.isLetter(c)) {
				System.out.println("Enter a value for the variable " + c);
				String variable = scanner.nextLine().replaceAll(" ", "");
				c = variable.charAt(0);
				if (Character.isLetter(c))
					return "Invalid Expression";
			}
			if (Character.isDigit(c)) {
				postFix += c;
			} else if (c.equals('(')) {
				postFixStack.push(c);
				open = true;
			} else if (c == ')') {
				close = true;
				while (!postFixStack.isEmpty() && !postFixStack.top().equals('('))
					postFix += popTop(postFixStack);
				if (!postFixStack.isEmpty() && !postFixStack.top().equals('(') || !open)
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
		}
		while (!postFixStack.isEmpty()) {
			postFix += popTop(postFixStack);
		}
		if (open != close)
			return "Invalid Expression";
		System.out.println("Postfix");
		System.out.println("> " + postFix);
		return postFix;
	}

	private int checkOperator(Character c) {

		for (int i = 0; i < operator.length; i++)
			if (c == operator[i]) {
				switch (c) {
				case '+':
					return (int) '+';

				case '-':
					return (int) '-';

				case '*':
					return ((int) '*' + 6);// make the dec value of '*' greater then '+' or '-'

				case '/':
					return (int) '/';

				case '^':
					return (int) '^';
				}
			}
		return -1;

	}

	private Object popTop(StackAsList stack) throws StackUnderflow {
		Object o = stack.top();
		stack.pop();
		return o;
	}

	private String readInfix() throws StackOverflow, StackUnderflow {
		scanner = new Scanner(System.in);
		boolean finished = false;
		System.out.println("Enter an infix expression.");
		System.out.print("> ");
		String ifx = scanner.nextLine().replaceAll(" ", "");

		while (!finished) {
			if (ifx.equals("") || ifx == null) {
				System.out.println(
						"The infix expression you have entered is unvalid. Enter a new one. ");
				System.out.print("> ");
				ifx = scanner.nextLine().replaceAll(" ", "");
			} else
				finished = true;
		}
		return ifx;
	}

	/*
	 * Method for multi digit numbers, not yet implemented. Needs to be implemented
	 * into an iterator of a String that takes the current char and the next char
	 * and hand it to this method as parameters. 
	 * Problems: 
	 * - could cause an OutOfBounce Exception for the char next when there is no guard
	 * - returns a String of the multi digit number that is
	 * 	splittet into single digit number term which needs to be written in the same
	 *	position where the multi digit number was
	 */
	private String multiDigit(char current, char next) {
		int count = 0;
		String infix = "";

		if (Character.isDigit(current) && Character.isDigit(next)) {
			String multi = multiDigit(current, next);
		}
		String digit = String.valueOf(Character.getNumericValue(current)) + (Character.getNumericValue(next));
		int number = Integer.parseInt(digit);
		while (number > 9) {
			number -= 9;
			count++;
		}
		infix = "(9" + "*" + count + "+" + number + ")";
		return infix;
	}

}
