//Author: Edan Meyer
//Used from Lab 6
public class MyStack<AnyType> implements Stack<AnyType>{

	//Part 2
	MyLinkedList<AnyType> list;
	
	//Part 2
	public MyStack(){
		list = new MyLinkedList<AnyType>();
	}
	
	//Part 4
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	//Part 3
	@Override
	public void push(AnyType x) {
		list.insert(x);
	}
	
	//Part 4
	@Override
	public AnyType pop() {
		if(isEmpty()){
			return null;
		}else{
			AnyType headNode = list.getHeadNode().data;
			list.delete(headNode);
			return headNode;
		}
	}

	//Part 5
	@Override
	public AnyType peek() {
		if(isEmpty())
			return null;
		return list.getHeadNode().data;
	}

}
