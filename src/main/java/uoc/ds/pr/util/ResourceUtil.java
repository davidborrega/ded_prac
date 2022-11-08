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
        return ((SportEvents4Club.FLAG_PRIVATE_SECURITY) & (resource)) == resource;
    }

    public static boolean hasPublicSecurity(byte resource) {
        return ((SportEvents4Club.FLAG_PUBLIC_SECURITY) & (resource)) == resource;
    }

    public static boolean hasBasicLifeSupport(byte resource) {
        return ((SportEvents4Club.FLAG_BASIC_LIFE_SUPPORT) & (resource)) == resource;
    }

    public static boolean hasVolunteers(byte resource) {
        return ((SportEvents4Club.FLAG_VOLUNTEERS) & (resource)) == resource;
    }

    public static boolean hasAllOpts(byte resource) {
        return ((SportEvents4Club.FLAG_ALL_OPTS) & (resource)) == resource;
    }
}
