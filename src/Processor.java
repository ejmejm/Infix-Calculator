//Author: Edan Meyer
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Processor {

	private static ArrayList<String> numCheck = new ArrayList<>(Arrays.asList( //Used to check if something is a number or not
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."));
	private static HashMap<String, Integer[]> operators = new HashMap<String, Integer[]>(); //Connect operators to their precedence and associativity
	private static double epsilon = 0.00000001; //Values must be within this difference to be equal to each other
	
	static {
		operators.put("(", new Integer[] {9, 0}); //First number assigns precedence
		operators.put("^", new Integer[] {8, 1}); //First number assigns precedence
		operators.put("!", new Integer[] {7, 1}); //Second number is 0 for left association
		operators.put("*", new Integer[] {6, 0}); //And 1 for right association
		operators.put("/", new Integer[] {6, 0});
		operators.put("+", new Integer[] {5, 0});
		operators.put("-", new Integer[] {5, 0});
		operators.put("<", new Integer[] {4, 0});
		operators.put(">", new Integer[] {4, 0});
		operators.put("=", new Integer[] {3, 0});
		operators.put("&", new Integer[] {2, 0});
		operators.put("|", new Integer[] {2, 0});
	}
	
	public static double infixEval(String infix){
		return postfixEval(toPostfix(infix));
	}
	
	public static double postfixEval(Queue<String> tokens){
		MyStack<Double> operands = new MyStack<Double>();
		while(!tokens.isEmpty()){ //For every token
			String currToken = tokens.dequeue();
			if(isNumeric(currToken)){ //If its an operand
				operands.push(Double.valueOf(currToken)); //Push it to the stack
			}else{
				operands.push(evaluate(currToken, operands)); //If its an operator, evaluate the equation
			}
		}
		return operands.pop(); //Return the final value left in the stack
	}
	
	public static Queue<String> toPostfix(String infix){
		String[] tokens = splitInfix(infix);
		MyStack<String> stack = new MyStack<String>();
		MyQueue<String> queue = new MyQueue<String>();
		
		for(int i = 0; i < tokens.length; i++){ //For every part of the equation
			if(isNumeric(tokens[i])){ //If operand
				queue.enqueue(tokens[i]); //Enqueue the operand
			}else if(tokens[i].equals(")")){ //If ")"
				while(!stack.peek().equals("(")) //Loop until it gets to "("
					queue.enqueue(stack.pop()); //Move the top of the stack to the queue
				stack.pop(); //Remove the "(" from the stack
			}else if(operators.containsKey(tokens[i])){
				if(!stack.isEmpty()){
					Integer[] info = operators.get(tokens[i]); //Data on current token
					Integer[] stackInfo = operators.get(stack.peek()); //Data on token at top of stack
					while(stackInfo != null && ((info[1] == 0 && info[0] <= stackInfo[0]) || //If left-assoc and precedence less or equal than top of stack
						(info[1] == 1 && info[0] < stackInfo[0])) && stackInfo[0] != 9){ //Or right-assoc and precedence less than top of stack
						queue.enqueue(stack.pop()); //Move the top of the stack to the queue
						stackInfo = operators.get(stack.peek()); //Update info on top of stack
					}
					stack.push(tokens[i]); //Push current token to stack
				}else{
					stack.push(tokens[i]); //Push current token to stack
				}
			}
		}
		while(!stack.isEmpty()){
			queue.enqueue(stack.pop()); //Pop all stack values into the queue
		}
		return queue;
	}
	
	//Used to evaluate equations and define operator's rules
	private static double evaluate(String operator, MyStack<Double> operands){
		if(operator.equals("^")){ //Exponentiate
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			return Math.pow(operand1, operand2);
		}else if(operator.equals("!")){ //Negate
			double operand = operands.pop();
			if(dEquals(operand, 0.0))
				return 1.0;
			else if(dEquals(operand, 1.0))
				return 0.0;
		}else if(operator.equals("*")){ //Multiply
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			return operand1 * operand2;
		}else if(operator.equals("/")){ //Divide
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			return operand1 / operand2;
		}else if(operator.equals("+")){ //Add
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			return operand1 + operand2;
		}else if(operator.equals("-")){ //Subtract
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			return operand1 - operand2;
		}else if(operator.equals("<")){ //Less than
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			if(!dEquals(operand1, operand2) && operand1 < operand2)
				return 1.0;
			return 0.0;
		}else if(operator.equals(">")){ //Greater than
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			if(!dEquals(operand1, operand2) && operand1 > operand2)
				return 1.0;
			return 0.0;
		}else if(operator.equals("=")){ //Equality
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			if(dEquals(operand1, operand2))
				return 1.0;
			return 0.0;
		}else if(operator.equals("&")){ //AND
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			if(dEquals(operand1, 1.0) && dEquals(operand2, 1.0))
				return 1.0;
			return 0.0;
		}else if(operator.equals("|")){ //OR
			double operand2 = operands.pop();
			double operand1 = operands.pop();
			if(dEquals(operand1, 1.0) || dEquals(operand2, 1.0))
				return 1.0;
			return 0.0;
		}
		return 0.0; //If something goes horribly wrong (or input is invalid)
	}
	
	private static boolean dEquals(double d1, double d2){
		return Math.abs(Math.abs(d1) - Math.abs(d2)) < epsilon ? true : false; //Check for relative equality
	}
	
	//Checks to see if a string is numeric
	private static boolean isNumeric(String check){
		for(String split : check.split(""))
			if(!numCheck.contains(split)) //If any of the tokens are not 1-9 or a .
				return false; //It's not numeric
		return true; //Otherwise it is numeric
	}
	
	private static String[] splitInfix(String infix){
		int index = 0;
		//Makes every input value in the infix notation be separated by a space
		while(index < infix.length()-1){
			if(!numCheck.contains(infix.substring(index, index+1)) && //If its and operator
				!infix.substring(index, index+1).equals(" ") &&
				!infix.substring(index+1, index+2).equals(" ")){ //And it is not followed by a space
					infix = infix.substring(0, index+1) + " " + infix.substring(index+1); //Add a space
			}else if(numCheck.contains(infix.substring(index, index+1)) && //If it's a number
				!numCheck.contains(infix.substring(index+1, index+2)) && //And the next character is not part of the number
				!infix.substring(index+1, index+2).equals(" ")){ //Or a space
					infix = infix.substring(0, index+1) + " " + infix.substring(index+1); //Add a space
			}
			index++;
		}
		return infix.split(" "); //Return an array of each token in the equation
	}
}