import java.util.LinkedList;

public interface ILibrary {

	String TYPE_MOVIE = "movie";
	String TYPE_BOOK = "book";
	String EMPTY_STRING = "";
	String IN_STOCK = " (In stock)";
	int EMPTY_INT = -1;

	String libraryPath = "library.csv";
	Library itemLibrary = new Library(libraryPath);

	LinkedList<Item> library = new LinkedList<Item>();

}
