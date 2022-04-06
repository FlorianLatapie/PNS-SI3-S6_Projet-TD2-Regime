package fr.univ.cotedazur.polytech.projet_td2_regime.profile;

public enum HealthMonitoring {
    NO_CHANGE("Aucun changement", "Ce régime vous permet de garder votre poids et de rester en forme. Il vous faut une alimentation équilibrée et une activité physique régulière."),
    WEIGHT_GAIN("Gain de poids", "Ce régime vous permet de gagner de l'énergie et de augmenter votre masse corporelle. Il vous faut une alimentation équilibrée et une activité physique régulière."),
    WEIGHT_LOSS("Perte de poids", "Ce régime vous permet de perdre de l'énergie et de diminuer votre masse corporelle. Il vous faut une alimentation équilibrée et une activité physique régulière."),
    FAT_LOSS("Perte de masse corporelle", "Ce régime vous permet de perdre de l'énergie et de diminuer votre masse corporelle. Il vous faut une alimentation équilibrée et une activité physique régulière.");

    private final String name;
    private final String description;

    public static final HealthMonitoring[] values = values();

    HealthMonitoring(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public static HealthMonitoring getHealthMonitoring(String name) {
        for (HealthMonitoring healthMonitoring : values) {
            if (healthMonitoring.getName().equals(name)) {
                return healthMonitoring;
            }
        }
        return null;
    }
}
