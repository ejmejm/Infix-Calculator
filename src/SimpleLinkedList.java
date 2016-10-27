/*
 * 
 * Author: Edan Meyer
 * Partner: None
 * 
 * Used from Lab 4
 * 
 */
//Part 2 of Lab 4, copied from PDF
public interface SimpleLinkedList<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
}