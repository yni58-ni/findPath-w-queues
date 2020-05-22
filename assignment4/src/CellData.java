/**
 * This class represent the data items that will be stored in the circular array
 * 
 * @author Nicole Ni
 * @param id:    A reference to the identifier stored in this object
 * @param value: The value of the data item stored in this object
 */
public class CellData<T> {
	private T id;
	private int value;

	/**
	 * Constructor
	 */
	public CellData(T theId, int theValue) {
		id = theId;
		value = theValue;
	}

	/**
	 * Return instance variable value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Return instance variable id
	 */
	public T getId() {
		return id;
	}

	/**
	 * Assigns newValue to value
	 */
	public void setValue(int newValue) {
		value = newValue;
	}

	/**
	 * Assigns the value of newId to id
	 */
	public void setId(T newId) {
		id = newId;
	}
}
