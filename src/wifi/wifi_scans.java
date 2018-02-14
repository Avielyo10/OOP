package wifi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;


/**
 * this class represents a data type called wifi scans which store in scans
 * it's reading a csv file or files and create a different line with the top 10 strongest 
 * wifi scans that was taken at the same time and the same place!
 * @author Aviel
 *
 */
@SuppressWarnings("serial")
public class wifi_scans extends ArrayList<wifi_scan> {
	wifi_scan scan;
	public wifi_scans() {
		this.scan=new wifi_scan();
	} 
	/**
	 *  creates wifi scans from path
	 * @param path
	 * @return all csv lines 
	 * @throws IOException
	 */
	public static wifi_scans createDs(String path) throws IOException {
		wifi_scans csvLines = new wifi_scans();
		int j =0;
		File folder = new File(path);//take folder
		File[] listOfFiles = folder.listFiles();{//create array of files in this folder
			for (int i = 0; i < listOfFiles.length; i++) {//Iterate the files
				File file = listOfFiles[i];//insert to the array
				if (file.isFile() && file.getName().endsWith(".csv") && file.getName().contains("WigleWifi")) {//take the csv file 
					FileReader in = new FileReader(listOfFiles[i]);
					BufferedReader br = new BufferedReader(in);
					String firstLine = br.readLine();
					String[] firstLineSpl=firstLine.split(",");
					String id = firstLineSpl[2];
					br.readLine();
					String data;
					while ((data=br.readLine())!= null) {
						wifi_scan line = new wifi_scan(id,data);
						csvLines.add(j, line);//adding lines
						j++;
					}
					in.close();
				}
			}
		}
		return csvLines;
	}
	/**
	 * removes all the gsm mac addresses
	 * @param csvLines
	 * @return
	 */
	public static wifi_scans removeFakeMac(wifi_scans csvLines){
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < csvLines.size(); i++) {
				if(!csvLines.get(i).mac.contains(":")) {
					csvLines.remove(i);
				}
			}
		}
		return csvLines;
	}
	/**
	 * final function that find all the wifi scans from all the files that happened with 
	 * the same device at the same time in the same place!
	 * @param csvLinesOrd
	 * @return
	 */
	public static wifi_scans finDs(wifi_scans csvLinesOrd){
		wifi_scans csvLinesFin = new wifi_scans();
		for (int i = 0; i < csvLinesOrd.size(); i++) {
			wifi_scans tempy = new wifi_scans();
			tempy.add(csvLinesOrd.get(i));
			for (int k = i+1; k < csvLinesOrd.size(); k++) {
				if((Objects.equals(csvLinesOrd.get(i).time, csvLinesOrd.get(k).time))&&(Objects.equals(csvLinesOrd.get(i).getLat(), csvLinesOrd.get(k).getLat()))&&(Objects.equals(csvLinesOrd.get(i).getLon(), csvLinesOrd.get(k).getLon()))&&(Objects.equals(csvLinesOrd.get(i).alt, csvLinesOrd.get(k).alt))) {
					tempy.add(csvLinesOrd.get(k));
				}
			}
			csvLinesFin.add(unite(topTen(tempy)));
		}
		return csvLinesFin;
	}
	/**
	 * removes repeated lines
	 * @param orderedCSV
	 * @return
	 */
	public static wifi_scans removeSim(wifi_scans orderedCSV){
		for (int i = 0; i < orderedCSV.size(); i++) {
			for (int k = i+1; k < orderedCSV.size(); k++) {
				if((Objects.equals(orderedCSV.get(i).time, orderedCSV.get(k).time))&&(Objects.equals(orderedCSV.get(i).getLat(), orderedCSV.get(k).getLat()))&&(Objects.equals(orderedCSV.get(i).getLon(), orderedCSV.get(k).getLon()))&&(Objects.equals(orderedCSV.get(i).alt, orderedCSV.get(k).alt))) {
					orderedCSV.remove(orderedCSV.get(k));
				}
			}
		}
		return orderedCSV;
	}
	/**
	 * taking the wifi scans and unite them to one wifi scan
	 * @param tempy
	 * @return
	 */
	public static wifi_scan unite(wifi_scans tempy){
		wifi_scan line = new wifi_scan();
		for (int l = 0; l < tempy.size(); l++) {
			line.getScan().add((tempy.get(l).ssid));
			line.getScan().add((tempy.get(l).mac));
			line.getScan().add((tempy.get(l).freq));
			line.getScan().add((tempy.get(l).signal));

		}
		line.getScan().add(0,tempy.get(0).time);
		line.getScan().add(1,tempy.get(0).id);					//Unite
		line.getScan().add(2,tempy.get(0).getLat());
		line.getScan().add(3,tempy.get(0).getLon());
		line.getScan().add(4,tempy.get(0).alt);
		line.getScan().add(5,String.valueOf(tempy.size()));
		wifi_scan unitedLine = new wifi_scan(line);
		return unitedLine;
	}
	/**
	 * find the most top ten wifi scans by signal
	 * @param tempy
	 * @return
	 */
	public static wifi_scans topTen(wifi_scans tempy){
		Comparator<wifi_scan> comper = new Comparator<wifi_scan>() {
			public int compare(wifi_scan csvLine1, wifi_scan csvLine2) {
				return Double.valueOf(csvLine1.signal).compareTo(Double.valueOf(csvLine2.signal));
			}
		};
		Collections.sort(tempy, Collections.reverseOrder(comper));//sorting
		wifi_scans topTen = new wifi_scans();
		if(tempy.size()>0&&tempy.size()<10) {
			for (int l = 0; l < tempy.size(); l++) {
				topTen.add(tempy.get(l));
			}	
		}
		else {
			for (int i = 0; i < 10; i++){//adding to top 10
				topTen.add(tempy.get(i));
			}	
		}
		return topTen;
	}
	/**
	 * do all the functions at this class
	 * @param path
	 * @return final scans to output 
	 * @throws IOException
	 */
	public static wifi_scans all(String path) throws IOException{
		wifi_scans csvLinesFin = finDs(removeFakeMac(createDs(path)));
		for (int i = 0; i < 10; i++) {
			removeSim(csvLinesFin);
		}
		return csvLinesFin;
	}
}