
public class Commands {
	
	private Library itemLibrary;
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


}
