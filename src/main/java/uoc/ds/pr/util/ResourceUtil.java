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
        return ((SportEvents4Club.FLAG_PRIVATE_SECURITY) & (resource)) == SportEvents4Club.FLAG_PRIVATE_SECURITY;
    }

    public static boolean hasPublicSecurity(byte resource) {
        return ((SportEvents4Club.FLAG_PUBLIC_SECURITY) & (resource)) == SportEvents4Club.FLAG_PUBLIC_SECURITY;
    }

    public static boolean hasBasicLifeSupport(byte resource) {
        return ((SportEvents4Club.FLAG_BASIC_LIFE_SUPPORT) & (resource)) == SportEvents4Club.FLAG_BASIC_LIFE_SUPPORT;
    }

    public static boolean hasVolunteers(byte resource) {
        return ((SportEvents4Club.FLAG_VOLUNTEERS) & (resource)) == SportEvents4Club.FLAG_VOLUNTEERS;
    }

    public static boolean hasAllOpts(byte resource) {
        return ((SportEvents4Club.FLAG_ALL_OPTS) & (resource)) == SportEvents4Club.FLAG_ALL_OPTS;
    }
}
