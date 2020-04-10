package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

//TODO:
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.PROPERTY)
public class Section {
    private long id;
    protected IntegerProperty number = new SimpleIntegerProperty();

    @Access(AccessType.FIELD)
    protected List<Camera> cameras = FXCollections.observableArrayList();
    protected IntegerProperty countCamera = new SimpleIntegerProperty();

    public void setCameras(List<Camera> cameras) {
        this.cameras.addAll(cameras);
        countCamera.set(this.cameras.size());
    }

    public void setCameras(ObservableList<Camera> cameras) {
        this.cameras = cameras;
        countCamera.set(this.cameras.size());
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public void addCamera(Camera camera){
        cameras.add(camera);
        countCamera.set(this.cameras.size());
    }

    public int getNumber() {
        return number.get();
    }

    @Transient
    public List<Camera> getCameras() {
        return cameras;
    }

    @Transient
    public ObservableList<Camera> getCamerasOList(){
        return (ObservableList<Camera>) cameras;
    }

    @Transient
    public IntegerProperty numberProperty() {
        return number;
    }

    @Transient
    public IntegerProperty count(){
        countCamera.set(this.cameras.size());
        return countCamera;
    }

    @Id
    @GeneratedValue()
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
