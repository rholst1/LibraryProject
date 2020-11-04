
public class Item implements ILibrary, Comparable {

	protected int id;
	protected String title;
	protected int value;
	protected String typeOfItem;

	protected boolean borrowedToCustomer = false;
	protected Customer customerLentTo = new Customer(EMPTY_STRING, EMPTY_STRING, EMPTY_INT);

	public boolean isBorrowedToCustomer() {
		return borrowedToCustomer;
	}

	public void setBorrowedToCustomer(boolean borrowedToCustomer) {
		this.borrowedToCustomer = borrowedToCustomer;
	}

	public String getCustomerLentToName() {
		return customerLentTo.getName();
	}

	public String getCustomerLentToPhoneNumber() {
		return customerLentTo.getPhoneNumber();
	}

	public Customer getCustomerLentTo() {
		return customerLentTo;
	}

	public void setCustomerLentTo(String customerName, String customerPhoneNumber, int idOfBorrowedItem) {
		customerLentTo.setName(customerName);
		customerLentTo.setPhoneNumber(customerPhoneNumber);
		customerLentTo.setIdOfBorrowedItem(idOfBorrowedItem);
	}

	public String getTypeOfItem() {
		return typeOfItem;
	}

	public void setTypeOfItem(String typeOfItem) {
		this.typeOfItem = typeOfItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toStringList() {
		String formatedString = String.format("ID: %d | Type: %s | Title: %s", this.id, this.typeOfItem, this.title);
		if (borrowedToCustomer) {
			formatedString += customerLentTo.toString();
		} else if (!borrowedToCustomer) {
			return formatedString += IN_STOCK;
		}
		return formatedString;
	}

	public Item() {
		super();
	}

	@Override
	public int compareTo(Object o) {
        Item i = (Item) o; 
        return this.id - i.id ;
		
	}

}
