package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class TOE {
    private long id;
    private StringProperty typeofequipment = new SimpleStringProperty();

    public TOE(){}

    public TOE(String TypeOfEquipment){
            this.typeofequipment.set(TypeOfEquipment);
        }

    public void setTypeOfEquipment(String typeofequipment) {
            this.typeofequipment.set(typeofequipment);
        }

    public String getTypeOfEquipment() {
            return typeofequipment.get();
        }

    @Transient
    public StringProperty typeofequipmentProperty() {
            return typeofequipment;
        }

    public void setTypeofequipmentProperty(StringProperty typeofequipment) {
        this.typeofequipment = typeofequipment;
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
    public String toString() {
            return typeofequipment.get();
        }
}
