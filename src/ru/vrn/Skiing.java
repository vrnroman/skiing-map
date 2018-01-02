package ru.vrn;

import java.io.IOException;

/**
 * Entry point for application that search longest path in the skiing map.
 *
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class Skiing {

    public static void main(String[] args) throws IOException {
        MapReader reader = new MapReader();
        //SkiingMap map = reader.readMapFromFile("./resources/map.txt");
        SkiingMap map = reader.readMapFromFile("./resources/bigMap.txt");
        LongestPathCalculator calculator = new LongestPathCalculator();
        System.out.println(calculator.calculateLongestPath(map));
    }
}
