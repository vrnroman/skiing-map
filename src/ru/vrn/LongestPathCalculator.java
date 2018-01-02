package ru.vrn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Calculates longest path in the skiing map.
 * Algorithms tries to start from the end of the path and go higher.
 * If processed step (from one sector to another) looks good we add another sector into processed queue.
 *
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class LongestPathCalculator {


    /**
     * Calculates the longest path in the skiing map.
     * @param map Skiing map.
     * @return Description of the longest path.
     */
    public String calculateLongestPath(SkiingMap map) {
        Queue<Pair> queue = new LinkedList<>();
        //init sectors
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                SkiingSector sector = map.getSectorsMap()[i][j];
                //test and set sectors availability from each other
                sector.setAvailableTop(sector.getHeight() < map.getSectorHeigthOrMin(i + 1, j));
                sector.setAvailableRight(sector.getHeight() < map.getSectorHeigthOrMin(i, j + 1));
                sector.setAvailableBottom(sector.getHeight() < map.getSectorHeigthOrMin(i - 1, j));
                sector.setAvailableLeft(sector.getHeight() < map.getSectorHeigthOrMin(i, j - 1));

                //in case sector is local minimum, put coordinates into queue
                if (isLocalMinimum(map, i, j)) {
                    sector.setLastSectorHeight(sector.getHeight());
                    queue.offer(new Pair(i, j));
                }
            }
        }
        //main cycle: test neighbors and put them into queue
        while (!queue.isEmpty()) {
            Pair coordinates = queue.poll();
            SkiingSector sector = map.getSectorsMap()[coordinates.getLeft()][coordinates.getRight()];
            if (sector.isAvailableTop()) {
                testNeighborAndPutIntoQueue(map, coordinates.getLeft() + 1, coordinates.getRight(), sector, queue);
            }
            if (sector.isAvailableRight()) {
                testNeighborAndPutIntoQueue(map, coordinates.getLeft(), coordinates.getRight() + 1, sector, queue);
            }
            if (sector.isAvailableBottom()) {
                testNeighborAndPutIntoQueue(map, coordinates.getLeft() - 1, coordinates.getRight(), sector, queue);
            }
            if (sector.isAvailableLeft()) {
                testNeighborAndPutIntoQueue(map, coordinates.getLeft(), coordinates.getRight() - 1, sector, queue);
            }
        }
        //find best start position
        return findBestOption(map);
    }

    /**
     * Find the best start point.
     * @param map Skiing map.
     * @return Description of the longest path.
     */
    private String findBestOption(SkiingMap map) {
        SkiingSector currentBest = map.getSectorsMap()[0][0];
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                SkiingSector candidate = map.getSectorsMap()[i][j];
                if (candidate.compareTo(currentBest) > 0) {
                    currentBest = candidate;
                }
            }
        }
        return String.format("Longest path: %s; largest drop: %s", currentBest.getNextSectorsCount() + 1,
                currentBest.getHeight() - currentBest.getLastSectorHeight());
    }

    /**
     * Test (neighbor) sector and add it into the processing queue in case current path in better than previous.
     * @param map Skiing map.
     * @param height Height coordinate.
     * @param width Width coordinate.
     * @param original Original sector (we are going from this sector)
     * @param queue Processing queue.
     */
    private void testNeighborAndPutIntoQueue(SkiingMap map, int height, int width, SkiingSector original, Queue<Pair> queue) {
        SkiingSector neighborSector = map.getSectorsMap()[height][width];
        if (neighborSector.getNextSectorsCount() < original.getNextSectorsCount() + 1
                || (neighborSector.getNextSectorsCount() == original.getNextSectorsCount())
                && neighborSector.getLastSectorHeight() > original.getLastSectorHeight()) {
            neighborSector.setNextSectorsCount(original.getNextSectorsCount() + 1);
            neighborSector.setLastSectorHeight(original.getLastSectorHeight());
            queue.offer(new Pair(height, width));
        }
    }

    /**
     * Detect local minimum in the skiing map. It gives us start points for the algorithm.
     * At the same time, this points are the end points for the skiing path.
     * @param map Skiing map.
     * @param height Height coordinate.
     * @param width Width coordinate.
     * @return Is the point local minimum of our skiing map.
     */
    private boolean isLocalMinimum(SkiingMap map, int height, int width) {
        SkiingSector sector = map.getSectorsMap()[height][width];
        return sector.getHeight() <= map.getSectorHeigthOrMax(height + 1, width)
                && sector.getHeight() <= map.getSectorHeigthOrMax(height, width + 1)
                && sector.getHeight() <= map.getSectorHeigthOrMax(height - 1, width)
                && sector.getHeight() <= map.getSectorHeigthOrMax(height, width - 1);
    }
}
