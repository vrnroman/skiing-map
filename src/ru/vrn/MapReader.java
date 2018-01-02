package ru.vrn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read data from text file.
 *
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class MapReader {

    /**
     * read all data from text ile into specified structure.
     *
     * @param filePath Path to file.
     * @return Structure with skiing map.
     * @throws IOException In case file is not correct.
     */
    public SkiingMap readMapFromFile(String filePath) throws IOException {

        SkiingMap skiingMap;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            int width, height;

            String widthHeight[] = bufferedReader.readLine().split("\\s+");
            width = Integer.parseInt(widthHeight[0]);
            height = Integer.parseInt(widthHeight[1]);

            skiingMap = new SkiingMap(height, width);

            for (int i = 0; i < height; i++) {
                String[] horizontalArray = bufferedReader.readLine().split("\\s+");
                for (int j = 0; j < width; j++) {

                    skiingMap.getSectorsMap()[i][j].setHeight(Integer.parseInt(horizontalArray[j]));
                }
            }

        } catch (IOException ex) {
            throw new IllegalArgumentException("Incorrect input file");
        }
        return skiingMap;
    }

}
