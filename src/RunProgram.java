import java.util.Scanner;

public class RunProgram implements ILibrary {

	private Commands commandManager = new Commands();

	public void start() {
		itemLibrary.readFile();

		boolean running = true;
		Scanner sc = new Scanner(System.in);
		initialPrompt();
		while (running) {
			System.out.printf("\nPlease enter a command:> ");
			String userInput = sc.nextLine();

			commandManager.handleCommand(commandManager.parseCommand(userInput),
					commandManager.parseArguments(userInput));

		}

	}

	private void initialPrompt() {
		System.out.printf("\nWelcome!");
		itemLibrary.printInventory();
		System.out.printf("\n*******************Command list*******************");
		commandManager.displayCommands();
		System.out.printf("\n**************************************************");
	}
}
