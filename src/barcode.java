// barcode
// Jerred Shepherd

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class barcode {
    private static final String ZERO_CODE = "nnwwn";
    private static final String ONE_CODE = "wnnnw";
    private static final String TWO_CODE = "nwnnw";
    private static final String THREE_CODE = "wwnnn";
    private static final String FOUR_CODE = "nnwnw";
    private static final String FIVE_CODE = "wnwnn";
    private static final String SIX_CODE = "nwwnn";
    private static final String SEVEN_CODE = "nnnww";
    private static final String EIGHT_CODE = "wnnwn";
    private static final String NINE_CODE = "nwnwn";
    private static final String START_CODE = "NnNn";
    private static final String END_CODE = "WnN";


    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("barcode.in");
        File outputFile = new File("barcode.out");

        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        String line;
        String barcode;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (Double.valueOf(line) == 0) {
                break;
            }

            barcode = generateBarcode(line);

            System.out.println(barcode);
            printWriter.println(barcode);
        }

        printWriter.close();
    }

    private static String generateBarcode(String s) {
        StringBuilder sb = new StringBuilder();
        s = addLeadingZero(s);
        System.out.println(s);

        sb.append(START_CODE);
        for (int i = 0; i < s.length() - 1; i+= 2) {
            char[] sChars = s.substring(i, i + 2).toCharArray();
            char l = sChars[0];
            char r = sChars[1];
            sb.append(generatePairString(l, r));
        }
        sb.append(END_CODE);
        return sb.toString();
    }

    private static String addLeadingZero(String s) {
        if (s.length() % 2 != 0) {
            s = 0 + s;
        }
        return s;
    }

    private static String generatePairString(char l, char r) {
        String lCode = getCode(l);
        String rCode = getCode(r);
        return combineStrings(lCode.toUpperCase(), rCode.toLowerCase());
    }

    private static String combineStrings(String l, String r) {
        char[] lChars = l.toCharArray();
        char[] rChars = r.toCharArray();
        char[] combined = new char[10];
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                combined[i] = lChars[i / 2];
            } else {
                combined[i] = rChars[i / 2];
            }
        }
        return String.valueOf(combined);
    }

    private static String getCode(char c) {
        switch (c) {
            case '0':
                return ZERO_CODE;
            case '1':
                return ONE_CODE;
            case '2':
                return TWO_CODE;
            case '3':
                return THREE_CODE;
            case '4':
                return FOUR_CODE;
            case '5':
                return FIVE_CODE;
            case '6':
                return SIX_CODE;
            case '7':
                return SEVEN_CODE;
            case '8':
                return EIGHT_CODE;
            case '9':
                return NINE_CODE;
            default:
                throw new IllegalArgumentException("Invalid argument: " + c);
        }
    }

}
