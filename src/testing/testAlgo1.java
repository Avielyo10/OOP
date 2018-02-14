package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import algos.algo_1DS;
import wifi.wifi_scans;

class testAlgo1 {

	@Test
	void takeMac() throws IOException {
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		wifi_scans macs = algo_1DS.takeTheMac(scans);
		System.out.println(macs.get(0));
	}
	@Test
	void rmRepeated() throws IOException {
		boolean flag = false;
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");	
		wifi_scans test = algo_1DS.rmRepeated(algo_1DS.orgData(algo_1DS.takeTheMac(scans)));
		for (int i = 0; i < test.size(); i++) {
			for (int j = i+1; j < test.size(); j++) {
				if(test.get(i).hashCode()==test.get(j).hashCode()) {
					flag = true;
				}
			}

		}
		assertFalse(flag);
	}
	@Test
	void testOrg() throws IOException {
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");	
		wifi_scans test = (algo_1DS.orgData(algo_1DS.takeTheMac(scans)));
		System.out.println(test.get(0));
	}
	@Test
	void testAll() throws IOException {
		wifi_scans all = algo_1DS.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		System.out.println(all.get(0));
	}

}
