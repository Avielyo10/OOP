package algos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import wifi.wifi_scan;
import wifi.wifi_scans;

/**
 * implementing the first algorithm 
 * @author Aviel
 *
 */
public class algo_1DS {
	/**
	 * taking all the scans and divide them by mac address
	 * @param finalData
	 * @return data by mac addresses
	 */
	public static wifi_scans takeTheMac(wifi_scans finalData){
		wifi_scans finData = new wifi_scans();
		for (int i = 0; i < finalData.size(); i++) {
			int _intance=Integer.parseInt(finalData.get(i).getNumOfNet())*4;
			for (int j = 0; j < _intance; j+=4) {
				wifi_scan line = new wifi_scan();
				ArrayList<String> _line = new ArrayList<String>();
				line.setLat(finalData.get(i).getLat());//lat
				line.setLon(finalData.get(i).getLon());//lon
				line.setAlt(finalData.get(i).getAlt());//alt
				line.setMac(finalData.get(i).getScan().get(7+j));//mac
				line.setSsid(finalData.get(i).getScan().get(6+j));//ssid
				line.setSignal(finalData.get(i).getScan().get(9+j));//signal	
				line.setTime(finalData.get(i).getTime());//time
				_line.add(finalData.get(i).getLat());
				_line.add(finalData.get(i).getLon());
				_line.add(finalData.get(i).getAlt());
				_line.add(finalData.get(i).getScan().get(7+j));
				_line.add(finalData.get(i).getScan().get(6+j));
				_line.add(finalData.get(i).getScan().get(9+j));
				_line.add(finalData.get(i).getTime());
				line.setScan(_line);
				finData.add(line);
			}
		}
		return finData;
	}
	/**
	 * takes all the data by mac addresses and calculate the estimated location
	 * @param toCalc
	 * @return
	 */
	public static wifi_scans orgData(wifi_scans toCalc) {
		wifi_scans orgData = new wifi_scans();
		for (int i = 0; i < toCalc.size(); i++) {
			int hashMac=toCalc.get(i).getMac().hashCode();
			wifi_scans tempy = new wifi_scans();
			for (int j = 0; j < toCalc.size(); j++) {
				if(hashMac==toCalc.get(j).getMac().hashCode()) {
					tempy.add(toCalc.get(j));
				}
			}
			orgData.add(estLoc.calcEstiLoc(tempy));
		}
		return orgData;
	}
	/**
	 * remove the repeated data
	 * @param orgData
	 * @return
	 */
	public static wifi_scans rmRepeated(wifi_scans orgData) {
		Set<HashSet<String>> filterd = new LinkedHashSet<HashSet<String>>();
		for (int i = 0; i < orgData.size(); i++) {
			HashSet<String> line = new LinkedHashSet<String>();
			line.addAll(orgData.get(i).getScan());
			filterd.add((LinkedHashSet<String>) line);
		}
		wifi_scans tempy = new wifi_scans();
		for (HashSet<String> temp : filterd) {
			ArrayList<String> _line = new ArrayList<String>(temp);
			wifi_scan line = new wifi_scan();
			line.setScan(_line);
			tempy.add(line);
		}
		return tempy;
	}
	/**
	 * do all the functions together
	 * @param path
	 * @return final data by the first algorithm 
	 * @throws IOException
	 */
	public static wifi_scans all(String path) throws IOException {
		return rmRepeated(orgData(takeTheMac(wifi_scans.all(path))));
	}
}
