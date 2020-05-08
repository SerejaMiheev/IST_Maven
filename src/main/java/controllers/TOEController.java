package controllers;

import gw.TypeGateway;
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

public class TOEController {
    @FXML
    TableView<TOE> typeTable;

    @FXML
    TableColumn<TOE, String> typeColumn;


    private Main app;
    private TypeGateway typeGateway = GWRegistry.getInstance().getTypeGateway();
    private ObservableList<TOE> types = FXCollections.observableArrayList(typeGateway.all());

    public void setApp(Main app) {
        this.app = app;
    }

    public void initialize() {
        typeTable.setItems(types);
        typeColumn.setCellValueFactory(item -> item.getValue().typeofequipmentProperty());
    }

    public void addType() throws IOException {
        TOE typeOfEquipments = new TOE();
        if (!this.app.OpenTOEAdd(typeOfEquipments)) {
            this.types.add(typeOfEquipments);
        }
    }

    public void delType() {
        TOE typeOfEquipments = this.typeTable.getSelectionModel().getSelectedItem();
        if (typeOfEquipments != null) {
            this.typeGateway.delete(typeOfEquipments);
            this.types.remove(typeOfEquipments);
        }
    }

    public void edit() throws IOException {
        TOE typeOfEquipments = this.typeTable.getSelectionModel().getSelectedItem();
        if (typeOfEquipments != null) {
            this.app.OpenTOEAdd(typeOfEquipments);
        }
    }

    public void cancel() {
        Stage stage = (Stage) this.typeTable.getScene().getWindow();
        stage.close();
    }
}
