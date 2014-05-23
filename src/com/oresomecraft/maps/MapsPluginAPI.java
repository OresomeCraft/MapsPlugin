package com.oresomecraft.maps;

public class MapsPluginAPI {

    /**
     * Resolves a short name to a full map name (i.e. perro -> Casa de Perro)
     *
     * @param shortName The name to resolve.
     * @return The resolved name, returns 'None' if it couldn't resolve.
     */
    public static String resolveShortName(String shortName) {
        String resolve = MapsPlugin.getMaps().get(shortName).getFullName();
        if (resolve == null) return "None";
        return resolve;
    }

    /**
     * Resolves a short name to a map's creator list (i.e. perro -> {"zachoz", "pegabeavercorn", "Dogmode555"}
     *
     * @param shortName The name to resolve.
     * @return The resolved creator list, returns null if it couldn't resolve.
     */
    public static String[] resolveCreators(String shortName) {
        String[] resolve = MapsPlugin.getMaps().get(shortName).getCreators();
        if (resolve == null) return new String[]{"???"};
        return resolve;
    }
}
