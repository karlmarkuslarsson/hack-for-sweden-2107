package sweden.hack.userinfo;

public class Cache {

    private static Cache sSharedInstance;
    private String mUserFirstName;
    private String mUserLastName;

    private Cache() {
    }

    public static Cache sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new Cache();
        }
        return sSharedInstance;
    }

    public void setUserFirstName(String userFirstName) {
        mUserFirstName = userFirstName;
    }

    public String getUserFirstName() {
        return mUserFirstName;
    }

    public void setUserLastName(String userLastName) {
        mUserLastName = userLastName;
    }

    public String getUserLastName() {
        return mUserLastName;
    }
}
