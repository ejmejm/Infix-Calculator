//Author: Edan Meyer
//Used from Lab 7
public class MyQueue<AnyType> implements Queue<AnyType>{

	//Part 2
	MyDoubleLinkedList<AnyType> list = new MyDoubleLinkedList<AnyType>();
	
	//Part 2
	public MyQueue(){
		list = new MyDoubleLinkedList<AnyType>();
	}
	
	//Part 4
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	//Part 3
	@Override
	public void enqueue(AnyType x) {
		list.insert(x);
	}

	//Part 4
	@Override
	public AnyType dequeue() {
		if(list.isEmpty()){
			return null;
		}else{
			AnyType last = list.getLastElement();
			list.delete(last);
			return last;
		}
	}

	//Part 5
	@Override
	public AnyType peek() {
		return list.getLastElement();
	}

}
