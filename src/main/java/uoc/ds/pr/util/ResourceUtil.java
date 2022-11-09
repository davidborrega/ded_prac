package uoc.ds.pr.util;

import uoc.ds.pr.SportEvents4Club;

public class ResourceUtil {

    public static byte getFlag(byte ... flags) {
        byte flag = 0;
        for (int i = 0; i < flags.length; i++) {
            flag += flags[i];
        }
        return flag;
    }

    public static boolean hasPrivateSecurity(byte resource) {
        return hasPermission(resource, SportEvents4Club.FLAG_PRIVATE_SECURITY);
    }

    public static boolean hasPublicSecurity(byte resource) {
        return hasPermission(resource, SportEvents4Club.FLAG_PUBLIC_SECURITY);
    }

    public static boolean hasBasicLifeSupport(byte resource) {
        return hasPermission(resource, SportEvents4Club.FLAG_BASIC_LIFE_SUPPORT);
    }

    public static boolean hasVolunteers(byte resource) {
        return hasPermission(resource, SportEvents4Club.FLAG_VOLUNTEERS);
    }

    public static boolean hasAllOpts(byte resource) {
        return hasPermission(resource, SportEvents4Club.FLAG_ALL_OPTS);
    }

    private static boolean hasPermission(byte resource, byte flag) {
        return (resource & flag) == flag;
    }
}
