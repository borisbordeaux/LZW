/*
 * This project is for educational purpose
 */
package lzw_compress;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * LZW class that offers methods to compress and decompress a message with LZW
 * algorithm
 *
 * @author boris
 */
public class LZW {

    /**
     * Compresses a message using the LZW compression method
     *
     * @param message the message to compress
     * @return the compressed message
     */
    public static String compress(String message) {
        //init dict with ASCII values
        HashMap<Integer, String> dict = initDictWithASCII();
        String encodedMessage = "";

        //chain takes first char
        String chain = "" + message.charAt(0);
        int index = 1;

        //while not end
        while (index < message.length()) {

            //character takes next char of message
            char character = message.charAt(index);
            index++;

            //if chain + character is in dict
            if (dict.containsValue(chain + character)) {
                //concatenate chain + character
                chain += character;
            } else {
                //add code of chain to output
                //a space is here to facilitate
                //the reading of the result
                encodedMessage += getKeyByValue(dict, chain) + " ";

                //save chain + caracter to dict
                dict.put(dict.size(), chain + character);

                //affect character to chain
                chain = "" + character;
            }
        }
        //add code of chain to output
        encodedMessage += getKeyByValue(dict, chain);

        //print dict (for non ascii values)
        System.out.println("dict after compression:");
        printDict(dict);

        //return compressed string
        return encodedMessage;
    }

    /**
     * Decompresses a message previously compressed with LZW method
     *
     * @param encodedMessage the message to decompress
     * @return the decompressed message
     */
    public static String decompress(String encodedMessage) {
        //init dict with ASCII values
        HashMap<Integer, String> dict = initDictWithASCII();

        //separate message to get each codes
        String[] messageCodes = encodedMessage.split(" ");

        //old code takes the first code
        String oldCode = messageCodes[0];
        int index = 1;

        //write corresponding character of old code
        String decodedMessage = dict.get(Integer.parseInt(oldCode));

        //character takes corresponding character of old code
        String character = dict.get(Integer.parseInt(oldCode));

        //while not the end of the message
        while (index < messageCodes.length) {
            //new code takes next code in message
            String newCode = messageCodes[index];
            index++;

            //chain takes a value depending on if
            //the dict contains new code
            String chain;
            if (dict.containsKey(Integer.parseInt(newCode))) {
                //if is does, chain takes corresponding
                //character of new code
                chain = dict.get(Integer.parseInt(newCode));
            } else {
                //if it doesn't, chain takes corresponding character
                //of old code concatenated to character
                chain = dict.get(Integer.parseInt(oldCode)) + character;
            }

            //write chain to the output
            decodedMessage += chain;

            //caractere = premier caractere de chaine
            //character takes first char of chain
            character = "" + chain.charAt(0);

            //add corresponding character of old code
            //concatenated to character to dict
            dict.put(dict.size(), dict.get(Integer.parseInt(oldCode)) + character);

            //old code takes new code
            oldCode = newCode;
        }

        //print dict
        System.out.println("dict after decompression:");
        printDict(dict);

        //return uncompressed message
        return decodedMessage;
    }

    /**
     * Inits a dictionnary (hashmap) of Integer keys associated to String values
     * corresponding to ASCII table (256 values)
     *
     * @return a dict initialzed with ASCII table
     */
    private static HashMap<Integer, String> initDictWithASCII() {
        HashMap<Integer, String> result = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            result.put(i, "" + (char) i);
        }
        return result;
    }

    /**
     * Getter for a key of a hashmap depending on a given value
     *
     * @param map the hashmap to find key in
     * @param value the value of the key in the hashmap
     * @return the first key of the given value if it exists in the given
     * hashmap, null otherwise
     */
    private static Integer getKeyByValue(HashMap<Integer, String> map, String value) {
        for (Entry<Integer, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Prints values above ASCII code (255) of given dictionnary
     *
     * @param dict the dictionnary to be printed
     */
    private static void printDict(HashMap<Integer, String> dict) {
        for (int i = 256; i < dict.size(); i++) {
            System.out.println("{" + String.valueOf(i) + " = " + dict.get(i) + "}");
        }
    }
}
