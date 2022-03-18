package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.uppercase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UpperCaseLetters {

    public int getNumberOfUpperCase(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            return getNumOfUpCase(lines);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read File!");
        }
    }

    private int getNumOfUpCase(List<String> lines) {
        int numberOfUpperCase = 0;
        for (String line: lines) {
            int numOfUpCaseLine = getNumOfUpCasePerLine(line);
            numberOfUpperCase += numOfUpCaseLine;
        }
        return numberOfUpperCase;
    }

    private int getNumOfUpCasePerLine(String line) {
        int numOfUpCaseLine = 0;
        for (char charatcter: line.toCharArray()) {
            if (Character.isUpperCase(charatcter)) {
                numOfUpCaseLine++;
            }
        }
        return numOfUpCaseLine;
    }
}
