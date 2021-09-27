package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      this.comparator = comparator;
      this.heap = (T[]) new Object[INIT_SIZE];
      this.isMaxHeap = isMaxHeap;
      numElements = 0;
  }

  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   */
  public void bubbleUp(int index) {
    int a = getParentOf(index);
    int temp = bubbleUpHelper(a, index);
    if(temp!=0)
    {
      bubbleUp(temp);
    }
  }

  private int bubbleUpHelper(int a, int b)
  {
    int temp = 0;
    if(compare(heap[a], heap[b])<0)
    {
      swap(b,a);
      temp = a;
    }
    return temp;
  }

  private int getLeftChildOf(int parentIndex)
  {
    return ((2*parentIndex)+1);
  }

  private int getRightChildOf(int parentIndex)
  {
    return ((2*parentIndex)+2);
  }

  private int getParentOf(int childIndex)
  {
    return ((childIndex-1)/2);
  }

  private void swap(int one, int two)
  {
    T temp = heap[one];
    heap[one] = heap[two];
    heap[two] = temp;
  } 

  private void expandCapacity()
  {
    T[] temp = (T[]) new Object[heap.length+1];
    for(int i = 0; i<heap.length;i++)
    {
      temp[i] = heap[i];
    }

    heap = temp;
  }


  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   */
  public void bubbleDown(int index) {
    int a = getLeftChildOf(index);
    int b = getRightChildOf(index);
    int temp = bubbleDownHelper(index, a, b);

    if(temp!=0)
    {
      if(compare(heap[temp], heap[index])>0)
      {
        swap(index, temp);
      }
      bubbleDown(temp);
    }

  }


  private int bubbleDownHelper(int index, int a, int b)
  {
    if(numElements<3)
    {
      if(heap[a]!=null)
        return a;
      
      else if(heap[b]!=null)
        return b;
      
    }

    else if((checkIsValidChildIndexes(a, b) == true) && (heap[a]!=null && heap[b]!=null))
    {
      if(compare(heap[a], heap[b])>0)
        return a;
      else
        return b;
    }

    return 0;
  }

  private boolean checkIsValidChildIndexes(int a, int b)
  {
    boolean check = false;
    if(a<numElements && b<numElements)
      check = true;
    return check;
  }


  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
    if(numElements==0)
      isEmpty = true;
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int size(){
    int size = -100;
    return numElements;
  }

  /**
   * Compare method to implement max/min heap behavior.  It calls the comparae method from the 
   * comparator object and multiply its output by 1 and -1 if max and min heap respectively.
   * TODO: implement the heap compare method
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, negative int otherwise
   */
  public int compare(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
     T data = null;
    if(isEmpty()==true)
      throw new QueueUnderflowException();
    data = heap[0];
    return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeue() throws QueueUnderflowException{
    T data = null;
    if(isEmpty()==true)
      throw new QueueUnderflowException();
    data = heap[0];
    int tmp = numElements-1; 
    numElements = numElements-1;
    swap(0, tmp);
    heap[tmp]= null;

    if(heap[0] !=null)
      bubbleDown(0);
    return data;
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueue(T newElement) {
    if(heap.length >=numElements)
      expandCapacity();
    
    numElements = numElements+1;
    heap[numElements-1] = newElement;
    bubbleUp(numElements-1);

  }
}