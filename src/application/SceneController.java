package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class SceneController  {
	
	int switcher = 1;
	
	@FXML
    private Text textForChanging;
	
	@FXML
	public void changeText() {
		if (switcher == 1) {
			textForChanging.setText("Special text!");
			switcher = 0;
		} else {
			textForChanging.setText("Standart text!");
			switcher = 1;
		}
		
	}
	
}
