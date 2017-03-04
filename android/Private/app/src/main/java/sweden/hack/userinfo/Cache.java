package sweden.hack.userinfo;

public class Cache {

    private static Cache sSharedInstance;
    String userFirstName;

    private Cache() {
    }

    public static Cache sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new Cache();
        }
        return sSharedInstance;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }
}
