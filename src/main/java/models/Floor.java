package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Floor extends Section implements EntityClass {
    @Access(AccessType.FIELD)
    @OneToMany
    @JoinColumn(name = "floor_id")
    private List<Room> rooms = FXCollections.observableArrayList();
    private IntegerProperty countRoom = new SimpleIntegerProperty();

    public Floor(){}

    public Floor(int number){
        this.number.set(number);
    }

    public Floor(List<Room> rooms, int number){
        this.rooms = rooms;
        this.number.set(number);
        countRoom.set(this.rooms.size());
        countCamera.set(this.cameras.size());
    }

    public Floor(List<Room> rooms){
        this.rooms = rooms;
        countRoom.set(this.rooms.size());
        countCamera.set(this.cameras.size());
    }

    public Floor(Room rooms){
        this.rooms.add(rooms);
        countRoom.set(this.rooms.size());
        countCamera.set(this.cameras.size());
    }

    public void setRoomonfloor(ObservableList<Room> rooms) {
        this.rooms = rooms;
        countRoom.set(this.rooms.size());
    }

    public void setRooms(List<Room> rooms){
        this.rooms = rooms;
        countRoom.set(this.rooms.size());
    }

    public void addRoom(Room rooms){
        this.rooms.add(rooms);
        countRoom.set(this.rooms.size());
    }

    public int getCountRoom() {
        countRoom.set(this.rooms.size());
        return countRoom.get();
    }

    private void setCountRoom(int countRoom) {
        this.countRoom.set(countRoom);
    }

    @Transient
    public IntegerProperty countRoomProperty() {
        countRoom.set(this.rooms.size());
        return countRoom;
    }

    @Transient
    public ObservableList<Room> getRoomonfloor(){
        return (ObservableList<Room>) rooms;
    }

    @Transient
    public List<Room> getRooms(){
        return rooms;
    }

    @Override
    public String toString(){
        return "Номер этажа: "+getNumber()+"\nКомнаты на этаже: "+getRoomonfloor().toString();
    }
}
//@OneToMany(mappedBy = "floor", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)