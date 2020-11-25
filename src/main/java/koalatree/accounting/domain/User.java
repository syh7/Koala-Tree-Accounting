package koalatree.accounting.domain;

public enum User {

    SJOERD("Sjoerd"),
    LOES("Loes"),
    ALL("Beide");

    private String dutchName;

    public String getDutchName() {
        return dutchName;
    }

    User(String dutchName) {
        this.dutchName = dutchName;
    }

    public static User valueOfDutchName(String value) {
        for (User user : values()) {
            if (user.dutchName.equals(value)) {
                return user;
            }
        }
        throw new IllegalArgumentException("Tried parsing " + value + " to User but failed.");
    }
}
