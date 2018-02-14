package GUI;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wifi.wifi_scans;
import writing.toCsv;

public class algo1 {
	static wifi_scans algo1Scans = wrapper.current;
	/**
	 * create csv file for algo 1
	 */
	public static void algo1Pop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,400,100);
		Label save = new Label("Save?");
		Button no = new Button("No");
		Button ok = new Button("Yes");
		ok.setOnAction(e -> {
			try {
				toCsv.writeToCsv(algo1Scans,"Algo_1.csv");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			pop.close();
		});
		no.setOnAction(e -> pop.close());
		save.setTranslateY(10);
		save.setTranslateX(175);
		no.setTranslateX(200);
		no.setTranslateY(50);
		ok.setTranslateX(150);
		ok.setTranslateY(50);
		pop.initModality(Modality.APPLICATION_MODAL);
		layout.getChildren().addAll(ok,no,save);
		pop.setScene(scene);
		pop.setTitle("Algo 1");
		pop.showAndWait();
	}
}
