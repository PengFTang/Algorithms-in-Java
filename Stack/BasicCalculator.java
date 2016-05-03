/*
Given an expression string, return the final result of this expression. The expression contains only integer, spaces, +, -, *, /, (, ).
Example: For the expression "2 * 6 - (23 + 7) / (1 + 2)", return 2
 */

public class BasicCalculator {
	
    public int calculate(String str) {
		Deque<Character> op = new ArrayDeque<>();
		Deque<Integer> nums = new ArrayDeque<>();
		
		for(int i = 0; i < str.length(); i++) {
			
			char ch = str.charAt(i);
			
			if(Character.isDigit(ch)) {
				// get num
				int num = ch - '0';
				while(i+1 < str.length() && Character.isDigit(str.charAt(i+1))) {
					num = num * 10 + (str.charAt(++i) - '0');
				}
				
				// update num if previous operator is * or /
				if(!op.isEmpty() && (op.peek()=='*' || op.peek()=='/')) {
					num = compute(nums.pop(), num, op.pop());
				}
				
				// push num into stack
				nums.push(num);
			} else if(ch == '(') {
				op.push(ch);
			} else if(ch == ')') {
				while(op.peek() != '(') {
					updateStack(op, nums);
				}
				op.pop();
			} else if(ch == '*' || ch == '/') {
				op.push(ch);
			} else if(ch == '+' || ch == '-'){
				// update stack by evaluating top two elements in nums stack
				updateStack(op, nums);
				
				op.push(ch);
			}
		}
		
		updateStack(op, nums);
		
		return nums.peek();
	}
	
	private void updateStack(Deque<Character> op, Deque<Integer> nums) {
		if(!op.isEmpty() && op.peek() != '(') {
			int num2 = nums.pop();
			int num1 = nums.pop();
			int newNum = compute(num1, num2, op.pop());
			nums.push(newNum);
		}
	}
	
	private int compute(int a, int b, char operator) {
		switch(operator) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				return a / b;
			default:
				throw new IllegalArgumentException("invalid input!");
		}
	}
	
	public static void main(String[] args) {
		BasicCalculator q = new BasicCalculator();
		
		String s = "2 * 6-(23 + 7) / (1 + 2)";
		
		int res = q.calculate(s);
		System.out.println(res);
	}
}
