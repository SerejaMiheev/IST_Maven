package models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import models.Equipment;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Room extends Section implements EntityClass {
    @Access(AccessType.FIELD)
    @ManyToMany
    @JoinTable(name = "equipment_room", joinColumns = @JoinColumn(name = "ROOM_ID"),
            inverseJoinColumns = @JoinColumn(name = "EQUIPMENT_ID"))
    protected List<Equipment> equipments = FXCollections.observableArrayList();

    @Access(AccessType.FIELD)
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Person> persons = FXCollections.observableArrayList();
    private ObjectProperty<Floor> floor = new SimpleObjectProperty<>();
    private CheckBox selected = new CheckBox();

    public Room(){
        number.set(0);
    }

    public Room(int number){
        this.number.set(number);
    }

    public Room(List<Equipment> equipments, int number){
        this.equipments = equipments;
        this.number.set(number);
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addEquipments(Equipment equipments){
        this.equipments.add(equipments);
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipmentsO(ObservableList<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Transient
    public ObservableList<Equipment> getEquipmentsO(){return (ObservableList<Equipment>) equipments;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    public Floor getFloor() {
        return floor.get();
    }

    @Transient
    public ObjectProperty<Floor> floorProperty() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor.set(floor);
    }

    @Transient
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Transient
    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(final Object other){
        if (this == other)
            return true;
        if (!(other instanceof Room))
            return false;
        return this.id == ((Room) other).getId();
    }

    @Transient
    public StringProperty stringProperty(){
        return new SimpleStringProperty(toString());
    }

    @Override
    public String toString(){
        return String.valueOf(getNumber());
    }

    /*public String toString(){
        if ((!getEquipments().isEmpty()) && (!getCameras().isEmpty())){
            return "Номер комнаты: "+getNumber()+"\nСписок оборудования: "+getEquipments().toString()+"\nСписок камер: "+getCameras().toString();
        }
        else {
            if((!getEquipments().isEmpty()) && (getCameras().isEmpty())){
                return "Номер комнаты: "+getNumber()+"\nСписок оборудования: "+getEquipments().toString();
            }
            else{
                if((getEquipments().isEmpty()) && (!getCameras().isEmpty())){
                    return "Номер комнаты: "+getNumber()+"\nСписок камер: "+getCameras().toString();
                }
                else {
                    return "Номер комнаты: "+getNumber();
                }
            }
        }
    }*/
}
