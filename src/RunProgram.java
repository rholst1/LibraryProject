import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RunProgram {

	public final String TYPE_MOVIE = "m";
	public final String TYPE_BOOK = "b";

	String libraryPath = "library.csv";
	Library itemLibrary = new Library(libraryPath);

	Scanner sc = new Scanner(System.in);

	private Commands commandManager = new Commands();

	public void start() {
		itemLibrary.readFile();
		
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		displayCommand();
		while (running) {
			System.out.print("Please enter a command:> ");
			String userInput = scanner.nextLine();

			handleCommand(commandManager.parseCommand(userInput), commandManager.parseArguments(userInput));

		}

	}

	public void handleCommand(Commands.Command userCommand, String arguments) {
		switch (userCommand) {
		case LIST:
			listCommand();
			break;
		case CHECKOUT:
			// checkoutCommand(arguments);
			break;
		case CHECKIN:
			// checkinCommand(arguments);
			break;
		case REGISTER:
			registerCommand();

			break;
		case DEREGISTER:
			deRegisterCommand(arguments);
			break;
		case INFO:
			infoCommand(arguments);
			break;
		case QUIT:
			// quitCommand(arguments);
			break;
		}
	}

//	private void registerCommand(String[] arguments) {
//
//		String title;
//		String publisher;
//		int id;
//		int value;
//		int runtime;
//		float rating;
//		int totalPages;
//		char book = 'b';
//		char movie = 'm';
//		
//		if (arguments[0] == book) {
//			try {
//				title = arguments[1];
//				totalPages = Integer.parseInt(arguments[2]);
//				publisher = arguments[3];
//				id = Integer.parseInt(arguments[4]);
//				value = Integer.parseInt(arguments[5]);
//			 }
//			
//		else if	(arguments[0]== movie) {
//			try {
//				title = arguments[1];
//				runtime = Integer.parseInt(arguments[2]);
//				rating = float.parsefloat(arguments[3]);
//				id = Integer.parseInt(arguments[4]);
//				
//		}catch(Exception e){
//	
//		System.out.println("Failed to parse movie attributes from arguments.");
//		return;
//	}
//	E movie = new E(title, runtime, rating);
//	R book = new R(title, totalPages, publisher);
//	library.add(movie);
//	}

	private void listCommand() {
		itemLibrary.printInventory();
	}

	private void removeCommand(String[] arguments) {
		int index;
		try {
			index = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		}

	}

	private void registerCommand() {
		System.out.print("\nEnter type: 'm' for Movie or 'b' for Book");

		char type = sc.nextLine().charAt(0);

		System.out.print("\nEnter ID: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("\nEnter Title: ");
		String title = sc.nextLine();
		System.out.print("\nEnter Value: ");
		int value = Integer.parseInt(sc.nextLine());

		if (type == 'm') {
			System.out.print("\nEnter Runtime: ");
			int runtime = Integer.parseInt(sc.nextLine());
			System.out.println(runtime);
			System.out.print("\nEnter Rating: ");
			float rating = Float.parseFloat(sc.nextLine());

			System.out.println(rating);
			Movie userInputMovie = new Movie(id, title, value, runtime, rating);
			itemLibrary.add(userInputMovie);
		} else if (type == 'b') {
			System.out.println("\nEnter Total pages: ");
			int totalPages = Integer.parseInt(sc.nextLine());
			System.out.println("\nEnter publisher: ");
			String publisher = sc.nextLine();
			Book userInputBook = new Book(id, title, value, totalPages, publisher);
			itemLibrary.add(userInputBook);
		}
		try {
			itemLibrary.writeItems();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void deRegisterCommand(String id) {

		int itemToRemoveIndex = itemLibrary.searchLibrary(id);
		if (itemToRemoveIndex >= 0) {
			itemLibrary.removeFromInventory(itemToRemoveIndex);
		}

	}

	private void infoCommand(String id) {

		Item thisItem = itemLibrary.get(itemLibrary.searchLibrary(id));
		String thisTypeOfItem = thisItem.getTypeOfItem();
		if (thisTypeOfItem.equals(TYPE_MOVIE)) {
			Movie thisItemMovie = (Movie) thisItem;
			System.out.println(thisItemMovie.toString());
		} else if (thisTypeOfItem.equals(TYPE_BOOK)) {
			Book thisItemBook = (Book) thisItem;
			System.out.println(thisItemBook.toString());
		}
	}

	private void displayCommand() {
		System.out.printf(
				"Please enter one of following commands:\nlist - list all the items in the inventory\ncheckout <id number>\ncheckin <id number>\nregister - start a dialog to add a new item to the inventory.\nderegister <id number>\ninfo <id number>\nquit or exit\n");
	}

}
