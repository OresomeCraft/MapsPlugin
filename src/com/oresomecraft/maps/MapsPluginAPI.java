package com.oresomecraft.maps;

import java.util.HashMap;

public class MapsPluginAPI {

    private static HashMap<String, String> nameToFull = new HashMap<String, String>();
    private static HashMap<String, String[]> nameToCreators = new HashMap<String, String[]>();

    /**
     * Registers a Map's full name and short name into a HashMap.
     *
     * @param shortName The short name to add.
     * @param fullName  The full name to be identified at a later time.
     */
    public static void putFullName(String shortName, String fullName) {
        if (!nameToFull.containsValue(fullName)) return;
        nameToFull.put(shortName, fullName);
    }

    /**
     * Registers a Map's creator list into a HashMap.
     *
     * @param shortName The short name to add.
     * @param creators  The creator list to be identified at a later time.
     */
    public static void putCreators(String shortName, String[] creators) {
        if (!nameToCreators.containsValue(creators)) return;
        nameToCreators.put(shortName, creators);
    }

    /**
     * Resolves a short name to a full map name (i.e. perro -> Casa de Perro)
     *
     * @param shortName The name to resolve.
     * @return The resolved name, returns 'None' if it couldn't resolve.
     */
    public static String resolveShortName(String shortName) {
        String resolve = nameToFull.get(shortName);
        if (!nameToFull.containsKey(shortName)) return "None";
        return resolve;
    }

    /**
     * Resolves a short name to a map's creator list (i.e. perro -> {"zachoz", "pegabeavercorn", "Dogmode555"}
     *
     * @param shortName The name to resolve.
     * @return The resolved creator list, returns null if it couldn't resolve.
     */
    public static String[] resolveCreators(String shortName) {
        String[] resolve = nameToCreators.get(shortName);
        if (!nameToCreators.containsKey(shortName)) return null;
        return resolve;
    }
}
