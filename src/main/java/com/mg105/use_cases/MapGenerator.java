package com.mg105.use_cases;

import com.mg105.entities.Doorway;
import com.mg105.entities.GameState;
import com.mg105.entities.Room;
import com.mg105.utils.PointComparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.function.Predicate;

public class MapGenerator {
    private final @NotNull GameState state;
    private final @NotNull Random random;

    /**
     * Creates a new MapGenerator that acts on a GameState.
     *
     * @param state the game state.
     */
    public MapGenerator(@NotNull GameState state) {
        this(state, new Random());
    }

    /**
     * Create a new MapGenerator that acts on a GameState
     *
     * @param state  the game state.
     * @param random the random number generator.
     */
    public MapGenerator(@NotNull GameState state, @NotNull Random random) {
        this.state = state;
        this.random = random;
    }

    /**
     * Generates a map.
     */
    public void generateMap() {
        // First we create the first and last rooms and create the between rooms
        Room firstRoom = generateEmptyRoom();
        Room lastRoom  = generateEmptyRoom();
        generateBetweenRooms(firstRoom, lastRoom);

        // Finally we set the map
        state.setMap(firstRoom, lastRoom);
    }

    /**
     * Generate an empty room.
     *
     * @return and empty room containing no doorways, chests, or opponents.
     */
    private @NotNull Room generateEmptyRoom() {
        return new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Get the neighbours to a point on the map, given some rooms should be filtered out.
     *
     * @param map    the map to reference.
     * @param target the point whose neighbours we're looking for.
     * @param filter a predicate saying which points should be excluded.
     *
     * @return a list of all the neighbours satisfying the precondition.
     */
    private @NotNull List<Point> getNeighbours(@NotNull Room[][] map,
                                               @NotNull Point target,
                                               @NotNull Predicate<Room> filter) {
        List<Point> neighbours = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            neighbours.add(new Point(target));
        }

        neighbours.get(0).translate(1, 0);
        neighbours.get(1).translate(-1, 0);
        neighbours.get(2).translate(0, 1);
        neighbours.get(3).translate(0, -1);

        neighbours.removeIf((Point p) -> {
            try {
                return filter.test(map[p.y][p.x]);
            } catch (IndexOutOfBoundsException e) {
                return true;
            }
        });

        return neighbours;
    }

    /**
     * Gets the closest point to end that is connected (in a graph sense) to start.
     *
     * @param map   the map to draw adjacency from (basically the adjacency matrix).
     * @param start the starting point.
     * @param end   the target.
     *
     * @return the closet point to end that is graph-connected to start.
     */
    private @NotNull Point getClosestPointTo(@NotNull Room[][] map, @NotNull Point start, @NotNull Point end) {
        // Implementation note: this is basically just Dijkstra's algorithm on the connected points starting at start,
        // keeping note of which one's the closest to end.

        boolean[][] visited = new boolean[map.length][map[0].length];
        PointComparator comparator = new PointComparator(end);
        Queue<Point> nextPointQueue = new PriorityQueue<>(10, comparator);

        visited[start.y][start.x] = true;
        nextPointQueue.add(start);

        Point closest = start;

        while (!nextPointQueue.isEmpty()) {
            Point current = nextPointQueue.remove();

            if (comparator.distanceTo(current) <= comparator.distanceTo(closest)) {
                closest = current;
            }

            for (Point next : getNeighbours(map, current, Objects::isNull)) {
                if (!visited[next.y][next.x]) {
                    visited[next.y][next.x] = true;
                    nextPointQueue.add(next);
                }
            }
        }

        return closest;
    }

    /**
     * Generate some random layout of rooms between firstRoom and lastRoom.  This algorithm attempts to make it so
     * the room layout could plausibly exist in euclidean space, as to not confuse the user.
     *
     * @param firstRoom the starting room.
     * @param lastRoom  the end room.
     */
    private void generateBetweenRooms(@NotNull Room firstRoom, @NotNull Room lastRoom) {
        final int mapHeight = 8 + random.nextInt(4);
        final int mapWidth  = 6 + random.nextInt(3);
        Room[][] map = new Room[mapHeight][mapWidth];

        // First fill in some random 'anchor' rooms
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (random.nextInt(100) > 63) {
                    map[y][x] = generateEmptyRoom();
                }
            }
        }

        // Next we manually place the first and last room
        Point startPos = new Point(random.nextInt(mapWidth), mapHeight-1);
        Point endPos   = new Point(random.nextInt(mapWidth), 0);

        map[startPos.y][startPos.x] = firstRoom;
        map[endPos.y][endPos.x]     = lastRoom;

        //System.out.println("Anchors:");
        //printMap(map, firstRoom, lastRoom);

        // Next we connect the anchors together
        Point closest = getClosestPointTo(map, startPos, endPos);
        while (!closest.equals(endPos)) {
            List<Point> nextCandidates = getNeighbours(map, closest, Objects::nonNull);
            Point next = nextCandidates.get(random.nextInt(nextCandidates.size()));
            map[next.y][next.x] = generateEmptyRoom();

            closest = getClosestPointTo(map, startPos, endPos);
        }

        //System.out.println("Final:");
        //printMap(map, firstRoom, lastRoom);

        // Finally we actually connect the connectible rooms
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                Room currentRoom = map[y][x];
                if (currentRoom != null) {
                    List<Point> neighbours = getNeighbours(map, new Point(x, y), Objects::isNull);
                    for (Point neighbour : neighbours) {
                        Room neighbourRoom = map[neighbour.y][neighbour.x];
                        int wallPosition = 3;

                        Doorway doorway;
                        if (neighbour.y < y) {
                            // neighbourRoom is above the currentRoom
                            doorway = new Doorway(new Point(wallPosition, 0), neighbourRoom);
                        } else if (neighbour.y > y) {
                            // neighbourRoom is below the currentRoom
                            doorway = new Doorway(new Point(wallPosition, 7), neighbourRoom);
                        } else if (neighbour.x < x) {
                            // neighbourRoom is to the left of the currentRoom
                            doorway = new Doorway(new Point(0, wallPosition), neighbourRoom);
                        } else {
                            // neighbourRoom is to the right of the currentRoom\
                            doorway = new Doorway(new Point(7, wallPosition), neighbourRoom);
                        }
                        currentRoom.getDoorways().add(doorway);
                    }
                }
            }
        }

        // Note: if there are completely disconnected regions no references to them will exist at this point, and
        // so they will be handled automatically.
    }

    /**
     * Print out a map.  Used to heuristically determine if the map has a nice general shape.
     * <p>
     * Empty spaces are denoted with spaces, rooms are denoted with #.
     *
     * @param map   the map to draw.
     * @param first the first room, will be denoted by ^.
     * @param last  the last room, will be denoted by !.
     */
    private @TestOnly void printMap(@NotNull Room[][] map, @NotNull Room first, @NotNull Room last) {
        System.out.print('+');
        for (int x = 0; x < map[0].length; x++) {
            System.out.print('-');
        }
        System.out.println('+');
        for (Room[] rooms : map) {
            System.out.print('|');
            for (int x = 0; x < map[0].length; x++) {
                if (rooms[x] == null) {
                    System.out.print(' ');
                } else {
                    if (rooms[x] == first) {
                        System.out.print('^');
                    } else if (rooms[x] == last) {
                        System.out.print('!');
                    } else {
                        System.out.print('#');
                    }
                }
            }
            System.out.println('|');
        }
        System.out.print('+');
        for (int x = 0; x < map[0].length; x++) {
            System.out.print('-');
        }
        System.out.println('+');
    }
}
