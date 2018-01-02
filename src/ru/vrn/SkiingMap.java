package ru.vrn;

/**
 * Wrapper for a skiing map.
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class SkiingMap {

    /**
     * Sectors.
     */
    SkiingSector[][] sectorsMap;

    /**
     * Width of the map.
     */
    int width;

    /**
     * Height of the map.
     */
    int height;

    /**
     * Construct and initialize the object.
     * @param height
     * @param width
     */
    public SkiingMap(int height, int width) {
        this.width = width;
        this.height = height;
        sectorsMap = new SkiingSector[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sectorsMap[i][j] = new SkiingSector();
            }
        }
    }

    /**
     * Returns sector's Height or MIN_VALUE in case outOfBound.
     * @param i Map's height index
     * @param j Map's width index
     * @return Sector's height
     */
    public int getSectorHeigthOrMin(int i, int j) {
        if (i < 0 || j < 0 || i >= height || j >= width) {
            return Integer.MIN_VALUE;
        }
        return sectorsMap[i][j].getHeight();
    }

    /**
     * Returns sector's Height or MAX_VALUE in case outOfBound.
     * @param i Map's height index
     * @param j Map's width index
     * @return Sector's height
     */
    public int getSectorHeigthOrMax(int i, int j) {
        if (i < 0 || j < 0 || i >= height || j >= width) {
            return Integer.MAX_VALUE;
        }
        return sectorsMap[i][j].getHeight();
    }

    //---- typical getters and setters

    public SkiingSector[][] getSectorsMap() {
        return sectorsMap;
    }

    public void setSectorsMap(SkiingSector[][] sectorsMap) {
        this.sectorsMap = sectorsMap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
