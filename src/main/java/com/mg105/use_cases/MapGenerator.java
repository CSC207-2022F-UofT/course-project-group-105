package com.mg105.use_cases;

import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.MegaPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.MapConstants;
import com.mg105.utils.MoveConstants;
import com.mg105.utils.OpponentConstants;
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
        Room firstRoom = generateEmptyRoom();
        Room lastRoom  = generateEmptyRoom();

        // Randomly generate the in between rooms
        generateBetweenRooms(firstRoom, lastRoom);
        populateBetweenRooms(firstRoom, lastRoom);

        // Hardcode the first and last rooms
        firstRoom.getChests().add(new TreasureChest(generateItem(), new Point(3, 3)));
        for (Doorway door : firstRoom.getDoorways()) {
            firstRoom.getOpponents().add(generateOpponentSetFor(door));
        }

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
        final int mapHeight = random.nextInt(MapConstants.MAPGEN_MIN_MAP_HEIGHT, MapConstants.MAPGEN_MAX_MAP_HEIGHT);
        final int mapWidth  = random.nextInt(MapConstants.MAPGEN_MIN_MAP_WIDTH,  MapConstants.MAPGEN_MAX_MAP_WIDTH);
        Room[][] map = new Room[mapHeight][mapWidth];

        // First fill in some random 'anchor' rooms
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (random.nextInt(100) > MapConstants.MAPGEN_ANCHOR_SPARSITY) {
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
                        int wallPosition = 3 + random.nextInt(MapConstants.ROOM_SIZE-6);

                        Doorway doorway;
                        if (neighbour.y < y) {
                            // neighbourRoom is above the currentRoom
                            doorway = new Doorway(new Point(wallPosition, 0), neighbourRoom);
                        } else if (neighbour.y > y) {
                            // neighbourRoom is below the currentRoom
                            doorway = new Doorway(new Point(wallPosition, MapConstants.ROOM_SIZE-1), neighbourRoom);
                        } else if (neighbour.x < x) {
                            // neighbourRoom is to the left of the currentRoom
                            doorway = new Doorway(new Point(0, wallPosition), neighbourRoom);
                        } else {
                            // neighbourRoom is to the right of the currentRoom\
                            doorway = new Doorway(new Point(MapConstants.ROOM_SIZE-1, wallPosition), neighbourRoom);
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
     * Populate room with some number of chests. (Currently only 1).
     * <p>
     * Precondition: room does not already contain a chest.
     *
     * @param room the room to populate with a chest.
     */
    private void populateRoomWithChests(@NotNull Room room) {
        assert room.getChests().size() == 0;

        Point[] possibleLocations = {
            new Point(
                MapConstants.ROOM_SIZE/2 - 1 + random.nextInt(2),
                MapConstants.ROOM_SIZE/2 - 1 + random.nextInt(2)
            ),
            new Point(1, 1),
            new Point(1, MapConstants.ROOM_SIZE-2),
            new Point(MapConstants.ROOM_SIZE-2, 1),
            new Point(MapConstants.ROOM_SIZE-2, MapConstants.ROOM_SIZE-2)
        };

        for (Point chestLocation : possibleLocations) {
            if (random.nextInt(100) > MapConstants.MAPGEN_CHEST_SPARSITY) {
                room.getChests().add(new TreasureChest(generateItem(), chestLocation));
            }
        }
    }

    /**
     * Randomly generates and Item.
     *
     * @return some random Item.
     */
    private @NotNull Item generateItem() {
        Item reward;

        // By generating two booleans, there is a 50% chance of upgrade token, 25% chance of each potion.
        if (random.nextBoolean()) {
            reward = new UpgradeToken();
        } else if (random.nextBoolean()) {
            reward = new HealthPotion();
        } else {
            reward = new MegaPotion();
        }

        return reward;
    }

    /**
     * Generate a new random Move.
     *
     * @return a random move.
     */
    private @NotNull Move generateMove() {
        String name = MoveConstants.ALL_NAMES[random.nextInt(MoveConstants.ALL_NAMES.length)];
        if (name.contains("heal")) {
            return new Move(random.nextInt(10), random.nextInt(10), name, true);
        } else {
            return new Move(-random.nextInt(10), -random.nextInt(10), name, false);
        }
    }

    /**
     * Randomly generates a list of length 4 of BattleCharacters.
     *
     * @return a list of length 4 containing BattleCharacters.
     */
    private @NotNull OpponentSet generateOpponentSetFor(@NotNull Doorway door) {
        Point battlePosition = new Point(door.getPosition());
        if (battlePosition.x == 0) {
            battlePosition.x += 1;
        } else if (battlePosition.x == MapConstants.ROOM_SIZE-1) {
            battlePosition.x -= 1;
        } else if (battlePosition.y == 0) {
            battlePosition.y += 1;
        } else {
            battlePosition.y -= 1;
        }

        List<BattleCharacter> opponents = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            opponents.add(new BattleCharacter(
                random.nextInt(5, 41),
                OpponentConstants.NAMES[random.nextInt(OpponentConstants.NAMES.length)],
                random.nextInt(1, 11),
                random.nextInt(3, 16),
                true,
                generateMove(),
                generateMove()
            ));
        }

        return new OpponentSet(battlePosition, opponents);
    }

    /**
     * Populate some doorways of room with battles.
     * <p>
     * Precondition: room does not already contain battles.
     *
     * @param room the room to populate with battles.
     */
    private void populateRoomWithBattles(@NotNull Room room) {
        assert room.getOpponents().size() == 0;

        for (Doorway door : room.getDoorways()) {
            if (random.nextInt(100) > MapConstants.MAPGEN_BATTLE_SPARSITY) {
                room.getOpponents().add(generateOpponentSetFor(door));
            }
        }
    }

    /**
     * Populate all the rooms connected between firstRoom and lastRoom with either a battle or a chest.
     *
     * @param firstRoom the first room of the map.
     * @param lastRoom  the last room of the map.
     */
    private void populateBetweenRooms(@NotNull Room firstRoom, @NotNull Room lastRoom) {
        Queue<Room> unpopulatedRooms = new LinkedList<>();
        Set<Room> visited = new HashSet<>();

        // We do BFS on all the rooms
        unpopulatedRooms.add(firstRoom);
        while (!unpopulatedRooms.isEmpty()) {
            Room current = unpopulatedRooms.remove();

            for (Doorway door: current.getDoorways()) {
                if (!visited.contains(door.getNextRoom())) {
                    unpopulatedRooms.add(door.getNextRoom());
                    visited.add(door.getNextRoom());
                }
            }

            // We leave the first and last room as hardcoded.
            if (current != firstRoom && current != lastRoom) {
                populateRoomWithChests(current);
                populateRoomWithBattles(current);
            }
        }
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
