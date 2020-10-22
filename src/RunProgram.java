import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunProgram {

	private ILibrary<E> library;

	private enum Command {
		LIST, // Prints article number, Title and status
		CHECKOUT, // Lets user add name and phone number to system, and removes item from stock
		CHECKIN, // Adding item back to stock after lending
		REGISTER, // Adding a new item to system
		DEREGISTER, // Removing item from system
		INFO, // Prints out information about item with that article number
		QUIT, // QUits the program
		UNKNOWN; // UNKNOWN COMMAND
	}

	public MovieLibraryManager(String moviesPath) {
		try {
			library = new MovieLibrary(moviesPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No file found at " + moviesPath);
			System.out.println("Exiting");
			System.exit(0);
		}
	}

	public void start() {

		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.QUIT) {
				running = false;
				System.out.println("Exiting.");
				continue;
			} else if (command == Command.UNKNOWN) {
				System.out.println("Unknown command.");
				continue;
			}

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case LIST:
				listCommand(arguments);
				break;
			case CHECKOUT:
				checkoutCommand(arguments);
				break;
			case CHECKIN:
				checkinCommand(arguments);
				break;
			case REGISTER:
				registerCommand(arguments);
				break;
			case DEREGISTER:
				deRegisterCommand(arguments);
				break;
			case INFO:
				infoCommand(arguments);
				break;
			case QUIT:
				quitCommand(arguments);
				break;
			}

		}

		scanner.close();

	}

	private void listCommand(String[] arguments) {
		System.out.println(library);
	}

	private void checkoutCommand(String[] arguments) {

	}

	private void CheckinCommand(String[] arguments) {

	}

	private void deregisterCommand(String[] arguments) {

	}

	private void infoCommand(String[] arguments) {

	}

	private void quitCommand(String[] arguments) {
		System.exit(0);
	}

	private void registerCommand(String[] arguments) {

		String title;
		String publisher;
		int id;
		int value;
		int runtime;
		float rating;
		int totalPages;
		char book = 'b';
		char movie = 'm';
		
		if (arguments[0] == book)
			try {
				title = arguments[1];
				totalPages = Integer.parseInt(arguments[2]);
				publisher = arguments[3];
				id = Integer.parseInt(arguments[4]);
				value = Integer.parseInt(arguments[5]);
			 }
			
		else if	(arguments[0]== movie)
			try {
				title = arguments[1];
				runtime = Integer.parseInt(arguments[2]);
				rating = float.parsefloat(arguments[3]);
				id = Integer.parseInt(arguments[4]);
			}
				
					
				
			}catch(

	Exception e)
	{
		System.out.println("Failed to parse movie attributes from arguments.");
		return;
	}
	E movie = new E(title, runtime, rating);
	R book = new R(title, totalPages, publisher);library.add(movie);
	}

	private void removeCommand(String[] arguments) {
		int index;
		try {
			index = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		}
		library.remove(index);
	}

	private void displayCommand() {
		System.out.println(library);
	}

	private Command parseCommand(String userInput) {
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

	private String[] parseArguments(String userInput) {
		String[] commandAndArguments = userInput.split(" ");
		String[] arguments = new String[commandAndArguments.length - 1];
		for (int i = 1; i < commandAndArguments.length; i++) {
			arguments[i - 1] = commandAndArguments[i];
		}
		return arguments; // contains only the arguments
	}

	public static void main(String[] args) {
		MovieLibraryManager manager = new MovieLibraryManager("movies.csv");
		manager.start();
	}

}
