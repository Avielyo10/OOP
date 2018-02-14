package Filter;

import wifi.wifi_scans;

public class andFilter {
	/**
	 * filtering by id && Location
	 * @param scans
	 * @param id
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public static wifi_scans idAndLoc(wifi_scans scans,String id,String lon,String lat,double radius) {
		filter filter = new filter();
		return filter.byLocation(filter.byId(scans, id), lon, lat, radius);
	}
	/**
	 * filtering by id && Time
	 * @param scans
	 * @param id
	 * @param date$time
	 * @param date_time
	 * @return
	 */
	public static wifi_scans idAndTime(wifi_scans scans,String id,String date$time,String date_time) {
		filter filter = new filter();
		return filter.byTime(filter.byId(scans, id), date$time, date_time);
	}
	/**
	 * filtering by Time && Location
	 * @param scans
	 * @param lon
	 * @param lat
	 * @param radius
	 * @param date$time
	 * @param date_time
	 * @return
	 */
	public static wifi_scans locAndTime(wifi_scans scans,String lon,String lat,double radius,String date$time,String date_time) {
		filter filter = new filter();
		return filter.byLocation(filter.byTime(scans, date$time, date_time), lon, lat, radius);
	}
}
