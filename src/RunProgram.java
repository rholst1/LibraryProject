import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RunProgram {

	public static final String TYPE_MOVIE = "movie";
	public static final String TYPE_BOOK = "book";
	public static final String EMPTY = "";
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
			checkoutCommand(arguments); 
			break;
		case CHECKIN:
			checkinCommand(arguments);
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
				System.exit(0);
			break;
		}
	}


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
		System.out.print("\nEnter type: 'movie' or 'book'");

		String type = sc.nextLine();
		if (type.equals(TYPE_MOVIE) || type.equals(TYPE_BOOK)) {

			System.out.print("\nEnter ID: ");
			String userInputId = sc.nextLine();
			if (!itemLibrary.isIdAvailable(userInputId)) {
				System.out.println("ID is unavailable, enter a different ID.");
				return;
			}
			int id = Integer.parseInt(userInputId);
			System.out.print("\nEnter Title: ");
			String title = sc.nextLine();
			System.out.print("\nEnter Value: ");
			int value = Integer.parseInt(sc.nextLine());

			if (type.equals(TYPE_MOVIE)) {
				System.out.print("\nEnter Runtime: ");
				int runtime = Integer.parseInt(sc.nextLine());
				System.out.println(runtime);
				System.out.print("\nEnter Rating: ");
				float rating = Float.parseFloat(sc.nextLine());

				System.out.println(rating);
				Movie userInputMovie = new Movie(id, title, value, runtime, rating);
				itemLibrary.add(userInputMovie);
			} else if (type.equals(TYPE_BOOK)) {
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
		}else {
			commandManager.syntaxError();
		}
	}

	private void deRegisterCommand(String id) {

		int itemToRemoveIndex = itemLibrary.getIndexFromItemId(id);
		if (itemToRemoveIndex >= 0) {
			itemLibrary.removeFromInventory(itemToRemoveIndex);
		}

	}

	private void infoCommand(String id) {

		Item thisItem = itemLibrary.get(itemLibrary.getIndexFromItemId(id));
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

	private void checkoutCommand(String argument) {
		int indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish
		int idOfBorrowedItem = Integer.parseInt(argument);															// to borrow
		System.out.println("Enter customer name: ");
		String customerName = sc.nextLine();
		System.out.println("Enter customer phone number: ");
		String customerPhoneNumber = sc.nextLine();

		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		borrowedItem.setBorrowedToCustomer(true);
		borrowedItem.setCustomerLentTo(customerName, customerPhoneNumber, idOfBorrowedItem);
		System.out.println(borrowedItem.toStringList());
		
		try {
			itemLibrary.writeItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void checkinCommand(String argument) {

		int indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish

		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		String customerName = borrowedItem.getCustomerLentToName();
		
		borrowedItem.setBorrowedToCustomer(false);
		borrowedItem.setCustomerLentTo(EMPTY, EMPTY, -1);
			
		try {
			itemLibrary.writeItems();
			System.out.printf("\nSuccefully returned %s from %s\n", borrowedItem.getTitle(), customerName);
		} catch (IOException e) {
	
			e.printStackTrace();
		}
}
}