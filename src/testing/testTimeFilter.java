package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Filter.timeFilter;
import wifi.wifi_scan;

class testTimeFilter {

	/**
	 * check if the range works
	 */
	@Test
	void checkAll() {
		timeFilter filter = new timeFilter("26/09/2017 16:13","27/10/2017 16:18");
		String s = "0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],27/10/2017 16:17,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		String id ="id";
		wifi_scan scan = new wifi_scan(id,s);
		assertTrue(filter.withinRange(scan));
	}
	/**
	 * see if the seconds are deleted
	 */
	@Test
	void deleteSec() {
		timeFilter filter = new timeFilter("26/09/2017 16:13:18","27/10-2017 16:17:19");
		System.out.println(filter.delSec("26/09/2017 16:13:18"));
	}
	@Test
	void isTime() {
		String num = "23:24";
		assertFalse(timeFilter.isNumeric(num));
	}
	@Test
	void checkTime() {
		String num1 = "54:60";
		System.out.println(timeFilter.checkTime(num1));
		String num2 = "fw";
		System.out.println(timeFilter.checkTime(num2));
		String num3 = "24:45";
		System.out.println(timeFilter.checkTime(num3));
		String num4 = "12:61";
		System.out.println(timeFilter.checkTime(num4));
		
	}
}
