package controllers;

import main.Main;

import java.io.IOException;

public class Controller {

    private Main app;

    public void setApp(Main app) {
        this.app = app;
    }

    public void ClickFloor() throws IOException {
        this.app.OpenFloorView();
    }

    public void ClickPerson() throws IOException {
        this.app.OpenPersonView();
    }

    public void clickType() throws IOException {
        this.app.OpenTOEView();
    }

    public void clickCamera() throws IOException {
        this.app.OpenCameraView();
    }

    public void clickEquipment() throws IOException {
        this.app.OpenEquipmentView();
    }

    public void clickRoom() throws IOException {
        this.app.OpenRoomView();
    }

    public void clickEvent() throws IOException {
        this.app.OpenEventView();
    }
}
