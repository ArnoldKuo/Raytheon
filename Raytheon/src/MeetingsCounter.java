
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 
	
	/**
	 * @author Surya Polina
	 *
	 */
    class MeetingsCounter implements Counter{
    /**
     * 
     * @param fileName
     * @return array with date from text file
     * @throws FileNotFoundException
     */
	public int[] read(String fileName) throws FileNotFoundException {
		File file = new File(fileName); //parameter file
		Scanner sc = new Scanner(file);
		String part = sc.next(); //only one line of text will work
		String part2 = sc.next(); 
		String word = sc.next(); //gets the Day of the Week of meetings
		sc.close();
		
		//text file must have exact order everytime to extract properly
		String year = part.substring(0, 4); 
		String month = part.substring(5,7); //extracts month
		String day = part.substring(8, 10); //extracts day
		
		int[] arr = new int[4]; //store date information
		arr[0] = Integer.parseInt(month);
		arr[1] = Integer.parseInt(day);	//extracted present date from file
		arr[2] = Integer.parseInt(year);
		
		//gets the week day in numeric form for the third index 
		switch (word) {
			case "Sunday":
				arr[3] = 0;
				break;
			case "Monday":
				arr[3] = 1;
				break;
			case "Tuesday":
				arr[3] = 2;
				break;
			case "Wednesday":
				arr[3] = 3;
				break;
			case "Thursday":
				arr[3] = 4;
				break;
			case "Friday":
				arr[3] = 5;
				break;
			case "Saturday":
				arr[3] = 6;
				break;
			default:
				break;
		}
		
		return arr;
		
		
	}
	@Override
	/**
	 * @return number of holidays
	 */
	public int holidays() {
		// TODO Auto-generated method stub
		
		int numHol = 0;
		boolean check = false;
		while (check == false) {
			try {
				System.out.print("How many holidays do you anticipate on meeting days this year? ");
				Scanner sc = new Scanner(System.in);
				numHol = sc.nextInt();
				check = true;
			}
			catch(Exception e) {
				System.out.println("Please specify the number of holidays occuring this year on the meeting day");
			}
		}
		
		return numHol;
	}
	/**
	 * @param file name as a string
	 * @return number of meetings
	 * @throws FileNotFoundException
	 */
	@Override
	public int totalMeets(String name) throws FileNotFoundException {
		int[] date = read(name);
		//System.out.print("Day: " + date[1] + "\nmonth: " + date[0] + "\nyear: " + date[2] + "\n");
		Calendar start = day(date[0]-1, date[2]);

		Calendar endDay = Calendar.getInstance();
		endDay.set(date[2], 12, 30);
	    int meetingCount = 0;
	    int monthCount = 0;
	    while (start.compareTo(endDay) < 0) {
	    	System.out.println(start.compareTo(endDay));
		    int maxDayInMonth = start.getActualMaximum(Calendar.DAY_OF_MONTH);
	    	System.out.println("maxDayInMonth " + maxDayInMonth);
	    	
		    for (int i = date[1];  i < maxDayInMonth;  i++) {
		        start.set(Calendar.DAY_OF_MONTH, i);
		        
		        int dayOfWeek = start.get(Calendar.DAY_OF_WEEK);
		        if (date[3] == dayOfWeek) {
		            meetingCount++;
		        }
		    }
//		    if (monthCount == 16) {
//	    		break;
//	    	}
		    monthCount++;
		    start.set(date[2], date[0] + monthCount, 0);
		    //start = day(date[0] - 1 + monthCount, date[2]);
		    if (date[1] != 0) {
		    	date[1] = 0;
		    }
		    
	    }

		System.out.print(meetingCount);
		return meetingCount;
	}
	/**
	 * 
	 * @param month
	 * @param year
	 * @return Instance of Calendar with assigned Month and Year
	 */
	public Calendar day(int month, int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		switch (month) {
		case 0:
			c.set(Calendar.MONTH, Calendar.JANUARY);
			break;
		case 1:
			//System.out.print("here");
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			break;
		case 2:
			c.set(Calendar.MONTH, Calendar.MARCH);
			break;
		case 3:
			c.set(Calendar.MONTH, Calendar.APRIL);
			break;
		case 4:
			c.set(Calendar.MONTH, Calendar.MAY);
			break;
		case 5:
			c.set(Calendar.MONTH, Calendar.JUNE);
			break;
		case 6:
			c.set(Calendar.MONTH, Calendar.JULY);
			break;
		case 7:
			c.set(Calendar.MONTH, Calendar.AUGUST);
			break;
		case 8:
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			break;
		case 9:
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			break;
		case 10:
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			break;
		case 11:
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			break;	
		}
		return c;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		MeetingsCounter hi = new MeetingsCounter();
		hi.totalMeets("test.txt");
	}
	

}
