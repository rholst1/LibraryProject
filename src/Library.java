import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library<T> implements ILibrary<E> {
	
	private List<E> movies;
	private String moviesPath;
		
	public MovieLibrary(String moviesPath) throws FileNotFoundException {
		this.moviesPath = moviesPath;
		movies = parseMovies(moviesPath);
	}
	
	public void add(E movie) {
		movies.add(movie);
		writeMovies();
	}
	
	public void remove(int index) {
		movies.remove(index);
		writeMovies();
	}
	
	private void writeMovies() {
		
		try {
			PrintWriter printWriter = new PrintWriter(moviesPath);
			printWriter.println(E.getCsvHeaderString());
			for (E movie : movies) {
				String csvRecord = movie.movieCsvRecord();
				printWriter.println(csvRecord);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private List<E> parseMovies(String moviesPath) throws FileNotFoundException {
		
		FileReader reader = new FileReader(moviesPath);
		Scanner scanner = new Scanner(reader);
		
		// Read the movie from CSV
		List<E> movies = new ArrayList<E>();
		scanner.nextLine(); // skip header line
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			E movie = E.parseMovie(csvRecord);
			movies.add(movie);
		}
		scanner.close();
		
		return movies;

	}
	
	@Override
	public String toString() {
		String s = "";
		int index = 0;
		for (E movie : movies) {
			s += index + ": " + movie.toString() + "\n";
			index++;
		}
		return s;
	}
	
}