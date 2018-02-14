package testing;

import org.junit.jupiter.api.Test;

import wifi.wifi_scan;

class testWifiScan {

	/**
	 * check the constructor
	 */
	@Test
	void testConstructor() {
		String s = "0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],2017-10-27 16:16:45,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		String id ="id";
		wifi_scan w = new wifi_scan(id,s);
		String s2 = w.toString();
		System.out.println(s2);
	}

}
