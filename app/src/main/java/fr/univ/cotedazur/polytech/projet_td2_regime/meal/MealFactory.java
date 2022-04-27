package fr.univ.cotedazur.polytech.projet_td2_regime.meal;

public class MealFactory {
    public static final int MEAL_LUNCH = 1;
    public static final int MEAL_STARTER = 2;
    public static final int MEAL_DESSERT = 3;

    static Meal build(int type) throws Throwable {
        switch (type) {
            case MEAL_LUNCH:
                return new MealLunch();
            case MEAL_STARTER:
                return new MealStarter();
            case MEAL_DESSERT:
                return new MealDessert();
            default:
                throw new Throwable("not made");
        }
    }
}
