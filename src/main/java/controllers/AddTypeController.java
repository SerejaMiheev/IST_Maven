package controllers;

import exceptions.EntityNotFound;
import gw.TypeGateway;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.TOE;
import registry.GWRegistry;

public class AddTypeController {
    @FXML
    TextField nameType;

    private TOE typeOfEquipments;
    private boolean isCancel;
    private TypeGateway typeGateway = GWRegistry.getInstance().getTypeGateway();

    public void setTypeOfEquipments(TOE typeOfEquipments) {
        this.typeOfEquipments = typeOfEquipments;
        this.nameType.setText(typeOfEquipments.getTypeOfEquipment());
    }

    public void addType() throws EntityNotFound {
        if (this.nameType.getText() != null) {
            String name = this.nameType.getText();

            this.typeOfEquipments.setTypeOfEquipment(name);

            if (this.typeOfEquipments.getId() != 0) {
                this.typeGateway.update(this.typeOfEquipments);
            } else {
                this.typeGateway.insert(this.typeOfEquipments);
            }
            isCancel = false;

            Stage stage = (Stage) this.nameType.getScene().getWindow();
            stage.close();
        }
    }

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean retCancel() {
        return isCancel;
    }

    public void cancel() {
        isCancel = true;
        Stage stage = (Stage) this.nameType.getScene().getWindow();
        stage.close();
    }
}
