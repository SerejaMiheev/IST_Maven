package models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import models.Equipment;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Room extends Section {
    @Access(AccessType.FIELD)
    private List<Equipment> equipments = FXCollections.observableArrayList();
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

    @Transient
    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
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
