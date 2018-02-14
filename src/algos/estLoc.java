package algos;

import wifi.wifi_scan;
import wifi.wifi_scans;

public class estLoc {
	/**
	 * calculating estimated location
	 * @param toCalc
	 * @return calculated wifi scans location 
	 */
	public static wifi_scan calcEstiLoc(wifi_scans toCalc) {
		double wLat=0,wLon=0,wAlt=0,wSignal=0,cSignal=0;
		wifi_scan calculated = new wifi_scan();
		for (int i = 0; i < toCalc.size(); i++) {
			Double signal = 1/(Math.pow(Double.valueOf(toCalc.get(i).getSignal()), 2));//signal
			wLat+=signal*Double.valueOf(toCalc.get(i).getLat());//lat
			wLon+=signal*Double.valueOf(toCalc.get(i).getLon());//lon
			wAlt+=signal*Double.valueOf(toCalc.get(i).getAlt());//alt
			wSignal+=signal;
			cSignal+=1/(Double.valueOf(toCalc.get(i).getSignal()));
		}
		calculated.add(toCalc.get(0).getMac());//mac //0
		calculated.add(toCalc.get(0).getSsid());//ssid //1
		calculated.add(String.valueOf(toCalc.size()));//num of macs //2
		calculated.add(String.valueOf(cSignal/wSignal));//signal //3
		calculated.add(String.valueOf(wLat/wSignal));//lat //4
		calculated.add(String.valueOf(wLon/wSignal));//lon //5
		calculated.add(String.valueOf(wAlt/wSignal));//alt //6
		calculated.add(toCalc.get(0).getTime());//Time //7
		calculated.setMac(toCalc.get(0).getMac());
		calculated.setSsid(toCalc.get(0).getSsid());
		calculated.setSignal(String.valueOf(cSignal/wSignal));
		calculated.setLat(String.valueOf(wLat/wSignal));
		calculated.setLon(String.valueOf(wLon/wSignal));
		calculated.setAlt(String.valueOf(wAlt/wSignal));
		calculated.setTime(toCalc.get(0).getTime());
		return calculated;
	}
}
