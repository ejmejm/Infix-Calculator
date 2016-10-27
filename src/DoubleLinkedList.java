//Author: Edan Meyer
//Part 2 Obtained from Lab 5 PDF
//Used from Lab 5
public interface DoubleLinkedList<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
} 