
public class ErrorMessage {

	public static void syntaxError() {
		System.out.println("Wrong syntax, try again.");
	}

	public static void unknownCommand() {

		System.out.println("Unkown command, try again.");
	}

	public static void itemIdAlreadyExist() {
		System.out.println("This ID is already in use. Try again with another ID.");
	}

	public static void itemDoesNotExist() {
		System.out.println("This ID does not exist, please enter a valid ID.");
	}

	public static void InStockError() {
		System.out.println("Can't return something that isn't lent out, silly. Enter a valid ID.");
	}

	public static void notInStockError() {
		System.out.println("This item is currently lent out, please enter a valid ID.");
	}

	public static void inputErrorId() {
		System.out.println("Please use only numbers while entering an ID [0-9].");
	}

	public static void inputErrorValue() {
		System.out.println("Please use only numbers while entering a value [0-9].");
	}

	public static void inputErrorRuntime() {
		System.out.println("Please use only numbers while entering a runtime [0-9].");
	}

	public static void inputErrorRating() {
		System.out
				.println("Please use only decimal numbers separated with a dot '.' while entering a value [0-9.0-9].");
	}

	public static void inputErrorTotalPages() {
		System.out.println("Please use only numbers while entering total pages [0-9].");
	}

}
