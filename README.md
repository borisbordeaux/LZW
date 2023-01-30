# LZW
Data compression using LZW algorithm for educational purpose.

This is a Netbeans project in Java.

It currently compresses strings using an initial dictionnary containing ASCII values (code from 0 to 255). For instance, the value of 65 is A and the code of a is 97.  
It outputs the result that should be written in a file, it does not actually compress data.

There is also a decompression algorithm to decompress previously compressed data.

## ALgorithms

### Compression Algorithm

Input: MESSAGE (each character will be read from left to right, only one time)  
Output: ENCODED_MESSAGE (possible separation of each code with a space for more lisibility)  

Init DICTIONNARY with ASCII values  
CHAIN = read one character of MESSAGE  
while MESSAGE has characters left  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHARACTER = read one character of MESSAGE  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if CHAIN + CHARACTER is in DICTIONNARY  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHAIN = CHAIN + CHARACTER  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENCODED_MESSAGE = ENCODED_MESSAGE + code of CHAIN  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;save CHAIN + CHARACTER to DICTIONNARY  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHAIN = CHARACTER  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENCODED_MESSAGE = ENCODED_MESSAGE + code of CHAIN  

### Decompression algorithm

Input: ENCODED_MESSAGE (each code will be read from left to right, only one time)  
Output: DECODED_MESSAGE  

Init DICTIONNARY with ASCII values  
OLD_CODE = read one code of ENCODED_MESSAGE  
DECODED_MESSAGE = value of OLD_CODE in DICTIONNARY  
CHARACTER = value of OLD_CODE in DICTIONNARY  
while ENCODED_MESSAGE has codes left  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NEW_CODE = read one code of ENCODED_MESSAGE  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if DICTIONNARY contains NEW_CODE  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHAIN = value of NEW_CODE  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHAIN = value of OLD_CODE + CHARACTER  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DECODED_MESSAGE = DECODED_MESSAGE + CHAIN  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CHARACTER = first character of CHAIN  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;save value of OLD_CODE + CHARACTER to DICTIONNARY  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OLD_CODE = NEW_CODE