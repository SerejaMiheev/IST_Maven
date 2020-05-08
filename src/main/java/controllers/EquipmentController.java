package controllers;

import gw.EquipmentGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Equipment;
import models.TOE;
import registry.GWRegistry;

import java.io.IOException;

public class EquipmentController {
    @FXML
    TableView<Equipment> equipTable;

    @FXML
    TableColumn<Equipment, TOE> typeColumn;

    @FXML
    TableColumn<Equipment, Number> countColumn;

    private EquipmentGateway equipmentGateway = GWRegistry.getInstance().getEquipmentGateway();
    private ObservableList<Equipment> equipments = FXCollections.observableArrayList(equipmentGateway.all());
    private Main app;

    public void setApp(Main app) {
        this.app = app;
    }

    public void initialize() {
        equipTable.setItems(equipments);
        typeColumn.setCellValueFactory(item -> item.getValue().typeProperty());
        countColumn.setCellValueFactory(item -> item.getValue().countProperty());
    }

    public void add() throws IOException {
        Equipment equipment = new Equipment();
        if (!this.app.OpenEquipmentAdd(equipment)) {
            this.equipments.add(equipment);
        }
    }

    public void edit() throws IOException {
        Equipment equipment = this.equipTable.getSelectionModel().getSelectedItem();
        if (equipment != null) {
            this.app.OpenEquipmentAdd(equipment);
        }
    }

    public void del() {
        Equipment equipment = this.equipTable.getSelectionModel().getSelectedItem();
        if (equipment != null) {
            this.equipmentGateway.delete(equipment);
            this.equipments.remove(equipment);
        }
    }

    public void cancel() {
        Stage stage = (Stage) equipTable.getScene().getWindow();
        stage.close();
    }
}
