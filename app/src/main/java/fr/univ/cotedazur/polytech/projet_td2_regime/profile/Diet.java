package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

public enum Diet {
    VEGETARIAN("Vegetarien"),
    GLUTEN_FREE("Sans gluten"),
    PROTEIN("Sans proteine");
    private final String name;
    public static final Diet[] values = values();

    Diet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getNames() {
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }
}
