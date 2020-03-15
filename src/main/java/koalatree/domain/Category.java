package koalatree.domain;

public enum Category {

    GROCERIES("Boodschappen"),
    PRESENTS("Cadeau"),
    DATES("Date"),
    OTHER("Overig");

    private String dutchName;

    public String getDutchName() {
        return dutchName;
    }

    Category(String dutchName) {
        this.dutchName = dutchName;
    }

    public static Category valueOfDutchName(String value) {
        for (Category cat : values())
            if (cat.dutchName.equals(value)) {
                return cat;
            }
        throw new IllegalArgumentException("Tried parsing " + value + " to Category but failed.");
    }
}
