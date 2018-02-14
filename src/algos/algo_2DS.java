package algos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import wifi.wifi_scan;
import wifi.wifi_scans;

/**
 * implementing the second algorithm 
 * @author Aviel
 *
 */
public class algo_2DS {
	/**
	 * create data structure of wifi scans from the data we need to fix
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static wifi_scans createDs(String path) throws IOException {
		wifi_scans csvLines = new wifi_scans();
		int j =0;
		File folder = new File(path);//take folder
		File[] listOfFiles = folder.listFiles();{//create array of files in this folder
			for (int i = 0; i < listOfFiles.length; i++) {//Iterate the files
				File file = listOfFiles[i];//insert to the array
				if (file.isFile() && file.getName().endsWith(".csv")) {//take the csv file 
					FileReader in = new FileReader(listOfFiles[i]);
					BufferedReader br = new BufferedReader(in);
					String data;
					while ((data=br.readLine())!= null) {
						wifi_scan line = new wifi_scan();
						ArrayList<String> _line = new ArrayList<String>(Arrays.asList(data.split(",")));
						line.setScan(_line);
						wifi_scan line$ = new wifi_scan(line);
						csvLines.add(j, line$);//adding lines
						j++;
					}
					in.close();
				}
			}
		}
		return csvLines;
	}
	/**
	 * the main function that take two data bases,
	 * one of the whole data and one we need to fix
	 * @param data
	 * @param toFix
	 */
	public static void find(wifi_scans data, wifi_scans toFix) {
		Comparator<wifi_scan> comper = new Comparator<wifi_scan>() {
			public int compare(wifi_scan csvLine1, wifi_scan csvLine2) {
				return Double.valueOf(csvLine1.getScore()).compareTo(Double.valueOf(csvLine2.getScore()));
			}
		};
		for (int i = 0; i < toFix.size(); i++) {
			for (int j = 0; j < data.size(); j++) {
				setScores(data.get(j), toFix.get(i));
			}
			Collections.sort(data,Collections.reverseOrder(comper));
			calc(toFix.get(i),data.get(0),data.get(1),data.get(2));
		}
	}
	/**
	 * check between 2 scans which scan is the most closest to the data to fix
	 * @param fromData
	 * @param toFix
	 */
	public static void setScores(wifi_scan fromData, wifi_scan toFix) {
		int find=0;
		int numNetToFix=Integer.parseInt(toFix.getNumOfNet())*4;
		int numNetFromData=Integer.parseInt(fromData.getNumOfNet())*4;
		for (int i = 0; i < numNetToFix; i+=4) {
			String toFixMac = toFix.getScan().get(7+i);
			for (int j = 0; j < numNetFromData; j+=4) {
				String dataMac = fromData.getScan().get(7+j);
				if(Objects.equals(toFixMac, dataMac)) {
					find++;
				}
			}
		}
		double score = find / Double.parseDouble(toFix.getNumOfNet());
		fromData.setScore(String.valueOf(score));
	}
	/**
	 * calculating the estimated location based of the most closest results found on the data
	 * @param toFix
	 * @param one
	 * @param two
	 * @param three
	 */
	public static void calc(wifi_scan toFix, wifi_scan one,wifi_scan two,wifi_scan three) {
		if(Double.parseDouble(one.getScore())==1) {
			toFix.setAlt(one.getAlt());
			toFix.setLon(one.getLon());//if it's same line
			toFix.setLat(one.getLat());
			toFix.getScan().set(2, one.getLat());
			toFix.getScan().set(3, one.getLon());
			toFix.getScan().set(4, one.getAlt());
		}
		else if(Double.parseDouble(one.getScore())==0 && Double.parseDouble(two.getScore())==0 && Double.parseDouble(three.getScore())==0) {
			toFix.setAlt("UnKnown");
			toFix.setLon("UnKnown");//if no data was find
			toFix.setLat("UnKnown");
			toFix.getScan().set(2, "UnKnown");
			toFix.getScan().set(3, "UnKnown");
			toFix.getScan().set(4, "UnKnown");
		}
		else if((Double.parseDouble(three.getScore())==0)&&(Double.parseDouble(two.getScore())==0)&&(Double.parseDouble(one.getScore())!=0)) {
			toFix.setAlt(one.getAlt());
			toFix.setLon(one.getLon());//if the only line with the data is the first one
			toFix.setLat(one.getLat());
			toFix.getScan().set(2, one.getLat());
			toFix.getScan().set(3, one.getLon());
			toFix.getScan().set(4, one.getAlt());
		}
		else if((Double.parseDouble(three.getScore())==0)&&(Double.parseDouble(two.getScore())!=0)&&(Double.parseDouble(one.getScore())!=0)) {
			wifi_scans firstThree = new wifi_scans();
			firstThree.add(one);
			firstThree.add(two);
			three.setAlt("100");
			three.setLon("100");
			three.setLat("100");//2 lines of data
			firstThree.add(three);
			wifi_scan calc = estLoc.calcEstiLoc(firstThree);
			toFix.setAlt(calc.getAlt());
			toFix.setLon(calc.getLon());
			toFix.setLat(calc.getLat());
			toFix.getScan().set(2, calc.getLat());
			toFix.getScan().set(3, calc.getLon());
			toFix.getScan().set(4, calc.getAlt());
		}
		else {
			wifi_scans firstThree = new wifi_scans();
			firstThree.add(one);//3 lines of data
			firstThree.add(two);
			firstThree.add(three);
			wifi_scan calc = estLoc.calcEstiLoc(firstThree);
			toFix.setAlt(calc.getAlt());
			toFix.setLon(calc.getLon());
			toFix.setLat(calc.getLat());
			toFix.getScan().set(2, calc.getLat());
			toFix.getScan().set(3, calc.getLon());
			toFix.getScan().set(4, calc.getAlt());
		}
	}
	/**
	 * takes the data path and the path we need to fix and do the whole process 
	 * @param dataPath
	 * @param toFixPath
	 * @return
	 * @throws IOException
	 */
	public static wifi_scans all(String dataPath,String toFixPath) throws IOException {
		wifi_scans data  = wifi_scans.all(dataPath);
		wifi_scans toFix = createDs(toFixPath);
		find(data, toFix);
		return toFix;
	}
}
