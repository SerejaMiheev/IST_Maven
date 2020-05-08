package controllers;

import exceptions.EntityNotFound;
import gw.CameraGateway;
import gw.EquipmentGateway;
import gw.RoomGateway;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Camera;
import models.Equipment;
import models.Floor;
import models.Room;
import registry.GWRegistry;

public class AddRoomController {
    @FXML
    TextField numRoom;

    @FXML
    TableView<Equipment> typeTableView;

    @FXML
    TableColumn<Equipment, String> typeColumn;

    @FXML
    TableColumn<Equipment, CheckBox> addType;

    @FXML
    TableView<Camera> cameraTableView;

    @FXML
    TableColumn<Camera, Number> numCamera;

    @FXML
    TableColumn<Camera, CheckBox> addCamera;

    private Room room;
    private RoomGateway roomGateway = GWRegistry.getInstance().getRoomGateway();
    private EquipmentGateway equipmentGateway = GWRegistry.getInstance().getEquipmentGateway();
    private ObservableList<Equipment> equipments = FXCollections.observableArrayList(equipmentGateway.all());
    private CameraGateway cameraGateway = GWRegistry.getInstance().getCameraGateway();
    private ObservableList<Camera> cameras = FXCollections.observableArrayList(cameraGateway.all());
    private boolean isCancel;

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean retCancel() {
        return isCancel;
    }

    public void setRoom(Room room) {
        this.room = room;

        this.numRoom.setText(String.valueOf(room.getNumber()));
        ObservableList<Equipment> typeObservableList = FXCollections.observableArrayList(room.getEquipments());
        for (Equipment current : equipments) {
            CheckBox checkBox = new CheckBox();
            for (Equipment eq_in_room : typeObservableList) {
                if (eq_in_room.equals(current)){
                    checkBox.setSelected(true);
                    current.setSelected(checkBox);
                    break;
                }
                else{
                    checkBox.setSelected(false);
                    current.setSelected(checkBox);
                }
            }
        }

        ObservableList<Camera> cameraObservableList = FXCollections.observableArrayList(room.getCameras());
        for (Camera current : cameras) {
            CheckBox checkBox = new CheckBox();
            for (Camera c_in_room : cameraObservableList) {
                if (c_in_room.equals(current)){
                    checkBox.setSelected(true);
                    current.setSelected(checkBox);
                    break;
                }
                else{
                    checkBox.setSelected(false);
                    current.setSelected(checkBox);
                }
            }
        }
    }

    @FXML
    public void initialize() {
        numRoom.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*")) {
                    numRoom.setText(newValue);
                } else {
                    numRoom.setText(oldValue);
                }
            }
        });

        typeTableView.setItems(equipments);
        typeColumn.setCellValueFactory(item -> item.getValue().typeStringProperty());
        addType.setCellValueFactory(
                new PropertyValueFactory<>("selected")
        );
        cameraTableView.setItems(cameras);
        numCamera.setCellValueFactory(item -> item.getValue().numbercamProperty());
        addCamera.setCellValueFactory(
                new PropertyValueFactory<>("selected")
        );
    }

    public void add() throws EntityNotFound {
        if (this.numRoom.getText() != null) {
            int num = Integer.parseInt(this.numRoom.getText());
            ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
            for (Equipment current : equipments) {
                if (current.getSelected().isSelected()) {
                    equipmentList.add(current);
                }
            }
            ObservableList<Camera> cameraList = FXCollections.observableArrayList();
            for (Camera current : cameras) {
                if (current.getSelected().isSelected()) {
                    cameraList.add(current);
                }
            }
            this.room.setNumber(num);
            this.room.setEquipments(equipmentList);
            this.room.setCameras(cameraList);

            if (this.room.getId() != 0) {
                this.roomGateway.update(this.room);
            } else {
                this.roomGateway.insert(this.room);
            }
            isCancel = false;

            Stage stage = (Stage) this.numRoom.getScene().getWindow();
            stage.close();
        }
    }

    public void cancel() {
        isCancel = true;
        Stage stage = (Stage) this.numRoom.getScene().getWindow();
        stage.close();
    }
}
