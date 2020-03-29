package sample;

import javafx.fxml.FXML;
import javafx.scene.image.*;

public class imageController {

    @FXML
    private ImageView imgView;

    @FXML
    private void initialize(){
        Image image = new Image("sample/black.jpg");
        imgView.setImage(image);
    }

}
