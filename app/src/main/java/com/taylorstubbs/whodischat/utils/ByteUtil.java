package com.taylorstubbs.whodischat.utils;

/**
 * A utility for working with booleans and bytes
 */

public enum ByteUtil {;
    private static final String TAG = "ByteUtil";

    /**
     * Convert a boolean to a byte
     *
     * @param bool  the boolean to convert
     * @return      the byte
     */
    public static Byte booleanToByte(Boolean bool) {
        //if boolean == true, byte == 1
        return (byte) (bool ? 1 : 0);
    }

    /**
     * Convert byte to boolean
     *
     * @param b the byte to convert
     * @return  the boolean
     */
    public static Boolean byteToBoolean(Byte b) {
        //boolean == true if byte != 0
        return b != 0;
    }
}
