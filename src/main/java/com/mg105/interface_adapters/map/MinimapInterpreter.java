package com.mg105.interface_adapters.map;

import com.mg105.use_cases.Resetable;
import com.mg105.use_cases.map.RoomGetterInterface;
import com.mg105.use_cases.outputds.RoomLayout;
import com.mg105.utils.MapConstants;
import com.mg105.utils.PointComparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The MinimapInterpreter processes room change data an interprets its implicit position.
 */
public class MinimapInterpreter implements PropertyChangeListener, Resetable, MinimapInterpreterInterface {
    private final @NotNull RoomGetterInterface getter;

    private @Nullable Point lastPosition;
    private @NotNull MinimapRoomState[][] mapSoFar;
    private final @NotNull Point currentRoom;

    /**
     * Create a new MinimapInterpreter.
     *
     * @param getter the RoomGetter that will get room information.
     */
    public MinimapInterpreter(@NotNull RoomGetterInterface getter) {
        this.getter = getter;
        currentRoom = new Point();

        reset();
    }

    /**
     * Reset the minimap to its 'factory new' state.
     */
    @Override
    public void reset() {
        lastPosition = null;
        mapSoFar = new MinimapRoomState[1][1];
        mapSoFar[0][0] = MinimapRoomState.EXPLORED;
        currentRoom.x = 0;
        currentRoom.y = 0;
    }

    /**
     * Update room position data.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RoomLayout layout = getter.getCurrentRoomLayout();

        Point currentPosition = layout.getPlayer();
        if (lastPosition == null) {
            lastPosition = currentPosition;
        }

        if ((new PointComparator(lastPosition)).distanceTo(currentPosition) > 1) { // We must have changed rooms
            if (currentPosition.y == 0) { // We moved down into a new room
                currentRoom.y += 1;
            } else if (currentPosition.y == MapConstants.ROOM_SIZE-1) { // we moved up into a new room
                currentRoom.y -= 1;
            } else if (currentPosition.x == 0) { // we move right into a new room
                currentRoom.x += 1;
            } else { // we moved left into a new room
                currentRoom.x -= 1;
            }

            autoBumpMapSoFarBecauseOf(currentRoom);
            mapSoFar[currentRoom.y][currentRoom.x] = MinimapRoomState.EXPLORED;

            // Set look for potential next rooms
            for (Point nextDoowayPosition : layout.getDoorways()) {
                Point nextRoom = new Point(currentRoom);
                if (nextDoowayPosition.y == 0) {
                    nextRoom.y -= 1;
                } else if (nextDoowayPosition.y == MapConstants.ROOM_SIZE-1) {
                    nextRoom.y += 1;
                } else if (nextDoowayPosition.x == 0) {
                    nextRoom.x -= 1;
                } else {
                    nextRoom.x += 1;
                }

                autoBumpMapSoFarBecauseOf(nextRoom, currentRoom);
                if (mapSoFar[nextRoom.y][nextRoom.x] == null) {
                    mapSoFar[nextRoom.y][nextRoom.x] = MinimapRoomState.UNEXPLORED;
                }
            }
        }

        lastPosition = currentPosition;
    }

    /**
     * Get the current explored map.
     *
     * @return the currently explored map.
     */
    @Override
    public @NotNull MinimapRoomState[][] getMapSoFar() {
        return mapSoFar;
    }

    /**
     * Get the current position in (x, y)-coordinates of the result of getMapSoFar.
     *
     * @return the current room you are in.
     */
    @Override
    public @NotNull Point getCurrentPosition() {
        return new Point(currentRoom);
    }

    /**
     * Claim: I know something about the map at point next, and would like to access its location.
     * <p>
     * After running this method that is guaranteed to be possible. This is done by 'bumping' or creating a new array
     * with the contents and other coordinates appropriately shifted.
     *
     * @param next       the coordinate causing the potential bump.
     * @param toMaintain other points that need to be maintained if a bump happens.
     */
    private void autoBumpMapSoFarBecauseOf(@NotNull Point next, @NotNull Point... toMaintain) {
        int yOffset = 0;
        int xOffset = 0;
        MinimapRoomState[][] nextMap = new MinimapRoomState[0][0];
        boolean needsBump = false;

        if (next.x < 0 || next.x >= mapSoFar[0].length) {
            nextMap = new MinimapRoomState[mapSoFar.length][mapSoFar[0].length + 1];
            if (next.x < 0) {
                xOffset = 1;
                next.x += 1;
                for (Point p : toMaintain) {
                    p.x += 1;
                }
            }
            needsBump = true;
        } else if (next.y < 0 || next.y >= mapSoFar.length) {
            nextMap = new MinimapRoomState[mapSoFar.length + 1][mapSoFar[0].length];
            if (next.y < 0) {
                yOffset = 1;
                next.y += 1;
                for (Point p : toMaintain) {
                    p.y += 1;
                }
            }
            needsBump = true;
        }

        if (needsBump) {
            for (int y = 0; y < mapSoFar.length; y++) {
                System.arraycopy(mapSoFar[y], 0, nextMap[y + yOffset], xOffset, mapSoFar[0].length);
                // This is equivalent to having
            }

            mapSoFar = nextMap;
        }
    }
}
