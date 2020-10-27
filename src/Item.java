
public class Item {
	
	protected int id;
	protected String title;
	protected int value;
	protected String typeOfItem;
	
	
	
	
	
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

	public Item() {
		super();
	}



}
