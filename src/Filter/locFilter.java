package Filter;

import wifi.wifi_scan;

/**
 * filtering location by radius in KM!
 * @author Aviel
 *
 */
public class locFilter {
	double _lat;
	double _lon;
	public locFilter(String lon, String lat) {
		this._lon = Double.parseDouble(lon);
		this._lat = Double.parseDouble(lat);
	}
	
	/**
	 * check if the scan is within the right range
	 * @param scan
	 * @param radius
	 * @return 
	 */
	public boolean withinRange(wifi_scan scan, double radius) {
		boolean flag = false;
		double distance = distanceBet2(this._lon, this._lat, Double.parseDouble(scan.getLon()), Double.parseDouble(scan.getLat()));
		if(distance<=radius) {
			flag = true;
		}
		return flag;
	}
	/**
	 * getting the distance between two coordinates 
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return distance
	 */
	public static double distanceBet2(double lon1,double lat1,double lon2,double lat2) {
		int radiusOfEarth = 6371;//KM
		double deltaLon = deg2rad(lon1-lon2);
		double deltaLat = deg2rad(lat1-lat2);
		double haversine = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
				Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
				Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
		double _haversine = 2 * Math.atan2(Math.sqrt(haversine) , Math.sqrt(1-haversine));
		return (radiusOfEarth * _haversine);
	}
	
	/**
	 * from degree to radians
	 * @param locInDeg
	 * @return radians
	 */
	public static double deg2rad(double locInDeg) {
		return locInDeg*(Math.PI/180);
	}
	/**
	 * check if it is a double
	 * @param str
	 * @return
	 */
	public static boolean isDoubled(String str){  
		try{  
			@SuppressWarnings("unused")
			double i = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;  
	}
}


