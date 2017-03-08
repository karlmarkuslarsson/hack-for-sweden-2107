package sweden.hack.userinfo.objects.main.base;

/**
 * All cards mot extend MainCard
 */
public abstract class MainCard {
    public final static int TYPE_GENDER = 0;
    public final static int TYPE_AGE = 1;

    public abstract int getViewType();
}
