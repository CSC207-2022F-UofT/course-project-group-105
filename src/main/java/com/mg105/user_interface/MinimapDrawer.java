package com.mg105.user_interface;

import com.mg105.interface_adapters.map.MinimapInterpreter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Draw the Minimap.
 */
public class MinimapDrawer implements Toggleable {
    private static final int CANVAS_SIZE = 400;

    private final @NotNull MinimapInterpreter interpreter;

    private final @NotNull Scene scene;
    private final @NotNull Group group;

    private static final @NotNull Color PATH_COLOR = Color.rgb(135, 160, 227);
    private static final @NotNull Color ROOM_COLOR = Color.rgb(205, 220, 255);
    private static final @NotNull Color CURRENT_ROOM_COLOR = Color.rgb(200, 150, 61);
    private static final @NotNull Color BACKGROUND_COLOR = Color.rgb(38, 44, 68);

    /**
     * Create a new MinimapDrawer.
     *
     * @param interpreter the MinimapInterpreter that will process room change data.
     */
    public MinimapDrawer(@NotNull MinimapInterpreter interpreter) {
        this.interpreter = interpreter;

        group = new Group();
        scene = new Scene(group, CANVAS_SIZE, CANVAS_SIZE);
        scene.setFill(BACKGROUND_COLOR);
    }

    /**
     * Get the minimap scene.
     *
     * @return the minimap scene.
     */
    @Override
    public @NotNull Scene getScene() {
        return scene;
    }

    /**
     * Toggle the minimap.
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    @Override
    public void toggle(boolean isVisible) {
        if (isVisible) {
            MinimapInterpreter.RoomState[][] map = interpreter.getMapSoFar();
            Point currentPosition = interpreter.getCurrentPosition();

            final int cellDimension = CANVAS_SIZE / Math.max(map.length, map[0].length);
            final int innerCellPadding = cellDimension / 6;

            final int topPadding = (CANVAS_SIZE - map.length * cellDimension) / 2;
            final int leftPadding = (CANVAS_SIZE - map[0].length * cellDimension) / 2;

            group.getChildren().clear();
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    // Shading for the current room
                    if (currentPosition.x == x && currentPosition.y == y) {
                        Rectangle r = new Rectangle();
                        r.setX(leftPadding + x * cellDimension);
                        r.setY(topPadding + y * cellDimension);
                        r.setWidth(cellDimension);
                        r.setHeight(cellDimension);
                        r.setFill(CURRENT_ROOM_COLOR);
                        group.getChildren().add(r);
                    }

                    // Rectangle for each room
                    if (map[y][x] == MinimapInterpreter.RoomState.EXPLORED) {
                        // First we draw the middle square
                        Rectangle r = new Rectangle();
                        r.setX(leftPadding + innerCellPadding + x * cellDimension);
                        r.setY(topPadding + innerCellPadding + y * cellDimension);
                        r.setWidth(cellDimension - 2 * innerCellPadding);
                        r.setHeight(cellDimension - 2 * innerCellPadding);
                        r.setFill(ROOM_COLOR);
                        group.getChildren().add(r);

                        // Now we draw the lines that sick out
                        final int xMidpoint = leftPadding +  x * cellDimension + cellDimension / 2;
                        final int yMidpoint = topPadding + y * cellDimension + cellDimension / 2;
                        final int strokeWidth = cellDimension / 10;
                        final int strokeWidthCorrection = strokeWidth / 2;

                        // Line coming out the top
                        if (y > 0 && map[y-1][x] != null) {
                            Line l = new Line();
                            l.setStartX(xMidpoint);
                            l.setEndX(xMidpoint);
                            l.setStartY(topPadding + y * cellDimension + innerCellPadding - strokeWidthCorrection);
                            l.setEndY(topPadding + y * cellDimension + strokeWidthCorrection);
                            l.setStroke(PATH_COLOR);
                            l.setStrokeWidth(strokeWidth);
                            group.getChildren().add(l);
                        }

                        // Line coming out the bottom
                        if (y < map.length - 1 && map[y+1][x] != null) {
                            Line l = new Line();
                            l.setStartX(xMidpoint);
                            l.setEndX(xMidpoint);
                            l.setStartY(topPadding + y * cellDimension + cellDimension - innerCellPadding + strokeWidthCorrection);
                            l.setEndY(topPadding + y * cellDimension + cellDimension - strokeWidthCorrection);
                            l.setStroke(PATH_COLOR);
                            l.setStrokeWidth(strokeWidth);
                            group.getChildren().add(l);
                        }

                        // Line coming out the left
                        if (x > 0 && map[y][x-1] != null) {
                            Line l = new Line();
                            l.setStartX(leftPadding + x * cellDimension + innerCellPadding - strokeWidthCorrection);
                            l.setEndX(leftPadding + x * cellDimension + strokeWidthCorrection);
                            l.setStartY(yMidpoint);
                            l.setEndY(yMidpoint);
                            l.setStroke(PATH_COLOR);
                            l.setStrokeWidth(strokeWidth);
                            group.getChildren().add(l);
                        }

                        // Line coming out the right
                        if (x < map[0].length - 1 && map[y][x+1] != null) {
                            Line l = new Line();
                            l.setStartX(leftPadding + x * cellDimension + cellDimension - innerCellPadding + strokeWidthCorrection);
                            l.setEndX(leftPadding + x * cellDimension + cellDimension - strokeWidthCorrection);
                            l.setStartY(yMidpoint);
                            l.setEndY(yMidpoint);
                            l.setStroke(PATH_COLOR);
                            l.setStrokeWidth(strokeWidth);
                            group.getChildren().add(l);
                        }
                    }
                }
            }
        }
    }
}
