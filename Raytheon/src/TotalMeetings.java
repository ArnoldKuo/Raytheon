import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 

//MINIMUM VIABLE PRODUCT (MVP) 
public class TotalMeetings implements Occurences{ 

	/**
	 * @param text file name (includes.txt)
	 * @return Array with present date in which to calculate total Meetings from
	 */
	@Override
	public int[] extractDate(String fileName) throws FileNotFoundException {
		File file = new File(fileName); //parameter file
		
		Scanner sc = new Scanner(file);
		String part = sc.next(); //only one line of text will work
		String part2 = sc.next(); 
		String word = sc.next(); //gets the Day of the Week of meetings
		sc.close();
		//text file must have exact order everytime to extract properly
		String year = part.substring(0, 4); //extracts year
		String month = part.substring(5,7); //extracts month
		String day = part.substring(8, 10); //extracts day
		
		int finalDay = Integer.parseInt(day);	//extracted present date from file
		int finalMonth = Integer.parseInt(month);
		int finalYear = Integer.parseInt(year);
		System.out.print("Day: " + finalDay + "\nmonth: " + finalMonth + "\nyear: " + finalYear + "\n");
		
		int[] arr = new int[3]; //store date information
		arr[0] = finalMonth;
		arr[1] = finalDay;
		arr[2] = finalYear;
		
		return arr;
	}

	/**
	 * @return number of meetings
	 */
	@Override
	public int meetingCount() throws FileNotFoundException{
		int[] dates = extractDate("test.txt");
		float remainingDays = (float) ((31 - dates[1]) / 7.0); //takes account for the particular day in the month
		//System.out.print(remainingDays + "\n");
		float totalMeetings = (12 - dates[0]); //months remaining in year
		totalMeetings *= 4.33; //4.33 is the number of Mon-Sun's there are in a month on average
		totalMeetings+= remainingDays;
		int tempMeeting = (int) totalMeetings;
		if (totalMeetings - tempMeeting >= 0.5) {
			totalMeetings+=1.0;
		}
		return (int) totalMeetings;
	}
	public static void main(String[] args) throws FileNotFoundException {
		TotalMeetings c = new TotalMeetings();
		System.out.print(c.meetingCount());
	}


}
