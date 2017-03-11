package sweden.hack.userinfo.objects.main.base;

/**
 * All cards mot extend MainCard
 */
public abstract class MainCard {

    public final static int TYPE_AGE = 1;
    public static final int TYPE_POPULATION = 2;
    public static final int TYPE_INCOME = 3;
    public static final int TYPE_WEATHER = 4;
    public static final int TYPE_SL_CLOSEST_STATIONS = 5;
    public static final int TYPE_CURRENCY = 6;
    public static final int TYPE_INTERNET = 7;

    public abstract int getViewType();
}
