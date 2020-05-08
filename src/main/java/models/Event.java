package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Event implements EntityClass {
    private long id;
    private ObjectProperty<Date> date = new SimpleObjectProperty<>();
    private ObjectProperty<Camera> camera = new SimpleObjectProperty<>();
    private ObjectProperty<Person> person = new SimpleObjectProperty<>();
    private StringProperty record = new SimpleStringProperty();

    public Event(){
        date.set(new Date());
    }

    public Event(Camera camera, Person person){
        date.set(new Date());
        this.camera.set(camera);
        this.person.set(person);
    }

    public Event(Camera camera){
        date.set(new Date());;
        this.camera.set(camera);
    }

    public void setRecord(String record) {
            this.record.set(record);
        }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id", nullable = false)
    public Camera getCamera() {
            return camera.get();
        }

    @Transient
    public ObjectProperty<Date> getDateProperty() {
        return date;
    }

    @org.hibernate.annotations.CreationTimestamp
    private void setDate(Date date) {
        this.date.set(date);
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
            return date.get();
        }

    public String getRecord() {
            return record.get();
        }

    @Transient
    public ObjectProperty<Camera> cameraProperty() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera.set(camera);
    }

    @Transient
    public ObjectProperty<Person> personProperty() {
        return person;
    }

    public void setPerson(Person person) {
        this.person.set(person);
    }

    @Transient
    public StringProperty recordProperty() {
        return record;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    public Person getPerson() {
            return person.get();
        }

    @Transient
    public StringProperty stringProperty(){
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
    public String toString() {
        if(person.get() != null) {
            return "Человек: " + getPerson().toString() + "\n" + getCamera().toString();
        }
        else {
            return getCamera().toString();
        }
    }
}
