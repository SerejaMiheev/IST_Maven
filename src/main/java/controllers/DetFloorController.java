package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Camera;
import models.Floor;
import models.Room;


public class DetFloorController {
    @FXML
    Label numberLabel;

    @FXML
    ListView<Room> roomListView;

    @FXML
    ListView<Camera> camerasListView;


    public void setFloor(Floor floor) {
        numberLabel.setText(String.valueOf(floor.getNumber()));
        roomListView.setItems(FXCollections.observableList(floor.getRooms()));
        camerasListView.setItems(FXCollections.observableList(floor.getCameras()));
    }

    public void Cancel() {
        Stage stage = (Stage) numberLabel.getScene().getWindow();
        stage.close();
    }
}
