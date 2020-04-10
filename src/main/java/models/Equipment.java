package models;

import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Equipment {
    private long id;
    private IntegerProperty count = new SimpleIntegerProperty();
    private CheckBox selected = new CheckBox();
    private ObjectProperty<TOE> type = new SimpleObjectProperty<>();

    public Equipment(){
        this.count.set(0);
        this.type.set(new TOE(""));
    }

    public Equipment(TOE type, int count){
        this.type.set(type);
        this.count.set(count);
    }

    public void setTypeOfEquipment(TOE type){
        this.type.set(type);
    }

    public TOE getTypeOfEquipment(){
        return type.get();
    }

    public void setCountOfEquipment(int count){
        this.count.set(count);
    }

    @Transient
    public IntegerProperty countProperty() {
        return count;
    }

    @Transient
    public ObjectProperty<TOE> typeProperty() {
        return type;
    }

    public int getCountOfEquipment(){
        return count.get();
    }

    @Transient
    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    @Transient
    public StringProperty typeStringProperty(){
        return new SimpleStringProperty(toString());
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
        return getTypeOfEquipment().toString()+", кол-во: "+getCountOfEquipment();
    }
}
