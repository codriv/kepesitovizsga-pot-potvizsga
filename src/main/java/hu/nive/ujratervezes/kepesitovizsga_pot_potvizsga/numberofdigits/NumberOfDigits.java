package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.numberofdigits;

public class NumberOfDigits {

    public int getNumberOfDigits(int number) {
        String string = String.valueOf(number);
        int length = string.length();
        if (length == 1) {
            return number;
        } else {
            return getNumber(string, length);
        }
    }

    private int getNumber(String string, int length) {
        String fromSecond = string.substring(1);
        int remainder = (Integer.parseInt(fromSecond) + 1) * length;
        int withoutRemainder = 0;
        for (int i = 1; i < length; i++) {
            withoutRemainder += 9 * i * Math.pow(10, (i - 1));
        }
        return remainder + withoutRemainder;
    }
}
