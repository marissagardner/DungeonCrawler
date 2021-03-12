package sample.Java;

public class DungeonRoom {

    //15 different possible rooms, equal chance of creating them
    //use binary 0001 to 1111 (no 0000 because every room has at least one exit)
    //north-south-east-west corresponding bits
    //room names will be changed to binary, roomPath will be calculated

    private int numExits;
    private int roomNum;
    private String roomPath;

    private boolean hasNorthExit;
    private boolean hasSouthExit;
    private boolean hasEastExit;
    private boolean hasWestExit;

    private boolean northFilled;
    private boolean southFilled;
    private boolean eastFilled;
    private boolean westFilled;
    private boolean exitsFilled;

    private DungeonRoom northRoom;
    private DungeonRoom southRoom;
    private DungeonRoom eastRoom;
    private DungeonRoom westRoom;

    public DungeonRoom(int north, int south, int east, int west, int num) {
        this.numExits = north + south + east + west;
        this.roomPath = "../FXML/dungeon_room_" + north + south + east + west + ".fxml";
        this.roomNum = num;

        this.hasNorthExit = north == 1;
        this.hasSouthExit = south == 1;
        this.hasEastExit = east == 1;
        this.hasWestExit = west == 1;

        this.northFilled = !this.hasNorthExit;
        this.southFilled = !this.hasSouthExit;
        this.eastFilled = !this.hasEastExit;
        this.westFilled = !this.hasWestExit;
        this.exitsFilled = false;

        this.northRoom = null;
        this.southRoom = null;
        this.eastRoom = null;
        this.westRoom = null;
    }

    public String getRoomPath() {
        return roomPath;
    }

    public int getRoomNum() { return roomNum; }

    public boolean hasNorthExit() {
        return hasNorthExit;
    }

    public boolean hasSouthExit() {
        return hasSouthExit;
    }

    public boolean hasEastExit() {
        return hasEastExit;
    }

    public boolean hasWestExit() {
        return hasWestExit;
    }

    public boolean isNorthFilled() {
        return northFilled;
    }

    public boolean isSouthFilled() {
        return southFilled;
    }

    public boolean isEastFilled() {
        return eastFilled;
    }

    public boolean isWestFilled() {
        return westFilled;
    }

    public boolean areExitsFilled() {
        return exitsFilled;
    }

    public DungeonRoom getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(DungeonRoom northRoom) {
        this.northRoom = northRoom;
        this.northFilled = true;
        this.exitsFilled = northFilled && southFilled && eastFilled && westFilled;
    }

    public DungeonRoom getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(DungeonRoom southRoom) {
        this.southRoom = southRoom;
        this.southFilled = true;
        this.exitsFilled = northFilled && southFilled && eastFilled && westFilled;
    }

    public DungeonRoom getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(DungeonRoom eastRoom) {
        this.eastRoom = eastRoom;
        this.eastFilled = true;
        this.exitsFilled = northFilled && southFilled && eastFilled && westFilled;
    }

    public DungeonRoom getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(DungeonRoom westRoom) {
        this.westRoom = westRoom;
        this.westFilled = true;
        this.exitsFilled = northFilled && southFilled && eastFilled && westFilled;
    }

    public void fillRooms() {
        if(hasNorthExit && !northFilled) {
            DungeonRoom sRoom = new DungeonRoom(0,1,0,0, 100);
            this.northRoom = sRoom;
            sRoom.setSouthRoom(this);
            this.northFilled = true;
        }
        if(hasSouthExit && !southFilled) {
            DungeonRoom nRoom = new DungeonRoom(1,0,0,0, 100);
            this.southRoom = nRoom;
            nRoom.setNorthRoom(this);
            this.southFilled = true;
        }
        if(hasEastExit && !eastFilled) {
            DungeonRoom wRoom = new DungeonRoom(0,0,0,1,100);
            this.eastRoom = wRoom;
            wRoom.setWestRoom(this);
            this.eastFilled = true;
        }
        if(hasWestExit && !westFilled) {
            DungeonRoom eRoom = new DungeonRoom(0,0,1,0,100);
            this.westRoom = eRoom;
            eRoom.setEastRoom(this);
            this.westFilled = true;
        }
        this.exitsFilled = northFilled && southFilled && eastFilled && westFilled;
    }

    public boolean isLinkedTo(DungeonRoom room) {
        boolean linked = false;
        if(hasNorthExit && northFilled) {
            if(this.northRoom.equals(room)) {
                linked = true;
            }
        }
        if(hasSouthExit && southFilled) {
            if(this.southRoom.equals(room)) {
                linked = true;
            }
        }
        if(hasEastExit && eastFilled) {
            if(this.eastRoom.equals(room)) {
                linked = true;
            }
        }
        if(hasWestExit && westFilled) {
            if(this.westRoom.equals(room)) {
                linked = true;
            }
        }
        return linked;
    }
}
