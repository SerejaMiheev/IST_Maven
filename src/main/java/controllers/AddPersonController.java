package controllers;

import exceptions.EntityNotFound;
import gw.PersonGateway;
import gw.RoomGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Person;
import models.Room;
import registry.GWRegistry;

public class AddPersonController {
    @FXML
    TextField fioText;

    @FXML
    ComboBox<Room> numRoom;

    private RoomGateway roomGateway = GWRegistry.getInstance().getRoomGateway();
    private PersonGateway personGateway = GWRegistry.getInstance().getPersonGateway();
    private ObservableList<Room> rooms = FXCollections.observableArrayList(roomGateway.all());
    private Person person;
    private boolean isCancel;

    public void setCancel(boolean isCancel){
        this.isCancel = isCancel;
    }

    public void setPerson(Person person){
        this.person = person;
        this.fioText.setText(person.getFio());
        if (this.person.getRoom() != null){
            this.numRoom.setValue(person.getRoom());
        }
    }

    public void initialize(){
        this.numRoom.setItems(rooms);
    }

    public void Add() throws EntityNotFound {
        if ((!this.fioText.getText().isBlank())) {
            String fio = this.fioText.getText();
            Room room = null;
            if ((!this.numRoom.getSelectionModel().isEmpty())){
                room = this.numRoom.getValue();
            }

            this.person.setFio(fio);
            this.person.setRoom(room);

            if (this.person.getId() != 0) {
                this.personGateway.update(this.person);
            } else {
                this.personGateway.insert(this.person);
            }
            isCancel = false;

            Stage stage = (Stage)this.fioText.getScene().getWindow();
            stage.close();
        }
    }

    public boolean retCancel(){
        return isCancel;
    }

    public void Cancel(){
        isCancel = true;
        Stage stage = (Stage)this.fioText.getScene().getWindow();
        stage.close();

    }
}
