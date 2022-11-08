package uoc.ds.pr.util;

import uoc.ds.pr.SportEvents4Club;

public class ResourceUtil {

    public static byte getFlag(byte param1) {
        return param1;
    }

    public static byte getFlag(byte param1, byte param2) {
        return (byte) (param1 + param2);
    }

    public static byte getFlag(byte param1, byte param2, byte param3) {
        return (byte) (param1 + param2 + param3);
    }

    public static byte getFlag(byte param1, byte param2, byte param3, byte param4) {
        return (byte) (param1 + param2 + param3 + param4);
    }

    public static boolean hasPrivateSecurity(byte resource) {
        String bString = Integer.toBinaryString(resource);
        return bString.charAt(bString.length()) == 1;
        //return resource >= SportEvents4Club.FLAG_PRIVATE_SECURITY;
    }

    public static boolean hasPublicSecurity(byte resource) {
        String bString = Integer.toBinaryString(resource);
        System.out.println(bString);
        System.out.println(bString.trim().charAt(bString.length()-1));
        System.out.println(bString.trim().charAt(bString.length()-1) == 1);
        return bString.charAt(bString.length()-1) == 1;

        //        return resource >= SportEvents4Club.FLAG_PUBLIC_SECURITY;
    }

    public static boolean hasBasicLifeSupport(byte resource) {
        String bString = Integer.toBinaryString(resource);
        return bString.charAt(bString.length()-2) == 1;

        //return resource >= SportEvents4Club.FLAG_BASIC_LIFE_SUPPORT;
    }

    public static boolean hasVolunteers(byte resource) {
        String bString = Integer.toBinaryString(resource);
        return bString.charAt(bString.length()-3) == 1;
        //return resource >= SportEvents4Club.FLAG_VOLUNTEERS;
    }

    public static boolean hasAllOpts(byte resource) {
        return resource == SportEvents4Club.FLAG_ALL_OPTS;
    }
}
