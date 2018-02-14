package Filter;

import wifi.wifi_scan;

/**
 * filtering by time
 * @author Aviel
 *
 */
public class timeFilter {
	String startingTime;
	String endTime;
	public timeFilter(String start,String end) {
		this.startingTime = delSec(start);
		this.endTime = delSec(end);
	}
	/**
	 * @param scan
	 * @return if the scan is within the right range
	 */
	public boolean withinRange(wifi_scan scan) {
		boolean flag= false;
		if(scan!=null) {
			String time = delSec(scan.getTime());
			int St = time.hashCode()-this.startingTime.hashCode();
			int Et = time.hashCode()-this.endTime.hashCode();
			if (St>=0&&Et<=0) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * delete the seconds
	 * @param time
	 * @return time without seconds
	 */
	public String delSec(String time) {
		String _time[] = time.split(":");
		return time = _time[0]+":"+_time[1];
	}
	/**
	 * flip the date that comes from the gui
	 * @param dateToFlip
	 * @return
	 */
	public static String flipDate(String dateToFlip) {
		String[] splited = dateToFlip.split(" ");
		String date[] = splited[0].split("/");
		return date[2] + "-" + date[1] + "-" + date[0] + " " + splited[1];
	}
	/**
	 * check the input for time 
	 * @param time
	 * @return
	 */
	public static String checkTime(String time) {
		String time$ = "00:00";
		if(time.contains(":")) {
			String _time[] = time.split(":");
			if(isNumeric(_time[0])&&isNumeric(_time[1])) {
				int hour = Integer.parseInt(_time[0])%24;
				int min = Integer.parseInt(_time[1])%60;
				String hourS = String.valueOf(hour);
				String minS = String.valueOf(min);
				if(hour<10) {
					hourS = "0" + String.valueOf(hour);
				}
				if(min<10) {
					minS = "0" + String.valueOf(min);
				}
				return hourS + ":" + minS;
			}
		}
		return time$;
	}
	/**
	 * checking if input is a number
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){  
		try{  
			@SuppressWarnings("unused")
			int i = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;  
	}
}
