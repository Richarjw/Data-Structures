package evaluator;

import java.util.Stack;

public class PostfixEvaluator extends Evaluator {
	private InfixEvaluator infixEvaluator;

	@Override
	public int evaluate(String expression) throws ArithmeticException {

		Stack<String> stack = new Stack<String>();
		String temp1;
		String temp2;

		String[] strings = expression.split(" ");
		if(expression.length() <= 0 || isInteger(strings[strings.length-1])){
			//If the only thing on the stack is an integer,
			//it will return the integer
			if(strings.length == 1){
				return Integer.parseInt(strings[0]);
			}
			throw new ArithmeticException();
		}
		
		
		for (int i = 0; i < strings.length; i++) {
			//If statement checks if the data is either an operator or integer,
			//If neither, it will throw exception.
			if (!isOperator(strings[i]) && !isInteger(strings[i])){
				throw new ArithmeticException();
			}
			if (!isOperator(strings[i]))
				stack.push(strings[i]);
			else {
				if(stack.size()<2){
					throw new ArithmeticException();
				}
				temp1 = stack.pop();
				temp2 = stack.pop();
				//This exception helps if there is only a single integer
				//so It doesn't try to use an operator on a null value.
				if(temp1 == null || temp2 == null){
					throw new ArithmeticException();
				}
				stack.push(operate(temp2, strings[i], temp1));
			}
		}
		if(stack.size()!= 1){
			throw new ArithmeticException();
		}
		return Integer.parseInt(stack.pop());

	}

	private String operate(String pop, String operator, String pop2)
			throws ArithmeticException {
		// TODO Auto-generated method stub.
		int int1 = Integer.parseInt(pop);
		int int2 = Integer.parseInt(pop2);
		if (operator.equals("+"))
			
			return Integer.toString(int1 + int2);
		if (operator.equals("-"))
			return Integer.toString(int1 - int2);
		if (operator.equals("/")) {
			if (int2 == 0)
				throw new ArithmeticException();
			return Integer.toString(int1 / int2);
		}
		if (operator.equals("*"))
			return Integer.toString(int1 * int2);
		if (operator.equals("^"))
			return Integer.toString((int)Math.pow(int1, int2));
		return null;
	}
	/*
	 * This helper method checks if a string is an integer.
	 * Specifically, we use it to check the last character
	 * in the string.
	 * @param str
	 * @return true if string is integer
	 */
	@SuppressWarnings("boxing")
	public Boolean isInteger(String str){
		try{
			Integer.parseInt(str);
		} catch(NumberFormatException numexception)
		{
			return false;
		}
		return true;
	}
}
