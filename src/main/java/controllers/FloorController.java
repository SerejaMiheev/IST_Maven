package controllers;

import gw.FloorGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Floor;
import registry.GWRegistry;

import java.io.IOException;

public class FloorController {

    @FXML
    TableView<Floor> floorTable;
    @FXML
    TableColumn<Floor, Number> floorColumn;
    @FXML
    TableColumn<Floor, Number> countFloor;
    @FXML
    TableColumn<Floor, Number> countCamera;

    private FloorGateway floorGateway = GWRegistry.getInstance().getFloorGateway();
    private ObservableList<Floor> floors = FXCollections.observableArrayList(floorGateway.all());
    private Main app;

    @FXML
    public void initialize() {
        floorTable.setItems(floors);
        floorColumn.setCellValueFactory(item -> item.getValue().numberProperty());
        countFloor.setCellValueFactory(cellDate -> cellDate.getValue().countRoomProperty());
        countCamera.setCellValueFactory(cellDate -> cellDate.getValue().count());
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public void Add() throws IOException {
        Floor floor = new Floor();
        if (!this.app.OpenFloorAdd(floor)) {
            this.floors.add(floor);
        }
    }

    public void Del() {
        Floor floor = this.floorTable.getSelectionModel().getSelectedItem();
        if (floor != null) {
            this.floorGateway.delete(floor);
            this.floors.remove(floor);
        }
    }

    public void Edit() throws IOException {
        Floor floor = this.floorTable.getSelectionModel().getSelectedItem();
        if (floor != null) {
            this.app.OpenFloorAdd(floor);
        }
    }

    public void View() throws IOException {
        Floor floor = this.floorTable.getSelectionModel().getSelectedItem();
        if (floor != null) {
            this.app.OpenFloorDetView(floor);
        }
    }

    public void cancel() {
        Stage stage = (Stage) this.floorTable.getScene().getWindow();
        stage.close();
    }
}
