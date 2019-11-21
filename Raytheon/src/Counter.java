import java.io.FileNotFoundException;

public interface Counter {
	public int holidays();
	public int totalMeets(String name) throws FileNotFoundException;
}
