package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Floor extends Section {
    @Access(AccessType.FIELD)
    private List<Room> roomonfloor = FXCollections.observableArrayList();
    private IntegerProperty countRoom = new SimpleIntegerProperty();

    public Floor(){}

    public Floor(int number){
        this.number.set(number);
    }

    public Floor(List<Room> roomonfloor, int number){
        this.roomonfloor = roomonfloor;
        this.number.set(number);
        countRoom.set(this.roomonfloor.size());
        countCamera.set(this.cameras.size());
    }

    public Floor(List<Room> roomonfloor){
        this.roomonfloor = roomonfloor;
        countRoom.set(this.roomonfloor.size());
        countCamera.set(this.cameras.size());
    }

    public Floor(Room roomonfloor){
        this.roomonfloor.add(roomonfloor);
        countRoom.set(this.roomonfloor.size());
        countCamera.set(this.cameras.size());
    }

    public void setRoomonfloor(ObservableList<Room> roomonfloor) {
        this.roomonfloor = roomonfloor;
        countRoom.set(this.roomonfloor.size());
    }

    public void setRooms(List<Room> roomonfloor){
        this.roomonfloor = roomonfloor;
        countRoom.set(this.roomonfloor.size());
    }

    public void addRoom(Room roomonfloor){
        this.roomonfloor.add(roomonfloor);
        countRoom.set(this.roomonfloor.size());
    }

    public int getCountRoom() {
        countRoom.set(this.roomonfloor.size());
        return countRoom.get();
    }

    private void setCountRoom(int countRoom) {
        this.countRoom.set(countRoom);
    }

    @Transient
    public IntegerProperty countRoomProperty() {
        countRoom.set(this.roomonfloor.size());
        return countRoom;
    }

    @Transient
    public ObservableList<Room> getRoomonfloor(){
        return (ObservableList<Room>) roomonfloor;
    }

    public List<Room> getRooms(){
        return roomonfloor;
    }

    @Override
    public String toString(){
        return "Номер этажа: "+getNumber()+"\nКомнаты на этаже: "+getRoomonfloor().toString();
    }
}
