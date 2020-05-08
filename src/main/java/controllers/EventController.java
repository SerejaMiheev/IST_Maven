package controllers;

import gw.EventGateway;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;
import models.Event;
import registry.GWRegistry;

import java.io.IOException;
import java.util.Date;

public class EventController {
    @FXML
    TableView<Event> eventTable;

    @FXML
    TableColumn<Event, Date> dateColumn;

    @FXML
    TableColumn<Event, String> infColumn;

    private EventGateway eventGateway = GWRegistry.getInstance().getEventGateway();
    private ObservableList<Event> events = FXCollections.observableArrayList(eventGateway.all());
    private Main app;

    public void initialize() {
        eventTable.setItems(events);
        dateColumn.setCellValueFactory(item -> item.getValue().getDateProperty());
        infColumn.setCellValueFactory(item -> item.getValue().stringProperty());
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public void add() throws IOException {
        Event event = new Event();
        if (!this.app.OpenEventAdd(event)) {
            this.events.add(event);
        }
    }

    public void del() {
        Event event = this.eventTable.getSelectionModel().getSelectedItem();
        if (event != null) {
            this.eventGateway.delete(event);
            this.events.remove(event);
        }
    }

    public void cancel() {
        Stage stage = (Stage) eventTable.getScene().getWindow();
        stage.close();
    }
}
