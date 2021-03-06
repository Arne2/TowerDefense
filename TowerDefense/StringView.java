import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author arne2
 */
public class StringView {
    public static String personMap(final MyWorld world) {
        final StringBuilder buffer = new StringBuilder();

        final Location edge = world.getLocations().stream()
                .max(Location::compareTo).orElse(Location.of(0, 0));

        for (int y = 0; y <= edge.y; y++) {
            for (int x = 0; x <= edge.x; x++) {
                final Location point = Location.of(x, y);
                if (world.getLocations().contains(point)) {
                    if (world.getGoal().equals(point)) {
                        buffer.append("[X]");
                    } else if (!world.getLocations().contains(point)) {
                        buffer.append("[ ]");
                    } else {
                        buffer.append("[p]");
                    }
                } else {
                    buffer.append("   ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     *
     * @param world
     * @param use
     * @return
     */
    public static String useMap(final MyWorld world, final Map<Location, Double> use) {
        final StringBuilder buffer = new StringBuilder();

        final Location edge = world.getLocations().stream()
                .max(Location::compareTo).orElse(Location.of(0, 0));

        for (int y = 0; y <= edge.y; y++) {
            for (int x = 0; x <= edge.x; x++) {
                final Location point = Location.of(x, y);
                if (world.getLocations().contains(point)) {
                    if (use.containsKey(point)) {
                        buffer.append(String.format("[%5.02f]", use.get(point)));
                    } else {
                        buffer.append("[?????]");
                    }
                } else {
                    buffer.append("       ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Reads an input String and generates a Field out of it.
     * @param map
     * @param cellSize
     * @return generated Field
     */
    public static Set<Location> parseStringMap(final String map, double cellSize) {
        final String[] rows = map.split("\n");
        final Set<Location> field = new HashSet();

        for (int y = 0; y < rows.length; y++) {
            final String row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                final char c = row.charAt(x);
                if (c == '0') {
                    field.add(Location.of(x, y));
                } else if (c == 'X') {
                    field.add(Location.of(x, y));
                }
            }
        }

        return field;
    }

    /**
     * Returns a String which contains distance map of a given Map.
     * @param use
     * @return String distance map.
     */
    public static String toStringDistanceMap(Map<Location, Double> use){
        final StringBuilder buffer = new StringBuilder();

        final Location edge = use.keySet().stream()
                .max(Location::compareTo).orElse(Location.of(0, 0));

        for (int y = 0; y <= edge.y; y++) {
            for (int x = 0; x <= edge.x; x++) {
                if (use.keySet().contains(Location.of(x, y))) {
                    buffer.append(String.valueOf(use.get(Location.of(x, y))) + " ");

                } else {
                    buffer.append(-Double.MAX_VALUE + " ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Generates a String View out of a given Field.
     * @param world
     * @return String View of the Field.
     */
    public static String toStringMap(MyWorld world) {
        final StringBuilder buffer = new StringBuilder();

        final Location edge = world.getLocations().stream()
                .max(Location::compareTo).orElse(Location.of(0, 0));

        for (int y = 0; y <= edge.y; y++) {
            for (int x = 0; x <= edge.x; x++) {
                if (world.getGoal().equals(Location.of(x, y))) {
                    buffer.append("X");
                } else if (world.getLocations().contains(Location.of(x, y))) {
                    buffer.append("0");
                } else {
                    buffer.append(" ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
