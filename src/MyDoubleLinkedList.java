//Author: Edan Meyer
//Used from Lab 5
public class MyDoubleLinkedList<AnyType> implements DoubleLinkedList<AnyType>{
	
	//Part 3
	private MyDoubleNode<AnyType> head = null;	
	private MyDoubleNode<AnyType> tail = null;
	
	//Added for Lab 7 part 2
	public AnyType getFirstElement(){
		return head.next.data;
	}

	//Added for Lab 7 part 2
	public AnyType getLastElement(){
		return tail.prev.data;
	}
	
	//Part 3
	public MyDoubleLinkedList(){
		head = new MyDoubleNode<AnyType>();
		tail = new MyDoubleNode<AnyType>();
		head.next = tail;
		head.prev = null;
		tail.next = null;
		tail.prev = head;
	}

	@Override
	public void insert(AnyType x) {
		if(contains(x))
			return;
		MyDoubleNode<AnyType> first = new MyDoubleNode<AnyType>();
		first.data = x;
		head.next.prev = first;
		first.next = head.next;
		head.next = first;
		first.prev = head;
	}

	//Part 3
	@Override
	public boolean isEmpty() {
		if(head.next == tail)
			return true;
		return false;
	}

	//Part 4
	//Expected runtime of O(n)
	@Override
	public void printList() {
		MyDoubleNode<AnyType> current = head.next;
		System.out.print("[ ");
		while(current.next != null){
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println("]");
	}

	//Part 4
	//Expected runtime of O(n)
	@Override
	public void printListRev() {
		MyDoubleNode<AnyType> current = tail.prev;
		System.out.print("[ ");
		while(current.prev != null){
			System.out.print(current.data + " ");
			current = current.prev;
		}
		System.out.println("]");
	}

	//Part 5
	@Override
	public boolean contains(AnyType x) {
		MyDoubleNode<AnyType> current = head;
		while(current.next != null){
			if(x == current.data)
				return true;
			current = current.next;
		}
		return false;
	}

	//Part 6
	@Override
	public AnyType lookup(AnyType x) {
		MyDoubleNode<AnyType> current = head.next;
		while(current.next != null){
			if(x == current.data)
				return current.data;
			current = current.next;
		}
		return null;
	}

	//Part 7
	@Override
	public void delete(AnyType x) {
		MyDoubleNode<AnyType> current = head.next;
		while(current.next != null){
			if(x == current.data){
				current.next.prev = current.prev;
				current.prev.next = current.next;
			}
			current = current.next;
		}
	}

}
/*
private MyNode<AnyType> baseNode = null;

//Part 3, O(1) runtime
@Override
public void insert(AnyType x) {
	//Part 5
	if(contains(x))
		return;
	//Back to Part 3
	MyNode<AnyType> tempNode = baseNode;
	baseNode = new MyNode<AnyType>();
	baseNode.data = x;
	baseNode.next = tempNode;
}

//Part 7
@Override
public void delete(AnyType x) {
	MyNode<AnyType> prevNode = null;
	MyNode<AnyType> currentNode = baseNode;
	while(currentNode != null){
		if(x == currentNode.data){
			if(prevNode != null)
				prevNode.next = currentNode.next;
			else
				baseNode = currentNode.next;
			return;
		}
		prevNode = currentNode;
		currentNode = currentNode.next;
	}
}

//Part 5
@Override
public boolean contains(AnyType x) {
	MyNode<AnyType> currentNode = baseNode;
	while(currentNode != null){
		if(x == currentNode.data)
			return true;
		currentNode = currentNode.next;
	}
	return false;
}

//Part 6
@Override
public AnyType lookup(AnyType x) {
	MyNode<AnyType> currentNode = baseNode;
	while(currentNode != null){
		if(x == currentNode.data)
			return currentNode.data;
		currentNode = currentNode.next;
	}
	return null;
}

//Part 3
@Override
public boolean isEmpty() {
	if(baseNode == null)
		return true;
	return false;
}

//Part 4, O(n) runtime
@Override
public void printList() {
	MyNode<AnyType> currentNode = baseNode;
	System.out.print("[ ");
	while(currentNode != null){
		System.out.print(currentNode.data + " ");
		currentNode = currentNode.next;
	}
	System.out.println("]");
}
*/