package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.PROPERTY)
public abstract class Section implements EntityClass {
    protected long id;
    protected IntegerProperty number = new SimpleIntegerProperty();

    @Access(AccessType.FIELD)
    @OneToMany
    @JoinColumn(name = "section_id")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
