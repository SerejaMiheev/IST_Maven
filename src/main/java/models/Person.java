package models;

import javafx.beans.property.*;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Person {
    private long id;
    private StringProperty fio = new SimpleStringProperty();
    private ObjectProperty<Room> room = new SimpleObjectProperty<>();

    public Person(){
        fio.set("");
        room.set(new Room());
    }

    public  Person(String fio){
        this.fio.set(fio);
        room.set(new Room());
    }


    public Person(String fio, Room room){
        this.fio.set(fio);
        this.room.set(room);
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public Room getRoom() {
        return room.get();
    }

    @Transient
    public ObjectProperty<Room> roomProperty() {
        return room;
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    @Transient
    public StringProperty fioProperty() {
        return fio;
    }


    public String getFio() {
        return fio.get();
    }

    @Id
    @GeneratedValue()
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "ФИО: "+getFio()+"\nКомната: "+ getRoom();
    }
}
