package rpn_lab6;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Postfix {

	private StackAsList postFixStack;
	private StackAsList expression;
	final private Character[] operator = { '+', '-', '*', '/', '^' };

	public static void main(String[] args) throws StackUnderflow, StackOverflow {
		/*String ifx = "1+2+3/4+5+6*(7+8)";
		String rpn = "";
		
		rpn = fix.infixToPostfix(ifx);
		System.out.println(rpn);
		System.out.println(fix.evaluate(rpn));*/
		Postfix fix = new Postfix();
		System.out.println(fix.evaluate(fix.infixToPostfix(fix.readInfix())));
	}

	public int evaluate(String pfx) throws StackOverflow, StackUnderflow {
		expression = new StackAsList();
		int a = -1;

		for (int n = pfx.length(); n > 0; n--) {
			Character c = pfx.charAt(++a);
			if (Character.isLetterOrDigit(c)) {
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

		for (int n = ifx.length(); n > 0; n--) {

			Character c = ifx.charAt(++a);

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
		}
		while (!postFixStack.isEmpty()) {
			postFix += popTop(postFixStack);
		}
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
	
	private String readInfix() throws StackOverflow, StackUnderflow 
	{
		Scanner scanner = new Scanner(System.in);
		boolean finished = false;
		System.out.println("Enter an infix expression.");
		System.out.print("> ");
		String ifx = scanner.nextLine().replaceAll(" ", "");
		
		while (!finished) 
		{
			if (ifx.matches(".*[a-zA-Z].*") || ifx.equals("") || ifx == null)
			{
				System.out.println("The infix expression you have entered is unvalid. You should not use any letters. Enter a new one. ");
				System.out.print("> ");
				ifx = scanner.nextLine().replaceAll(" ", "");
			}
			else finished = true;
		}
		
		scanner.close();
		
		return ifx;
	}

}
