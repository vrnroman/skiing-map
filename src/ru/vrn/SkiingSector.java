package ru.vrn;

/**
 * Represents information about sector.
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class SkiingSector implements Comparable<SkiingSector> {

    /**
     * Height.
     */
    int height;

    /**
     * Could we go to this sector from the top sector?
     */
    boolean availableTop;

    /**
     * Could we go to this sector from the right sector?
     */
    boolean availableRight;

    /**
     * Could we go to this sector from the bottom sector?
     */
    boolean availableBottom;

    /**
     * Could we go to this sector from the left sector?
     */
    boolean availableLeft;

    /**
     * How many sectors available in case we start here?
     */
    int nextSectorsCount;

    /**
     * What is the height of the last sector in longest path?
     */
    int lastSectorHeight;

    public int compareTo(SkiingSector another) {
        if (nextSectorsCount > another.getNextSectorsCount()) {
            return 1;
        } else if (nextSectorsCount < another.getNextSectorsCount()) {
            return -1;
        }
        return (height - lastSectorHeight) - (another.getHeight() - another.getLastSectorHeight());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAvailableTop() {
        return availableTop;
    }

    public void setAvailableTop(boolean availableTop) {
        this.availableTop = availableTop;
    }

    public boolean isAvailableRight() {
        return availableRight;
    }

    public void setAvailableRight(boolean availableRight) {
        this.availableRight = availableRight;
    }

    public boolean isAvailableBottom() {
        return availableBottom;
    }

    public void setAvailableBottom(boolean availableBottom) {
        this.availableBottom = availableBottom;
    }

    public boolean isAvailableLeft() {
        return availableLeft;
    }

    public void setAvailableLeft(boolean availableLeft) {
        this.availableLeft = availableLeft;
    }

    public int getNextSectorsCount() {
        return nextSectorsCount;
    }

    public void setNextSectorsCount(int nextSectorsCount) {
        this.nextSectorsCount = nextSectorsCount;
    }

    public int getLastSectorHeight() {
        return lastSectorHeight;
    }

    public void setLastSectorHeight(int lastSectorHeight) {
        this.lastSectorHeight = lastSectorHeight;
    }
}
