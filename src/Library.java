import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Library<T extends Item> {

	private LinkedList<Item> library = new LinkedList();

	private String libraryPath;
	private String[] typesOfItems = new String[] { "m", "b" };

	public Library(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public Library() {
		// TODO Auto-generated constructor stub
	}

	public <T> void add(Item item) {
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

	public void writeItems() throws IOException {

		try {
			FileWriter writer = new FileWriter(libraryPath, true);
			CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
			for (Item item : library) {
				String type = item.getTypeOfItem();
				char extraComma = ' ';
				if (type == typesOfItems[0]) {
					Movie movie = (Movie) item;

					printer.printRecord(movie.getTypeOfItem(), movie.getId(), movie.getTitle(), movie.getValue(), movie.getRuntime(), movie.getRating());
				} else if (type == typesOfItems[1]) {
					System.out.println("made it to print book");
					Book book = (Book) item;
					printer.printRecord(book.getTypeOfItem(), book.getId(), book.getTitle(), book.getValue(), book.getTotalPages(), book.getPublisher());
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
				char typeOfItem = parseItem(contents);
				System.out.println("type of item: " + typeOfItem);
				if (typeOfItem == 'm') {
					System.out.println("made it to parsemovie");
					Item movie = (Item) Movie.parseMovie(contents);
					library.add(movie);
				} else if (typeOfItem == 'b') {

					add((Item) (Book.parseBook(contents)));
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

	}

	private char parseItem(String csvRecord) {
		String[] values = csvRecord.split(",");
		char typeOfItem = values[0].charAt(0);
		return typeOfItem;
	}

	public void printInventory() {
		for (int i = 0; i < library.size(); i++) {
			Item tempItem = library.get(i);
			String tempType = tempItem.getTypeOfItem();
			if (tempType == "m") {
				System.out.println(((Movie) tempItem).toString());
			} else if (tempType == "b") {
				System.out.println(((Book) tempItem).toString());
			}
		}
	}
}
