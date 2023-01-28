/*
 * This project is for educational purpose
 */
package lzw_compress;

/**
 *
 * @author boris
 */
public class LZW_Compress {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //message to compress
        String msg = "0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 ";
        
        //compress and uncompress message
        String compressed = LZW.compress(msg);
        String uncompressed = LZW.uncompress(compressed);

        //display results
        System.out.println("message     : " + msg);
        System.out.println("compressed  : " + compressed);
        System.out.println("uncompressed: " + uncompressed);
    }

}
