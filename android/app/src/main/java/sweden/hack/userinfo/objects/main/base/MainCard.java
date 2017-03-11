package sweden.hack.userinfo.objects.main.base;

/**
 * All cards mot extend MainCard
 */
public abstract class MainCard {
    public final static int TYPE_GENDER = 0;
    public final static int TYPE_AGE = 1;
    public static final int TYPE_POPULATION = 2;
    public static final int TYPE_INCOME = 3;
    public static final int TYPE_WEATHER = 4;

    public abstract int getViewType();
}
