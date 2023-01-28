/*
 * This project is for educational purpose
 */
package lzw_compress;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * LZW class that offers methods to compress and uncompress a message with LZW
 * algorithm
 *
 * @author boris
 */
public class LZW {

    /**
     * Compresses a message using the LZW compression method
     *
     * @param msg the message to compress
     * @return the compressed message
     */
    public static String compress(String msg) {
        //init dict with ASCII values
        HashMap<Integer, String> dict = initDictWithASCII();
        String result = "";

        //chain takes first char
        String chain = "" + msg.charAt(0);
        int index = 1;

        //while not end
        while (index < msg.length()) {

            //character takes next char of message
            char character = msg.charAt(index);
            index++;

            //if chain + character is in dict
            if (dict.containsValue(chain + character)) {
                //concatenate chain + character
                chain += character;
            } else {
                //add code of chain to output
                //a space is here to facilitate
                //the reading of the result
                result += getKeyByValue(dict, chain) + " ";

                //save chain + caracter to dict
                dict.put(dict.size(), chain + character);

                //affect character to chain
                chain = "" + character;
            }
        }
        //add code of chain to output
        result += getKeyByValue(dict, chain);

        //print dict (for non ascii values)
        System.out.println("dict after compression :\n");
        for (int i = 256; i < dict.size(); i++) {
            System.out.println(String.valueOf(i) + " = " + dict.get(i).replace(" ", "_"));
        }
        System.out.println("\n");

        //return compressed string
        return result;
    }

    /**
     * Uncompresses a message previously compressed with LZW method
     *
     * @param msg the message to uncompress
     * @return the uncompressed message
     */
    public static String uncompress(String msg) {
        //init dict with ASCII values
        HashMap<Integer, String> dict = initDictWithASCII();

        //separate message to get each codes
        String[] messageCodes = msg.split(" ");

        //old code takes the first code
        String old_code = messageCodes[0];
        int index = 1;

        //write corresponding character of old code
        String result = dict.get(Integer.parseInt(old_code));

        //character takes corresponding character of old code
        String character = dict.get(Integer.parseInt(old_code));

        //while not the end of the message
        while (index < messageCodes.length) {
            //new code takes next code in message
            String new_code = messageCodes[index];
            index++;

            //chain takes a value depending on if
            //the dict contains new code
            String chain;
            if (!dict.containsKey(Integer.parseInt(new_code))) {
                //if it doesn't, chain takes corresponding character
                //of old code concatenated to character
                chain = dict.get(Integer.parseInt(old_code)) + character;
            } else {
                //if is does, chain takes corresponding
                //character of new code
                chain = dict.get(Integer.parseInt(new_code));
            }

            //write chain to the output
            result += chain;

            //caractere = premier caractere de chaine
            //character takes first char of chain
            character = "" + chain.charAt(0);

            //add corresponding character of old code
            //concatenated to character to dict
            dict.put(dict.size(), dict.get(Integer.parseInt(old_code)) + character);

            //old code takes new code
            old_code = new_code;
        }

        //print dict
        System.out.println("dict after uncompression :\n");
        for (int i = 256; i < dict.size(); i++) {
            System.out.println(String.valueOf(i) + " = " + dict.get(i).replace(" ", "_"));
        }
        System.out.println("\n");

        //return uncompressed message
        return result;
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
     * @param <T> the type of the keys
     * @param <E> the type of the values
     * @param map the hashmap to find key in
     * @param value the value of the key in the hashmap
     * @return the first key of the given value if it exists in the given
     * hashmap, null otherwise
     */
    private static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
