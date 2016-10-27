//Author: Edan Meyer
//Part 1, copied from Lab 7 PDF
public interface Queue<AnyType> {
	public boolean isEmpty();
	public void enqueue(AnyType x);
	public AnyType dequeue();
	public AnyType peek();
}
