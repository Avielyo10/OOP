package Filter;

import wifi.wifi_scans;

/**
 * filtering class
 * @author Aviel
 *
 */
public class filter {
	public filter() {
	}
	/**
	 * filtering by id
	 * @param scans
	 * @param id
	 * @return output filtered
	 */
	public wifi_scans byId(wifi_scans scans,String id) {
		idFilter filter = new idFilter(id);
		wifi_scans out = new wifi_scans();
		for (int i = 0; i < scans.size(); i++) {
			if(filter.isId(scans.get(i))) {
				out.add(scans.get(i));
			}
		} 
		return out;
	}
	/**
	 * filtering by time
	 * @param scans
	 * @param date$time
	 * @param date_time
	 * @return output filtered
	 */
	public wifi_scans byTime(wifi_scans scans,String date$time,String date_time) {
		timeFilter filter = new timeFilter(date$time, date_time);
		wifi_scans out = new wifi_scans();
		for (int i = 0; i < scans.size(); i++) {
			if(filter.withinRange(scans.get(i))) {
				out.add(scans.get(i));
			}
		}
		return out;
	}
	/**
	 * filtering by location
	 * @param scans
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return output filtered
	 */
	public wifi_scans byLocation(wifi_scans scans,String lon,String lat,double radius) {
		if(radius<0) {
			radius=radius*(-1);
		}
		locFilter filter = new locFilter(lon, lat);
		wifi_scans out = new wifi_scans();
		for (int i = 0; i < scans.size(); i++) {
			if(filter.withinRange(scans.get(i),radius)) {
				out.add(scans.get(i));
			}
		}
		return out;
	}
}
