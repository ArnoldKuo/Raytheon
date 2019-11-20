import java.io.FileNotFoundException;
//MVL
public interface Occurences {

	public int meetingCount() throws FileNotFoundException;
	int[] extractDate(String fileName) throws FileNotFoundException;
}
