
public class Book extends Item {
	private int totalPages;
	private String publisher;
	private String typeIdentifier = "b";

	protected Book(int id, String title, int value, int totalPages, String publisher) {
		super();
		this.typeOfItem = typeIdentifier;
		this.id = id;
		this.title = title;
		this.value = value;
		this.totalPages = totalPages;
		this.publisher = publisher;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String bookCsvRecord() {
		return String.format("%s,%d,%s,%d,%d,%s,\n", this.typeIdentifier, this.id, this.title, this.value, this.totalPages, this.publisher);
	}
	
	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		
		int id = Integer.parseInt(values[1]);
		String title = values[2];
		int value = Integer.parseInt(values[3]);
		int totalPages = Integer.parseInt(values[4]);
		String publisher = values[5];
		
		return new Book(id, title, value, totalPages, publisher);
		
	}
	@Override
	public String toString() {
		return String.format("ID: %d | Title: %s | Value: %d | Total pages: %d | Publisher: %s", getId(), getTitle(), getValue(), this.totalPages, this.publisher);
	}
	
}
