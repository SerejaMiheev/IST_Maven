package controllers;

import exceptions.EntityNotFound;
import gw.CameraGateway;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import models.Camera;
import registry.GWRegistry;

import java.io.File;

public class AddCameraController {
    @FXML
    VBox vBox;

    @FXML
    TextField numberLabel;

    @FXML
    TextField locText;

    private CameraGateway cameraGateway = GWRegistry.getInstance().getCameraGateway();
    private Camera camera;
    private boolean isCancel;

    public void setCancel(boolean isCancel){
        this.isCancel = isCancel;
    }

    public boolean retCancel(){
        return isCancel;
    }

    public void setCamera(Camera camera){
        this.camera = camera;
        this.numberLabel.setText(String.valueOf(camera.getNumbercam()));
        this.locText.setText(camera.getLocrecord());
    }

    public void initialize(){
        numberLabel.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                String newValue) {
                if (newValue.matches("\\d*")) {
                    numberLabel.setText(newValue);
                } else {
                    numberLabel.setText(oldValue);
                }
            }
        });
    }

    public void changeFolder(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage)this.vBox.getScene().getWindow();
        if(!locText.getText().isEmpty()) {
            directoryChooser.setInitialDirectory(new File(locText.getText()));
        }
        File file = directoryChooser.showDialog(stage);

        if(file != null){
            locText.setText(file.getAbsolutePath());
        }
    }

    public void add() throws EntityNotFound {
        if((!this.numberLabel.getText().isEmpty()) && (!this.locText.getText().isEmpty())) {
            int num = Integer.parseInt(this.numberLabel.getText());
            String loc = this.locText.getText();

            this.camera.setNumbercam(num);
            this.camera.setLocrecord(loc);

            if (this.camera.getId() != 0) {
                this.cameraGateway.update(this.camera);
            } else {
                this.cameraGateway.insert(this.camera);
            }
            isCancel = false;

            Stage stage = (Stage)this.vBox.getScene().getWindow();
            stage.close();
        }
    }

    public void cancel(){
        isCancel = true;
        Stage stage = (Stage)this.vBox.getScene().getWindow();
        stage.close();
    }
}
