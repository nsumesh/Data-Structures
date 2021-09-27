package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;


import evaluator.PostfixEvaluator;

/** An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions. */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /** Constructs an {@link ArithPostfixEvaluator} */
  public ArithPostfixEvaluator() {
    // TODO Initialize to your LinkedStack
    stack = new LinkedStack<Operand<Integer>>();
  }

  /** {@inheritDoc} */
  @Override
  public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
    ArithPostfixParser parser = new ArithPostfixParser(expr);
    for (Token<Integer> token : parser) {
      Type type = token.getType();
      switch (type) {
        case OPERAND:
          // TODO What do we do when we see an operand?
          stack.push(token.getOperand());
          break;
        case OPERATOR:
        Operator <Integer> Temp = token.getOperator();
          // TODO What do we do when we see an operator?
          if(Temp.getNumberOfArguments()==1)
            Temp.setOperand(0, stack.pop());
          else
          {
            Temp.setOperand(1, stack.pop());
            Temp.setOperand(0, stack.pop());
          }
          Operand<Integer> tempValue = Temp.performOperation();
          stack.push(tempValue);
          break;
        default:
          throw new IllegalStateException("Parser returned an invalid Type: " + type);
      }
    }
    
    // TODO What do we return?
    Integer res = stack.pop().getValue();
    if(stack.isEmpty()==false) 
      throw new IllegalPostfixExpressionException();
    return res;
    
  }
}
