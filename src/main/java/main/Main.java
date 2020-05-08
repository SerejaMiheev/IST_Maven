package main;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/sample.fxml"));
        VBox root = (VBox) loader.load();

        primaryStage.setScene(new Scene(root, 214, 303));
        primaryStage.setResizable(false);
        Controller controller = loader.getController();
        controller.setApp(this);
        primaryStage.show();
    }

    public void OpenFloorView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/floor_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewfloor = new Stage();
        viewfloor.setScene(new Scene(root));
        viewfloor.setTitle("Этажи");
        viewfloor.setResizable(false);
        FloorController floorController = loader.getController();
        floorController.setApp(this);
        viewfloor.showAndWait();
    }

    public boolean OpenFloorAdd(Floor floor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/floor_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage addfloor = new Stage();
        addfloor.setScene(new Scene(root));
        addfloor.setTitle("Этаж");
        addfloor.setResizable(false);
        AddFloorController addFloorController = loader.getController();
        addfloor.setOnCloseRequest(windowEvent -> {
            addFloorController.setCancel(true);
        });
        addFloorController.setFloor(floor);
        addfloor.showAndWait();
        return addFloorController.retCancel();
    }

    public void OpenFloorDetView(Floor floor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/floor_det_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage detfloor = new Stage();
        detfloor.setScene(new Scene(root));
        detfloor.setTitle("Просмотр этажа");
        detfloor.setResizable(false);
        DetFloorController detFloorController = loader.getController();
        detFloorController.setFloor(floor);
        detfloor.show();
    }

    public void OpenPersonView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/person_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewPerson = new Stage();
        viewPerson.setScene(new Scene(root));
        viewPerson.setTitle("Люди");
        viewPerson.setResizable(false);
        PersonController personController = loader.getController();
        personController.setApp(this);
        viewPerson.showAndWait();
    }

    public boolean OpenPersonAdd(Person person) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/person_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewAddPerson = new Stage();
        viewAddPerson.setScene(new Scene(root));
        viewAddPerson.setTitle("Человек");
        viewAddPerson.setResizable(false);
        AddPersonController addPersonController = loader.getController();
        viewAddPerson.setOnCloseRequest(windowEvent -> {
            addPersonController.setCancel(true);
        });
        addPersonController.setPerson(person);
        viewAddPerson.showAndWait();
        return addPersonController.retCancel();
    }

    public void OpenTOEView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/type_equp_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewTOE = new Stage();
        viewTOE.setScene(new Scene(root));
        viewTOE.setTitle("Тип");
        viewTOE.setResizable(false);
        TOEController toeController = loader.getController();
        toeController.setApp(this);
        viewTOE.showAndWait();
    }

    public boolean OpenTOEAdd(TOE typeOfEquipments) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/type_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewAddType = new Stage();
        viewAddType.setScene(new Scene(root));
        viewAddType.setTitle("Тип оборудования");
        viewAddType.setResizable(false);
        AddTypeController addTypeController = loader.getController();
        viewAddType.setOnCloseRequest(windowEvent -> {
            addTypeController.setCancel(true);
        });
        addTypeController.setTypeOfEquipments(typeOfEquipments);
        viewAddType.showAndWait();
        return addTypeController.retCancel();
    }

    public void OpenCameraView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/camera_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewCamera = new Stage();
        viewCamera.setScene(new Scene(root));
        viewCamera.setTitle("Камеры");
        viewCamera.setResizable(false);
        CameraController cameraController = loader.getController();
        cameraController.setApp(this);
        viewCamera.showAndWait();
    }

    public boolean OpenCameraAdd(Camera camera) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/camera_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewAddCamera = new Stage();
        viewAddCamera.setScene(new Scene(root));
        viewAddCamera.setTitle("Камера");
        viewAddCamera.setResizable(false);
        AddCameraController addCameraController = loader.getController();
        viewAddCamera.setOnCloseRequest(windowEvent -> {
            addCameraController.setCancel(true);
        });
        addCameraController.setCamera(camera);
        viewAddCamera.showAndWait();
        return addCameraController.retCancel();
    }

    public void OpenEquipmentView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/equipment_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewEquipment = new Stage();
        viewEquipment.setScene(new Scene(root));
        viewEquipment.setTitle("Оборудование");
        viewEquipment.setResizable(false);
        EquipmentController equipmentController = loader.getController();
        equipmentController.setApp(this);
        viewEquipment.showAndWait();
    }

    public boolean OpenEquipmentAdd(Equipment equipment) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/add_equip.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewAddEquip = new Stage();
        viewAddEquip.setScene(new Scene(root));
        viewAddEquip.setTitle("Оборудование");
        viewAddEquip.setResizable(false);
        AddEquipmentController addEquipmentController = loader.getController();
        viewAddEquip.setOnCloseRequest(windowEvent -> {
            addEquipmentController.setCancel(true);
        });
        addEquipmentController.setEquipment(equipment);
        viewAddEquip.showAndWait();
        return addEquipmentController.retCancel();
    }

    public void OpenRoomView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/room_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewRoom = new Stage();
        viewRoom.setScene(new Scene(root));
        viewRoom.setTitle("Комнаты");
        viewRoom.setResizable(false);
        RoomController roomController = loader.getController();
        roomController.setApp(this);
        viewRoom.showAndWait();
    }

    public boolean OpenRoomAdd(Room room) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/room_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage addRoom = new Stage();
        addRoom.setScene(new Scene(root));
        addRoom.setTitle("Комната");
        addRoom.setResizable(false);
        AddRoomController addRoomController = loader.getController();
        addRoom.setOnCloseRequest(windowEvent -> {
            addRoomController.setCancel(true);
        });
        addRoomController.setRoom(room);
        addRoom.showAndWait();
        return addRoomController.retCancel();
    }

    public void OpenEventView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/event_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage viewEvent = new Stage();
        viewEvent.setScene(new Scene(root));
        viewEvent.setTitle("События");
        viewEvent.setResizable(false);
        EventController eventController = loader.getController();
        eventController.setApp(this);
        viewEvent.showAndWait();
    }

    public boolean OpenEventAdd(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("../views/event_add_view.fxml"));

        VBox root = (VBox) loader.load();
        Stage addEvent = new Stage();
        addEvent.setScene(new Scene(root));
        addEvent.setTitle("Событие");
        addEvent.setResizable(false);
        AddEventController addEventController = loader.getController();
        addEvent.setOnCloseRequest(windowEvent -> {
            addEventController.setCancel(true);
        });
        addEventController.setEvent(event);
        addEvent.showAndWait();
        return addEventController.retCancel();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
