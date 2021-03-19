public class DataTransmission {
    int id;
    int componentID;
    String type;
    boolean missionFailure;
    boolean componentNeedResponse;
    DataTransmission(int id, int componentID, String type, boolean missionFailure, boolean componentNeedResponse) {
        this.id = id;
        this.componentID = componentID;
        this.type = type;
        this.missionFailure = missionFailure;
        this.componentNeedResponse = componentNeedResponse;
    }
}