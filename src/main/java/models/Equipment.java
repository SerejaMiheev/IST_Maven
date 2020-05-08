package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Equipment implements EntityClass {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "toe_id", nullable = false)
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(final Object other){
        if (this == other)
            return true;
        if (!(other instanceof Equipment))
            return false;
        return this.id == ((Equipment) other).getId();
    }

}
