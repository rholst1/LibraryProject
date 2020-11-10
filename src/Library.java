import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Library<T> implements ILibrary {

	// private LinkedList<Item> library = new LinkedList();

	private String libraryPath;

	public Library(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public Library() {
		// TODO Auto-generated constructor stub
	}

	public void add(Item item) {
		library.add(item);
	}

	public int size() {
		return library.size();
	}

	public Item get(int index) {
		return library.get(index);
	}

	public String getLibraryPath() {
		return libraryPath;
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public void removeFromInventory(int itemToRemoveIndex) {
		library.remove(itemToRemoveIndex);
	}

	public void writeItems() {

		try {
			FileWriter writer = new FileWriter(libraryPath, false);
			CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);

			for (Item item : library) {
				String type = item.getTypeOfItem();
				if (type.equals(RunProgram.TYPE_MOVIE)) {
					Movie movie = (Movie) item;

					printer.printRecord(movie.getTypeOfItem(), movie.getId(), movie.getTitle(), movie.getValue(),
							movie.getRuntime(), movie.getRating(), movie.isBorrowedToCustomer(),
							movie.getCustomerLentToName(), movie.getCustomerLentToPhoneNumber());
				} else if (type.equals(RunProgram.TYPE_BOOK)) {

					Book book = (Book) item;

					printer.printRecord(book.getTypeOfItem(), book.getId(), book.getTitle(), book.getValue(),
							book.getTotalPages(), book.getPublisher(), book.isBorrowedToCustomer(),
							book.getCustomerLentToName(), book.getCustomerLentToPhoneNumber());
				}

			}
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFile() {

		try {
			Scanner sc = new Scanner(new File(libraryPath));
			String contents = new String();

			while (sc.hasNextLine()) {
				contents = sc.nextLine();
				String typeOfItem = parseItem(contents);

				if (typeOfItem.equals(RunProgram.TYPE_MOVIE)) {

					add((Item) Movie.parseMovie(contents));

				} else if (typeOfItem.equals(RunProgram.TYPE_BOOK)) {

					add((Item) (Book.parseBook(contents)));
				}

			}
			sc.close();
		} catch (FileNotFoundException e) {

		}

	}

	private String parseItem(String csvRecord) {
		String[] values = csvRecord.split(",");
		String typeOfItem = values[0];
		return typeOfItem;
	}

	public void printInventory() {
		sortList();
		System.out.printf("\n*************Current Inventory Status*************");
		for (int i = 0; i < library.size(); i++) {
			Item thisItem = library.get(i);
			System.out.printf("\n" + thisItem.toStringList());
		}
	}

	public boolean validId(String id) throws NumberFormatException {
		int intId = Integer.parseInt(id);

		for (int i = 0; i < library.size(); i++) {
			Item tempItem = library.get(i);
			int tempId = tempItem.getId();
			if (intId == tempId) {
				return true;
			}

		}
		return false;
	}

	public int getIndexFromItemId(String id) throws NumberFormatException {
		int intId = Integer.parseInt(id);
		for (int i = 0; i < library.size(); i++) {
			Item tempItem = library.get(i);
			int tempId = tempItem.getId();
			if (intId == tempId) {
				return i;
			}

		}
		System.out.println("Could not find the item. Enter a valid ID.");
		return -1;
	}
	
	public void sortList() {
		Collections.sort(library);
	}
}
