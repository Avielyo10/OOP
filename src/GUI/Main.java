package GUI;

import java.io.IOException;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**
 * @author Aviel
 *Main gui
 */
public class Main extends Application {
	static boolean timeFilter = false;
	static boolean idFilter = false;
	static boolean locFilter = false;
	static Label info = new Label();
	static Label size = new Label();
	@Override
	public void start(Stage primaryStage) {
		BorderPane layout = new BorderPane();
		Scene scene = new Scene(layout, 300, 250, Color.WHITE);
		primaryStage.setTitle("MyWiggle");
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		layout.setTop(menuBar);
		//build file 
		Menu fileMenu = new Menu("File");
		MenuItem uploadMenuItem = new MenuItem("Upload");
		uploadMenuItem.setOnAction(e -> {
			try {
				filePopUp.uploadPop();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		Menu saveMenu = new Menu("Save as..");
		MenuItem csvItem = new MenuItem("csv");
		csvItem.setOnAction(e -> filePopUp.saveCsvPop());
		MenuItem kmlItem = new MenuItem("kml");
		kmlItem.setOnAction(e -> filePopUp.saveKmlPop());
		saveMenu.getItems().addAll(csvItem,kmlItem);
		MenuItem clearMenuItem = new MenuItem("Clear");
		clearMenuItem.setOnAction(e -> filePopUp.clearPop());
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setAccelerator(KeyCombination.keyCombination("Alt + F4"));
		exitMenuItem.setOnAction(e -> Platform.exit());

		fileMenu.getItems().addAll(uploadMenuItem, saveMenu, clearMenuItem,
				new SeparatorMenuItem(), exitMenuItem);
		//build filter
		Menu filterMenu = new Menu("Filter");
		CheckMenuItem timeMenuItem = new CheckMenuItem("By Time");
		filterMenu.getItems().add(timeMenuItem);
		timeMenuItem.setOnAction(e -> {
			if(!timeFilter) {
				filterPopUp.timePop();
			}
			else {
				timeMenuItem.setSelected(false);
				timeFilter = false;
				try {
					wrapper.refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		CheckMenuItem idMenuItem = new CheckMenuItem("By ID");
		filterMenu.getItems().add(idMenuItem);
		idMenuItem.setOnAction(e -> {
			if(!idFilter) {
				filterPopUp.idPop();
			}
			else {
				idMenuItem.setSelected(false);
				idFilter=false;
				try {
					wrapper.refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		CheckMenuItem locMenuItem = new CheckMenuItem("By Location");
		filterMenu.getItems().add(locMenuItem);
		locMenuItem.setOnAction(e -> {
			if(!locFilter) {
				filterPopUp.locPop();
			}
			else {
				locMenuItem.setSelected(false);
				locFilter=false;
				try {
					wrapper.refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		//build algo
		Menu algoMenu = new Menu("Algorithms");
		MenuItem firstAlgo = new MenuItem("First");
		firstAlgo.setOnAction(e -> algo1.algo1Pop());
		Menu secondAlgo= new Menu("Second");
		MenuItem algo2ByString = new MenuItem("by String");
		algo2ByString.setOnAction(e ->algo2.algo2StrPop());
		MenuItem algo2ByMac = new MenuItem("by Mac");
		algo2ByMac.setOnAction(e -> algo2.algo2MacPop());
		MenuItem nonAlgo = new MenuItem("None");
		secondAlgo.getItems().addAll(algo2ByString,algo2ByMac);
		algoMenu.getItems().addAll(firstAlgo, secondAlgo,
				new SeparatorMenuItem(), nonAlgo);

		menuBar.getMenus().addAll(fileMenu, filterMenu, algoMenu);
		setStatus();
		info.setTranslateY(40);
		info.setTranslateX(5);
		layout.setLeft(info);
		size.setTranslateY(-5);
		size.setTranslateX(65);
		layout.setBottom(size);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * updating the status of filtering & scans
	 */
	public static void setStatus() {
		info.setText("Data Base: "+ filePopUp._path + 
				"\nFilters: " + timeStat(timeFilter) +" "+ idStat(idFilter) +" "+ locStat(locFilter));
		size.setText("Number Of Scans: "+ filePopUp.numOfNet);
	}
	/**
	 * time status
	 * @param onOff
	 * @return
	 */
	public static String timeStat(boolean onOff) {
		String time;
		if(onOff&&filterPopUp.notTime) {
			time = "Time: " + filterPopUp.date$time +" -> "+ filterPopUp.date_time;
			time="(!"+time+")";
		}
		else if(onOff&&!filterPopUp.notTime){
			time = "Time: " + filterPopUp.date$time +" -> "+ filterPopUp.date_time;
		}
		else {
			time = "";
		}
		return time;
	}
	/**id status
	 * @param onOff
	 * @return
	 */
	public static String idStat(boolean onOff) {
		String id;
		if(onOff&&filterPopUp.notId) {
			id = "ID: " + filterPopUp.id;
			id="(!"+id+")";
		}
		else if(onOff&&!filterPopUp.notId) {
			id = "ID: " + filterPopUp.id;
		}
		else {
			id = "";
		}
		return id;
	}
	/**
	 * location status
	 * @param onOff
	 * @return
	 */
	public static String locStat(boolean onOff) {
		String loc;
		if(onOff&&filterPopUp.notLoc) {
			loc = "Location: (0<r<" + filterPopUp.radius+ "), center: ("+filterPopUp.lat+","+filterPopUp.lon+")";
			loc="(!"+loc+")";
		}
		else if(onOff&&!filterPopUp.notLoc) {
			loc = "Location: (0<r<" + filterPopUp.radius+ "), center: ("+filterPopUp.lat+","+filterPopUp.lon+")";
		}
		else {
			loc="";
		}
		return loc;
	}
}