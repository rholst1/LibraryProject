

public class Movie extends Item {
	private int runtime;
	private float rating;
	
	
	public Movie(int id, String title, int value, int runtime, float rating) {
		super();
		this.typeOfItem = RunProgram.TYPE_MOVIE;
		this.id = id;
		this.title = title;
		this.value = value;
		this.runtime = runtime;
		this.rating = rating;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	public String movieCsvRecord() {
		return String.format("%s,%d,%s,%d,%d,%.1f", this.typeOfItem, this.id, this.title, this.value, this.runtime, this.rating);
	}
	
	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");	
		int id = Integer.parseInt(values[1]);
		String title = values[2];
		int value = Integer.parseInt(values[3]);
		int runtime = Integer.parseInt(values[4]);
		float rating = Float.parseFloat(values[5]);
		
		Movie movie = new Movie(id, title, value, runtime, rating);
		return movie;
		
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d | Title: %s | Value: %d | Runtime: %d | Rating: %.1f", getId(), getTitle(), getValue(), this.runtime, this.rating);
	}
	
}
