import java.util.Scanner;
import java.text.ParseException;
import java.lang.ArrayIndexOutOfBoundsException;

public class Commands implements ILibrary {

	Scanner sc = new Scanner(System.in);

	public static enum Command {
		LIST, // Prints article number, Title and status
		CHECKOUT, // Lets user add name and phone number to system, and removes item from stock
		CHECKIN, // Adding item back to stock after lending
		REGISTER, // Adding a new item to system
		DEREGISTER, // Removing item from system
		INFO, // Prints out information about item with that article number
		INVALID_ID, QUIT, // QUits the program
		UNKNOWN; // UNKNOWN COMMAND
	}

	public Command parseCommand(String userInput) {
		try {
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
		} catch (ArrayIndexOutOfBoundsException e) {
			return Command.UNKNOWN;
		}

	}

	public String parseArguments(String userInput) {
		String[] commandAndArguments = userInput.split(" ");
		String arguments = new String();
		for (int i = 1; i < commandAndArguments.length; i++) {
			arguments += commandAndArguments[i];
		}
		return arguments;
	}

	public void handleCommand(Commands.Command userCommand, String arguments) {
		try {
			switch (userCommand) {
			case LIST:
				if (arguments.equals(EMPTY_STRING)) {
					listCommand();
				} else {
					System.out.println(ErrorMessage.syntaxError() + ErrorMessage.noArgumentsAllowed());
				}
				break;
			case CHECKOUT:
				if (itemLibrary.validId(arguments)) {
					checkoutCommand(arguments);
				} else {
					System.out.println(ErrorMessage.itemDoesNotExist());
				}
				break;
			case CHECKIN:
				if (itemLibrary.validId(arguments)) {
					checkinCommand(arguments);
				} else {
					System.out.println(ErrorMessage.itemDoesNotExist());
				}
				break;
			case REGISTER:
				if (arguments.equals(EMPTY_STRING)) {
					registerCommand();
				} else {
					System.out.println(ErrorMessage.syntaxError() + ErrorMessage.noArgumentsAllowed());
				}
				break;
			case DEREGISTER:
				if (itemLibrary.validId(arguments)) {
					deRegisterCommand(arguments);
				} else {
					System.out.println(ErrorMessage.itemDoesNotExist());
				}
				break;
			case INFO:
				if (itemLibrary.validId(arguments)) {
					infoCommand(arguments);
				} else {
					System.out.println(ErrorMessage.itemDoesNotExist());
				}
				break;
			case QUIT:
				System.exit(0);
				break;
			case UNKNOWN:
				System.out.println(ErrorMessage.unknownCommand());
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println(ErrorMessage.syntaxError() + ErrorMessage.inputErrorId());
		}
	}

	private void listCommand() {
		itemLibrary.printInventory();
	}

	private void registerCommand() {
		String userInputId = EMPTY_STRING, title = EMPTY_STRING, publisher = EMPTY_STRING;
		int id = EMPTY_INT, value = EMPTY_INT, runtime = EMPTY_INT, totalPages = EMPTY_INT;
		float rating = (float) EMPTY_INT;

		System.out.print("\nEnter type: 'movie' or 'book'");

		String type = sc.nextLine();
		if (type.equals(TYPE_MOVIE) || type.equals(TYPE_BOOK)) {

			System.out.print("\nEnter ID: ");
			try {
				userInputId = sc.nextLine();

				if (itemLibrary.validId(userInputId)) {
					ErrorMessage.itemIdAlreadyExist();
					return;
				}
				id = Integer.parseInt(userInputId);
			} catch (NumberFormatException e) {
				System.out.println(ErrorMessage.inputErrorId());
				return;
			}
			try {
				System.out.print("\nEnter Title: ");
				title = sc.nextLine();
				System.out.print("\nEnter Value: ");
				value = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(ErrorMessage.inputErrorValue());
				return;
			}
			if (type.equals(TYPE_MOVIE)) {
				try {
					System.out.print("\nEnter Runtime: ");
					runtime = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println(ErrorMessage.inputErrorRuntime());
					return;
				}
				try {
					System.out.print("\nEnter Rating: ");
					rating = Float.parseFloat(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println(ErrorMessage.inputErrorRating());
					return;
				}
				Movie userInputMovie = new Movie(id, title, value, runtime, rating);
				itemLibrary.add(userInputMovie);

			} else if (type.equals(TYPE_BOOK)) {
				try {
					System.out.println("\nEnter Total pages: ");
					totalPages = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println(ErrorMessage.inputErrorTotalPages());
					return;
				}
				System.out.println("\nEnter publisher: ");
				publisher = sc.nextLine();

				Book userInputBook = new Book(id, title, value, totalPages, publisher);
				itemLibrary.add(userInputBook);
			}

			itemLibrary.writeItems();

			System.out.printf("Successfully registered %s.", title);

		} else {
			System.out.println(ErrorMessage.inputErrorType());
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
		try {
			Item thisItem = itemLibrary.get(itemLibrary.getIndexFromItemId(id));
			String thisTypeOfItem = thisItem.getTypeOfItem();
			if (thisTypeOfItem.equals(TYPE_MOVIE)) {
				Movie thisItemMovie = (Movie) thisItem;
				System.out.println(thisItemMovie.toString());
			} else if (thisTypeOfItem.equals(TYPE_BOOK)) {
				Book thisItemBook = (Book) thisItem;
				System.out.println(thisItemBook.toString());
			}
		} catch (NumberFormatException e) {
			System.out.println(ErrorMessage.inputErrorId());
		}
	}

	public void displayCommands() {
		System.out.printf(
				"\nPlease enter one of following commands:\nlist - list all the items in the inventory\ncheckout <id number>\ncheckin <id number>\nregister - start a dialog to add a new item to the inventory.\nderegister <id number>\ninfo <id number>\nquit or exit");
	}

	private void checkoutCommand(String argument) {
		int indexOfBorrowedItem = EMPTY_INT, idOfBorrowedItem = EMPTY_INT;

		indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish
		idOfBorrowedItem = Integer.parseInt(argument); // to borrow
		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		if(borrowedItem.isBorrowedToCustomer() == false) {
		System.out.println("Enter customer name: ");
		String customerName = sc.nextLine();
		System.out.println("Enter customer phone number: ");
		String customerPhoneNumber = sc.nextLine();

		borrowedItem.setBorrowedToCustomer(true);
		borrowedItem.setCustomerLentTo(customerName, customerPhoneNumber, idOfBorrowedItem);

		System.out.printf("\nSuccesfully lended %s to %s\n", borrowedItem.getTitle(), customerName);
		itemLibrary.writeItems();
		} else {
			System.out.println(ErrorMessage.notInStockError());
		}
	}

	private void checkinCommand(String argument) {

		int indexOfBorrowedItem = itemLibrary.getIndexFromItemId(argument); // argument = id of the Item customer wish

		Item borrowedItem = itemLibrary.get(indexOfBorrowedItem);
		String customerName = borrowedItem.getCustomerLentToName();
		if (borrowedItem.isBorrowedToCustomer() == true) {
			borrowedItem.setBorrowedToCustomer(false);
			borrowedItem.setCustomerLentTo(EMPTY_STRING, EMPTY_STRING, -1);

			itemLibrary.writeItems();
			System.out.printf("\nSuccesfully returned %s from %s\n", borrowedItem.getTitle(), customerName);
		} else {
			System.out.println(ErrorMessage.inStockError());
		}
	}
}
