import java.util.Scanner;

public class Commands implements ILibrary {
	
	Scanner sc = new Scanner(System.in);
	
	public static enum Command {
		LIST, // Prints article number, Title and status
		CHECKOUT, // Lets user add name and phone number to system, and removes item from stock
		CHECKIN, // Adding item back to stock after lending
		REGISTER, // Adding a new item to system
		DEREGISTER, // Removing item from system
		INFO, // Prints out information about item with that article number
		QUIT, // QUits the program
		UNKNOWN; // UNKNOWN COMMAND
	}

	public Command parseCommand(String userInput) {
		String commandString = userInput.split(" ")[0];
		switch (commandString) {
		case "list":
			return Command.LIST;
		case "checkout":
			return Command.CHECKOUT;
		case "checkin":
			return Command.CHECKIN;
		case "register":
			return Command.REGISTER;
		case "deregister":
			return Command.DEREGISTER;
		case "info":
			return Command.INFO;
		case "quit":
		case "exit":
			return Command.QUIT;
		default:
			return Command.UNKNOWN;
		}
	}

	public String parseArguments(String userInput) {
		String[] commandAndArguments = userInput.split(" ");
		String arguments = new String();
		for (int i = 1; i < commandAndArguments.length; i++) {
			arguments += commandAndArguments[i];
		}
		return arguments; // contains only the arguments
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

	private void registerCommand() {
		System.out.print("\nEnter type: 'movie' or 'book'");

		String type = sc.nextLine();
		if (type.equals(TYPE_MOVIE) || type.equals(TYPE_BOOK)) {

			System.out.print("\nEnter ID: ");
			
			String userInputId = sc.nextLine();
			Integer.parseInt(userInputId);
			
			if (itemLibrary.validId(userInputId)) {
				ErrorMessage.itemIdAlreadyExist();
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
				System.out.print("\nEnter Rating: ");
				float rating = Float.parseFloat(sc.nextLine());

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

			itemLibrary.writeItems();

			System.out.printf("Successfully registered %s.", title);

		} else {
			ErrorMessage.syntaxError();
		}
	}

	private void deRegisterCommand(String id) {

		int itemToRemoveIndex = itemLibrary.getIndexFromItemId(id);
		Item itemToRemoveName = itemLibrary.get(itemToRemoveIndex);
		String itemName = itemToRemoveName.getTitle();
		if (itemLibrary.validId(id)) {
			itemLibrary.removeFromInventory(itemToRemoveIndex);
			itemLibrary.writeItems();
			System.out.printf("\nSuccesfully deregistered %s.", itemName);
		} else
			System.out.printf("\nError: Could not find ID.");

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

	public void displayCommands() {
		System.out.printf(
				"\nPlease enter one of following commands:\nlist - list all the items in the inventory\ncheckout <id number>\ncheckin <id number>\nregister - start a dialog to add a new item to the inventory.\nderegister <id number>\ninfo <id number>\nquit or exit");
	}

	private void checkoutCommand(String argument) {
		int indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish
		int idOfBorrowedItem = Integer.parseInt(argument); // to borrow
		System.out.println("Enter customer name: ");
		String customerName = sc.nextLine();
		System.out.println("Enter customer phone number: ");
		String customerPhoneNumber = sc.nextLine();

		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		borrowedItem.setBorrowedToCustomer(true);
		borrowedItem.setCustomerLentTo(customerName, customerPhoneNumber, idOfBorrowedItem);

		System.out.printf("\nSuccesfully lended %s to %s\n", borrowedItem.getTitle(), customerName);

		itemLibrary.writeItems();

	}

	private void checkinCommand(String argument) {

		int indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish

		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		String customerName = borrowedItem.getCustomerLentToName();

		borrowedItem.setBorrowedToCustomer(false);
		borrowedItem.setCustomerLentTo(EMPTY, EMPTY, -1);

		itemLibrary.writeItems();
		System.out.printf("\nSuccesfully returned %s from %s\n", borrowedItem.getTitle(), customerName);

	}
}
