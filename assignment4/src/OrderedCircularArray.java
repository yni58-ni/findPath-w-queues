/**
 * This class implements an ordered list using a circular array
 * 
 * @author Nicole Ni
 * @param list:  this array stores the data items of the ordered list
 * @param front: this variable store the position of the first data item in the
 *               list
 * @param rear:  this is the index of the largest data item in the ordered list
 * @param count: this is the number of data items in the list
 */
public class OrderedCircularArray<T> implements SortedListADT<T> {
	private CellData<T>[] list;
	private int front;
	private int rear;
	private int count;

	/**
	 * Constructor
	 */
	public OrderedCircularArray() {
		list = (CellData<T>[]) new CellData[5];
		front = 1;
		rear = 0;
		count = 0;
	}

	/**
	 * Add a new CellData object storing the given id and value to the ordered list
	 */
	public void insert(T id, int value) {
		CellData current = new CellData(id, value);
		int scan = 0;

		if (count == list.length) { // check if the list is full
			expandSize();
		}

		for (scan = front; scan < list.length + front; scan++) {
			int convert = scan % list.length;

			if (list[convert] == null || current.getValue() < list[convert].getValue()) { // find the position where the
																							// new item should be
																							// inserted
				for (int i = count + front; i > scan; i--) { // shift remaining items
					list[i % list.length] = list[(i - 1) % list.length];
				}

				list[convert] = current; // insert the new item
				count++;
				rear = (front + count - 1) % list.length;
				break;
			}
		}
	}

	/**
	 * return the integer value of the CellData object with the specified id
	 */
	public int getValue(T id) throws InvalidDataItemException {
		CellData current = null;

		for (int i = 0; i < list.length; i++) { // find the corresponding CellData
			current = list[i];
			if (current != null && current.getId().equals(id)) {
				break;
			} else if (i == list.length - 1) {
				throw new InvalidDataItemException("Inval idData Item");
			}
		}
		return current.getValue();
	}

	/**
	 * remove from the ordered list the CellData object with the specified id
	 */
	public void remove(T id) throws InvalidDataItemException {
		int i;
		CellData current = null;

		for (i = 0; i < list.length; i++) { // find the corresponding CellData
			current = list[i];
			if (current != null && current.getId().equals(id)) {
				break;
			} else if (i == list.length - 1) {
				throw new InvalidDataItemException("Invalid idData Item");
			}
		}

		for (int scan = i; scan < list.length; scan++) {
			list[scan] = list[(scan + 1) % list.length];
		}
		list[0] = null;
		count--;
		rear = (front + count - 1) % list.length;

	}

	/**
	 * change the value attribute of the CellData object with the given id to the
	 * specified newValue
	 */
	public void changeValue(T id, int newValue) throws InvalidDataItemException {
		remove(id);
		insert(id, newValue);
	}

	/**
	 * remove and return the id or the CellData object in the ordered list with
	 * smallest associated value
	 */
	public T getSmallest() throws EmptyListException {

		if (count == 0) {
			throw new EmptyListException("The list is empty");
		}
		T currentID = list[front].getId();
		list[front] = null;
		front = (front + 1) % list.length;
		count--;
		return currentID;
	}

	/**
	 * return true if the ordered list is empty and it returns false otherwise
	 */
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * return the number of data items in the ordered list
	 */
	public int size() {
		return count;
	}

	/**
	 * return the value of instance variable front
	 */
	public int getFront() {
		return front;
	}

	/**
	 * return the value of instance variable rear
	 */
	public int getRear() {
		return rear;
	}

	/**
	 * expand the list size by 2 times
	 */
	private void expandSize() {
		CellData[] list2 = (CellData<T>[]) new CellData[(list.length) * 2];
		int copied = 0; // number of copied items in list2
		int i = front; // index of entry in list2
		int j = front; // index of entry in list

		while (copied < count) {
			list2[i] = list[j];
			i++;
			j = (j + 1) % list.length;
			copied++;
		}
		rear = (front + count - 1) % list.length;
		list = list2;
	}
}
