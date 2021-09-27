package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure to allow for
 * unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

  private LLNode <T> topNode;
  private int size;
  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    // TODO: Implement the stack operation for `pop`!
    if(isEmpty()==false)
    {
      T popItem = topNode.getData();
      topNode = topNode.getNext();
      size = size-1;     
      return popItem;
    }
    throw new StackUnderflowException("The Stack is Empty, therefore the Pop Operation cannot be carried out");
  }

  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    // TODO: Implement the stack operation for `top`!
    if(isEmpty()==false)
      return topNode.getData();
    throw new StackUnderflowException("The Stack is Empty, therfore the Top Value cannot be returned");
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    // TODO: Implement the stack operation for `isEmpty`!
    boolean check = false;
    if(topNode==null)
      check = true;
    return check;
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    // TODO: Implement the stack operation for `size`!
    return size;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    // TODO: Implement the stack operation for `push`!
    LLNode <T> newNode = new LLNode<T>(elem);
    newNode.setNext(topNode); 
    topNode = newNode;
    size = size + 1;
  }
}
