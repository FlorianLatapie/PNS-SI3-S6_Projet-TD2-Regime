package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

public enum Diet {
    VEGETARIAN("Vegetarien", "Vegetarian"),
    GLUTEN_FREE("Sans gluten", "Gluten-free"),
    PROTEIN("Sans proteine", "Protein-free"),
    NONE("Aucun", "None");

    private final String name;
    private final String englishName;
    public static final Diet[] values = values();

    Diet(String name, String englishName) {
        this.name = name;
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public static String[] getNames() {
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }

    public static String[] getEnglishNames() {
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getEnglishName();
        }
        return names;
    }
}
