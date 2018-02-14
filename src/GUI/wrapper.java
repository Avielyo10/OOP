package GUI;

import java.io.IOException;

import Filter.filter;
import Filter.notFilter;
import wifi.wifi_scans;

public class wrapper {
	static wifi_scans current = new wifi_scans();
	/**
	 * size of scans
	 * @param scans
	 * @return
	 */
	public static int size(wifi_scans scans) {
		return scans.size();
	}
	/**
	 * set the scans path
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static wifi_scans setScan(String path) throws IOException {
		current = wifi_scans.all(path);
		return current;
	}
	/**
	 * clear the scans
	 * @return
	 */
	public static wifi_scans clearScan(){
		return current = null;
	}
	/**
	 * setting not id
	 * @param scans
	 * @param id
	 * @return
	 */
	public static wifi_scans setNotID(wifi_scans scans,String id) {
		current = notFilter.notId(scans, id);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * setting not time
	 * @param scans
	 * @param date$time
	 * @param date_time
	 * @return
	 */
	public static wifi_scans setNotTime(wifi_scans scans,String date$time,String date_time) {
		current = notFilter.notTime(scans, date$time, date_time);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * setting not location
	 * @param scans
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public static wifi_scans setNotLoc(wifi_scans scans,String lon,String lat,double radius) {
		current = notFilter.notLoc(scans, lon, lat, radius);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * set filter by id
	 * @param scans
	 * @param id
	 * @return
	 */
	public static wifi_scans setID(wifi_scans scans,String id) {
		filter filter = new filter();
		current = filter.byId(scans, id);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * set filter by time
	 * @param scans
	 * @param date$time
	 * @param date_time
	 * @return
	 */
	public static wifi_scans setTime(wifi_scans scans,String date$time,String date_time) {
		filter filter = new filter();
		current = filter.byTime(scans, date$time, date_time);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * set filter by location
	 * @param scans
	 * @param lon
	 * @param lat
	 * @param radius
	 * @return
	 */
	public static wifi_scans setLoc(wifi_scans scans,String lon,String lat,double radius) {
		filter filter = new filter();
		current = filter.byLocation(scans, lon, lat, radius);
		filePopUp.numOfNet = current.size();
		return current;
	}
	/**
	 * refresh the current scans by all filters 
	 * @return 
	 * @throws IOException
	 */
	public static void refresh() throws IOException{
		current = setScan(filePopUp._path);
		if(Main.timeFilter&&filterPopUp.notTime) {
			setNotTime(current, filterPopUp.date$time, filterPopUp.date_time);
		}
		if(Main.timeFilter&&!filterPopUp.notTime) {
			setTime(current, filterPopUp.date$time, filterPopUp.date_time);
		}
		if(Main.idFilter&&filterPopUp.notId) {
			setNotID(current, filterPopUp.id);
		}
		if(Main.idFilter&&!filterPopUp.notId) {
			setID(current, filterPopUp.id);
		}
		if(Main.locFilter&&filterPopUp.notLoc) {
			setNotLoc(current, filterPopUp.lon, filterPopUp.lat, filterPopUp.radius);
		}
		if(Main.locFilter&&!filterPopUp.notLoc) {
			setLoc(current, filterPopUp.lon, filterPopUp.lat, filterPopUp.radius);
		}
		filePopUp.numOfNet = current.size();
		Main.setStatus();
	}
}
