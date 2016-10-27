/*
 * 
 * Author: Edan Meyer
 * Partner: None
 * 
 * Used from Lab 4
 * 
 */
public class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType>{

	private MyNode<AnyType> baseNode = null;
	
	public MyNode<AnyType> getHeadNode(){
		return baseNode;
	}
	
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

}
