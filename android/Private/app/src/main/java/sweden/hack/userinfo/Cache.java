package sweden.hack.userinfo;

public class Cache {

    private static Cache sSharedInstance;

    private Cache() {
    }

    public static Cache sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new Cache();
        }
        return sSharedInstance;
    }
}
