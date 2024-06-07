package datastructures.strings;

public class EncryptDecyrpt {

    public static void main(String[] args) {

        encryptDecrypt("world hel2o");
    }

    private static void encryptDecrypt(final String s) {

        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();

        if (words.length >= 2) {
            String firstWord = words[0];
            String lastWord = words[words.length - 1];

            // reverse them
            sb.append(lastWord);
            for (int j = 1; j < words.length - 1; j++) {
                sb.append(words[j]);
            }
            sb.append(" ");
            sb.append(firstWord);
        }

        // loop hel2o world
        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);

            if (Character.isDigit(currentChar)) {
                if (i > 0) {
                    char previousChar = sb.charAt(i - 1);
                    int repeatCount = Character.getNumericValue(currentChar);
                    String repeatedPreviousChar = String.valueOf(previousChar).repeat(repeatCount-1);
                    result.append(repeatedPreviousChar);
                }
            } else {
                result.append(currentChar);
            }
        }
        System.out.println(result);
    }
}
