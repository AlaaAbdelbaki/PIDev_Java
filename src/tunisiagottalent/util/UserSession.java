package tunisiagottalent.util;

import tunisiagottalent.Entity.User;

public final class UserSession {

    private static UserSession instance;

    private User u;

    @Override
    public String toString() {
        return "UserSession{" +
                "u=" + u +
                '}';
    }

    public UserSession(User u) {
        this.u = u;

    }

    public static UserSession getInstance(User u) {
        if(instance == null) {
            instance = new UserSession(u);
        }
        return instance;
    }



    public void cleanUserSession() {
        instance=null;
    }


    }
