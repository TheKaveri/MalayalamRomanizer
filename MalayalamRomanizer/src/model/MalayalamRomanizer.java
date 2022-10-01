package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MalayalamRomanizer {

    public static String romanizer(String malText) throws FileNotFoundException {
        StringBuilder romanizedText = new StringBuilder();
        for (char c : malText.toCharArray()) {
            if (isVowelSign(c)) {
                // removes the extra 'a' at the end before applying the vowel signs
                romanizedText = new StringBuilder(romanizedText.substring(0, romanizedText.length() - 1));
            }
            romanizedText.append(romanizerHelper(c));
        }

        romanizedText = new StringBuilder(formatter(romanizedText.toString()));
        return romanizedText.toString();
    }

    public static String romanizerHelper(char malChar) throws FileNotFoundException {
        char min = '\u0D00';
        char max = '\u0D7F';
        if (malChar > max || malChar < min) { // checks if malChar is convertible
            return malChar + "";
        }

        String romanizedStr = "";
        Scanner input = new Scanner(new File("MalayalamRomanizer/src/model/romanizedMalayalamCharacters.txt"));

        while (input.hasNext()) {
            String s = input.next();
            if (s.equals(malChar + "")) {
                romanizedStr = input.next();
                break;
            }
        }

        return romanizedStr;
    }

    public static boolean isVowelSign(char c) {
        String VOWEL_SIGNS = "ാിീുൂൃൄെേൈൊോൌ്ൗ";
        return VOWEL_SIGNS.contains(c + "");
    }

    public static String formatter(String romanizedText) {
        StringBuilder finalRomanizedText = new StringBuilder();

        romanizedText += " ";
        for (int i = 0; i < romanizedText.length() - 1; i++) {
            char c = romanizedText.charAt(i);
            if (c == '★') {
                if (!Character.isAlphabetic(romanizedText.charAt(i + 1))) {
                    // changes "്" from "★" to ""
                    finalRomanizedText.append("u");
                }
//                } else {
//                    // changes "്" from "★" to "u"
//                }
            } else {
                finalRomanizedText.append(c);
            }
        }

        // changes "റ്റ" from "rrrr" to "tt"
        finalRomanizedText = new StringBuilder(finalRomanizedText.toString().replaceAll("rrrr", "tt"));

        return finalRomanizedText.toString();
    }
}


