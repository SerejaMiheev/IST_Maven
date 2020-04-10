package models;

import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Camera {
    private long id;
    private IntegerProperty numbercam = new SimpleIntegerProperty();
    private StringProperty locrecord = new SimpleStringProperty();
    private ObjectProperty<Section> section = new SimpleObjectProperty<>();
    private CheckBox selected = new CheckBox();

    public Camera() {
        numbercam.set(0);
        locrecord.set("");
    }

    public Camera(int numbercam, String locrecord, Section section) {
        this.numbercam.set(numbercam);
        this.locrecord.set(locrecord);
        this.section.set(section);
    }

    public Camera(int numbercam, String locrecord) {
        this.numbercam.set(numbercam);
        this.locrecord.set(locrecord);
    }

    public void setSection(Section section) {
        this.section.set(section);
    }

    public void setLocrecord(String locrecord) {
        this.locrecord.set(locrecord);
    }

    public void setNumbercam(int numbercam) {
        this.numbercam.set(numbercam);
    }

    public Section getSection() {
        return section.get();
    }

    public int getNumbercam() {
        return numbercam.get();
    }

    @Transient
    public IntegerProperty numbercamProperty() {
        return numbercam;
    }

    public String getLocrecord() {
        return locrecord.get();
    }

    @Transient
    public StringProperty locrecordProperty() {
        return locrecord;
    }

    @Transient
    public ObjectProperty<Section> sectionProperty() {
        return section;
    }

    @Transient
    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
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
        return "Номер камеры: " + getNumbercam() + ". Путь записи: " + getLocrecord();
}
}
