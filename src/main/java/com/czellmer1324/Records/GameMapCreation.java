package com.czellmer1324.Records;

import com.czellmer1324.Game.Room.Room;
import com.czellmer1324.Game.Room.RoomGraph;

public record GameMapCreation(Room startingRoom, RoomGraph map) {
}
