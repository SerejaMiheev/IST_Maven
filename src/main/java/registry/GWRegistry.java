package registry;

import gw.*;


public class GWRegistry {
    private CameraGateway cameraGateway = new CameraGateway();
    private EquipmentGateway equipmentGateway = new EquipmentGateway();
    private EventGateway eventGateway = new EventGateway();
    private FloorGateway floorGateway = new FloorGateway();
    private PersonGateway personGateway = new PersonGateway();
    private RoomGateway roomGateway = new RoomGateway();
    private SectionGateway sectionGateway = new SectionGateway();
    private TypeGateway typeGateway = new TypeGateway();

    public CameraGateway getCameraGateway() {
        return cameraGateway;
    }

    public EquipmentGateway getEquipmentGateway() {
        return equipmentGateway;
    }

    public EventGateway getEventGateway() {
        return eventGateway;
    }

    public FloorGateway getFloorGateway() {
        return floorGateway;
    }

    public PersonGateway getPersonGateway() {
        return personGateway;
    }

    public RoomGateway getRoomGateway() {
        return roomGateway;
    }

    public SectionGateway getSectionGateway() {
        return sectionGateway;
    }

    public TypeGateway getTypeGateway() {
        return typeGateway;
    }

    private GWRegistry(){}
    private static GWRegistry instance = new GWRegistry();
    public static GWRegistry getInstance(){return instance;}
}
