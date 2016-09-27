package evaluator;

import java.util.Stack;

public class InfixEvaluator extends Evaluator {
	private Evaluator postfixEvaluator;

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		// TODO Auto-generated method stub.
		// checks if parenthesis are mathematicaly correct
		Evaluator postfixEvaluator = new PostfixEvaluator();
		return postfixEvaluator.evaluate((String) (this
				.convertToPostfix(expression)));
	}

	public Object convertToPostfix(String string) {
		// TODO Auto-generated method stub.
		/*
		 * Here we use a Stack, array of strings, and a string builder
		 */
		Stack<String> operations = new Stack<String>();
		String[] str = string.split(" ");
		StringBuilder build = new StringBuilder();
		int left = 0;
		int right = 0;
		for (int i = 0; i < str.length; i++) {
			//checks if it is an illegal character
			if (!isOperator(str[i]) && !isInteger(str[i])) {
				throw new ArithmeticException();
			}
			//checks if integer
			if (isInteger(str[i])) {
				build.append(str[i]);
				build.append(" ");
			}
			if (isOperator(str[i])) {
				//checks if it is an open parenthesis
				if (priority(str[i]) == 5){
					left ++;
					operations.push(str[i]);
					continue;
				}
				//checks if it is the a closing parenthesis
				if (priority(str[i]) == 4) {
					right ++;
					//it will pop all operations until an open parenthesis is on top
					while (priority(operations.peek()) != 5) {
						build.append(operations.pop());
						build.append(" ");
						if (operations.isEmpty()) {
							throw new ArithmeticException();
						}
					}
					operations.pop();
				} else if (operations.isEmpty()) {
					operations.push(str[i]);
				} else if (operations.peek().equals("(")) {
					operations.push(str[i]);
				} else {
					//if it isn't a parenthesis, it will check priority of operations
					//then it will pop according to priority.
					if (priority(str[i]) <= priority(operations.peek())) {
						while (priority(str[i]) <= priority(operations.peek())
								&& priority(operations.peek()) != 5) {
							build.append(operations.pop());
							build.append(" ");
							if (operations.isEmpty())
								break;
						}
						operations.push(str[i]);
					} else
						operations.push(str[i]);
				}
			}

		}//empties stack onto builder
		while (!operations.isEmpty()) {
			build.append(operations.pop());
			build.append(" ");
		}
		build.deleteCharAt(build.length() - 1);
		String conversion = build.toString();
		if(left!=right){
			throw new ArithmeticException();
		}
		return conversion;
	}
	/*
	 * this is also used in postfix. It checks if the string is an integer
	 */
	@SuppressWarnings("boxing")
	public Boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException numexception) {
			return false;
		}
		return true;
	}

	/*
	 * This helps organize order of operations. priority always goes to parentheses,
	 * then carrying, then multiplication/division, and then finally add/subtract
	 */
	public int priority(String input) {
		int prlevel = 0;
		if (input.equals("+") || input.equals("-")) {
			prlevel = 1;
		} else if (input.equals("*") || input.equals("/")) {
			prlevel = 2;
		} else if (input.equals("^")) {
			prlevel = 3;
		} else if (input.equals(")")) {
			prlevel = 4;
		} else if (input.equals("(")) {
			prlevel = 5;
		}
		return prlevel;
	}
}
