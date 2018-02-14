package Filter;

import wifi.wifi_scans;

public class notFilter {
	/**
	 * cancel the time filter
	 * @param scans
	 * @param date$time
	 * @param date_time
	 * @return
	 */
	public static wifi_scans notTime(wifi_scans scans,String date$time,String date_time) {
		wifi_scans out = new wifi_scans();
		timeFilter filter = new timeFilter(date$time,date_time);
		for (int i = 0; i < scans.size(); i++) {
			if(!filter.withinRange(scans.get(i))){
				out.add(scans.get(i));
			}
		}
		return out;
	}
	/**
	 * cancel the location filter
	 * @param scans
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public static wifi_scans notLoc(wifi_scans scans,String lon,String lat,double radius) {
		wifi_scans out = new wifi_scans();
		locFilter filter = new locFilter(lon, lat);
		for (int i = 0; i < scans.size(); i++) {
			if(!filter.withinRange(scans.get(i),radius)){
				out.add(scans.get(i));
			}
		}
		return out;
	}
	/**
	 * cancel the id filter
	 * @param scans
	 * @param id
	 * @return
	 */
	public static wifi_scans notId(wifi_scans scans, String id) {
		wifi_scans out = new wifi_scans();
		idFilter filter = new idFilter(id);
		for (int i = 0; i < scans.size(); i++) {
			if(!filter.isId(scans.get(i))){
				out.add(scans.get(i));
			}
		}
		return out;
	}
}
