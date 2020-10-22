import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;																																				

public class Library<T extends Item> {
	
	private LinkedList<Item> library = new LinkedList();
	
	private String libraryPath;
	private String[] typesOfItems = new String[] {"m","b"};
	
	public Library(String libraryPath) {
		this.libraryPath = libraryPath;
	}
	
	public Library() {
		// TODO Auto-generated constructor stub
	}

	public <T> void add(Item newItem) {
		library.add(newItem);
	}
	
	
	public Item get(int index) {
		return library.get(index);
	}
	
	private void writeItems() {
		
		try {
				PrintWriter printWriter = new PrintWriter(libraryPath);
				
					for(Item item : library) {
						String type = item.getTypeOfItem();
						if (type == typesOfItems[0]){
								Movie movie = (Movie)item;
								String csvRecord = movie.movieCsvRecord();
								printWriter.println(csvRecord);
						} else if(type == typesOfItems[1]) {
//							Book book = (Book)item;
//							String csvRecord = book.bookCsvRecord();
//							printWriter.println(csvRecord);
						}
						
					}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
	
