package controllers;

import gw.CameraGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Camera;
import registry.GWRegistry;

import java.io.IOException;

public class CameraController {
    @FXML
    TableView<Camera> cameraTable;

    @FXML
    TableColumn<Camera, Number> numberColumn;

    @FXML
    TableColumn<Camera, String> locColumn;

    private CameraGateway cameraGateway = GWRegistry.getInstance().getCameraGateway();
    private ObservableList<Camera> cameras = FXCollections.observableArrayList(cameraGateway.all());
    private Main app;

    public void initialize() {
        cameraTable.setItems(cameras);
        numberColumn.setCellValueFactory(item -> item.getValue().numbercamProperty());
        locColumn.setCellValueFactory(item -> item.getValue().locrecordProperty());
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public void add() throws IOException {
        Camera camera = new Camera();
        if (!this.app.OpenCameraAdd(camera)) {
            this.cameras.add(camera);
        }
    }

    public void edit() throws IOException {
        Camera camera = this.cameraTable.getSelectionModel().getSelectedItem();
        if (camera != null) {
            this.app.OpenCameraAdd(camera);
        }
    }

    public void del() {
        Camera camera = this.cameraTable.getSelectionModel().getSelectedItem();
        if (camera != null) {
            this.cameraGateway.delete(camera);
            this.cameras.remove(camera);
        }
    }

    public void cancel() {
        Stage stage = (Stage) cameraTable.getScene().getWindow();
        stage.close();
    }
}
