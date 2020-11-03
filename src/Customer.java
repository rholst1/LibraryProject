
public class Customer {

	private String name = "";
	private String phoneNumber = "";
	private int idOfBorrowedItem;

	protected Customer(String name, String phoneNumber, int idOfBorrowedItem) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.idOfBorrowedItem = idOfBorrowedItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getIdOfBorrowedItem() {
		return idOfBorrowedItem;
	}

	public void setIdOfBorrowedItem(int idOfBorrowedItem) {
		this.idOfBorrowedItem = idOfBorrowedItem;
	}

	@Override
	public String toString() {
		return String.format("\nBorrowed by: %s, %s", this.name, this.phoneNumber);
	}
}
