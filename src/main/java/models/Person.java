package models;

import javafx.beans.property.*;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Person implements EntityClass {
    private long id;
    private LongProperty idProperty = new SimpleLongProperty();
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
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

    @Transient
    public long getIdProperty() {
        return idProperty.get();
    }

    public LongProperty idPropertyProperty() {
        return idProperty;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
        this.idProperty.set(id);
    }

    @Override
    public String toString(){
        return "ФИО: "+getFio()+"\nКомната: "+ getRoom();
    }
}
