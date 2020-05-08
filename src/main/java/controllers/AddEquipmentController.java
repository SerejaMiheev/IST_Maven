package controllers;

import exceptions.EntityNotFound;
import gw.EquipmentGateway;
import gw.TypeGateway;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Equipment;
import models.TOE;
import registry.GWRegistry;


public class AddEquipmentController {
    @FXML
    TextField countText;

    @FXML
    ComboBox<TOE> typeEquip;

    private TypeGateway typeGateway = GWRegistry.getInstance().getTypeGateway();
    private EquipmentGateway equipmentGateway = GWRegistry.getInstance().getEquipmentGateway();
    private Equipment equipment;
    private boolean isCancel;

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean retCancel() {
        return isCancel;
    }

    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
        if (!this.equipment.getTypeOfEquipment().toString().isEmpty()){
            this.typeEquip.setValue(equipment.getTypeOfEquipment());
        }
        this.countText.setText(String.valueOf(this.equipment.getCountOfEquipment()));
    }

    public void initialize() {
        this.typeEquip.setItems(FXCollections.observableArrayList(this.typeGateway.all()));

        countText.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*")) {
                    countText.setText(newValue);
                } else {
                    countText.setText(oldValue);
                }
            }
        });
    }

    public void addEquip() throws EntityNotFound {
        if ((!this.countText.getText().isEmpty()) & (!this.typeEquip.getSelectionModel().isEmpty())) {
            int count = Integer.parseInt(countText.getText());
            TOE selectedItem = this.typeEquip.getSelectionModel().getSelectedItem();

            this.equipment.setCountOfEquipment(count);
            this.equipment.setTypeOfEquipment(selectedItem);

            if (this.equipment.getId() != 0) {
                this.equipmentGateway.update(this.equipment);
            } else {
                this.equipmentGateway.insert(this.equipment);
            }
            isCancel = false;

            Stage stage = (Stage) this.typeEquip.getScene().getWindow();
            stage.close();
        }
    }

    public void cancel() {
        isCancel = true;
        Stage stage = (Stage) this.typeEquip.getScene().getWindow();
        stage.close();
    }
}
