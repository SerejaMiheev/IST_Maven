package controllers;

import exceptions.EntityNotFound;
import gw.CameraGateway;
import gw.EventGateway;
import gw.PersonGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import models.Camera;
import models.Event;
import models.Person;
import registry.GWRegistry;

public class AddEventController {
    @FXML
    ComboBox<Person> personBox;

    @FXML
    ComboBox<Camera> cameraBox;

    private PersonGateway personGateway = GWRegistry.getInstance().getPersonGateway();
    private CameraGateway cameraGateway = GWRegistry.getInstance().getCameraGateway();
    private EventGateway eventGateway = GWRegistry.getInstance().getEventGateway();
    private ObservableList<Person> persons = FXCollections.observableArrayList(personGateway.all());
    private ObservableList<Camera> cameras = FXCollections.observableArrayList(cameraGateway.all());
    private Event event;
    private boolean isCancel;

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean retCancel() {
        return isCancel;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void initialize() {
        this.personBox.setItems(persons);
        this.cameraBox.setItems(cameras);
    }

    public void add() throws EntityNotFound {
        if (!this.cameraBox.getSelectionModel().isEmpty()) {
            Camera camera = this.cameraBox.getValue();
            Person person;
            if (!this.personBox.getSelectionModel().isEmpty()) {
                person = this.personBox.getValue();
            } else {
                person = null;
            }

            this.event.setCamera(camera);
            this.event.setPerson(person);

            if (this.event.getId() != 0) {
                this.eventGateway.update(this.event);
            } else {
                this.eventGateway.insert(this.event);
            }
            isCancel = false;

            Stage stage = (Stage) this.personBox.getScene().getWindow();
            stage.close();
        }
    }

    public void cancel() {
        isCancel = true;
        Stage stage = (Stage) this.personBox.getScene().getWindow();
        stage.close();
    }
}
