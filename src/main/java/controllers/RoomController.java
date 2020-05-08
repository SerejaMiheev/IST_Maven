package controllers;

import gw.RoomGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Room;
import registry.GWRegistry;

import java.io.IOException;

public class RoomController {
    @FXML
    TableView<Room> roomTable;

    @FXML
    TableColumn<Room, String> roomColumn;

    private RoomGateway roomGateway = GWRegistry.getInstance().getRoomGateway();
    private ObservableList<Room> rooms = FXCollections.observableArrayList(roomGateway.all());
    private Main app;

    public void initialize() {
        roomTable.setItems(rooms);
        roomColumn.setCellValueFactory(item -> item.getValue().stringProperty());
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public void add() throws IOException {
        Room room = new Room();
        if (!this.app.OpenRoomAdd(room)) {
            this.rooms.add(room);
        }
    }

    public void edit() throws IOException {
        Room room = this.roomTable.getSelectionModel().getSelectedItem();
        if (room != null) {
            this.app.OpenRoomAdd(room);
        }
    }

    public void del() {
        Room room = this.roomTable.getSelectionModel().getSelectedItem();
        if (room != null) {
            this.roomGateway.delete(room);
            this.rooms.remove(room);
        }
    }

    public void cancel() {
        Stage stage = (Stage) this.roomTable.getScene().getWindow();
        stage.close();
    }
}
