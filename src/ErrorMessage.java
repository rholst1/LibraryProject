
public class ErrorMessage extends Exception {

	public ErrorMessage(String errorMessage) {
		super(errorMessage);
	}

	public static String syntaxError() {
		return "Syntax error: ";
	}

	public static String unknownCommand() {

		return "Unknown command, try again.";
	}
	
	public static String noArgumentsAllowed() {
		
		return "No arguments allowed.";
	}

	public static String itemIdAlreadyExist() {
		return "This ID is already in use. Try again with another ID.";
	}

	public static String itemDoesNotExist() {
		return "This ID does not exist, please enter a valid ID.";
	}

	public static String inStockError() {
		return "Can't return something that isn't lent out, silly. Enter a valid ID.";
	}

	public static String notInStockError() {
		return "This item is currently lent out, please enter a valid ID.";
	}

	public static String inputErrorId() {
		return "Please use only numbers while entering an ID [0-9].";
	}

	public static String inputErrorValue() {
		return "Please use only numbers while entering a value [0-9].";
	}

	public static String inputErrorRuntime() {
		return "Please use only numbers while entering a runtime [0-9].";
	}

	public static String inputErrorRating() {

		return "Please use only decimal numbers separated with a dot '.' while entering a value [0-9.0-9].";
	}

	public static String inputErrorTotalPages() {
		return "Please use only numbers while entering total pages [0-9].";
	}

	public static String inputErrorType() {
		return "Please enter 'movie' or 'book', try again.";
	}

}
