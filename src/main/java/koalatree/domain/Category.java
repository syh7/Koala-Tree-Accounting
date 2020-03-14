package koalatree.domain;

public enum Category{

    GROCERIES("Boodschappen"),
    PRESENTS("Cadeau"),
    DATES("Date"),
    OTHER("Overig");

    private String dutchName;

    public String getDutchName(){
        return dutchName;
    }

    Category(String dutchName){
        this.dutchName = dutchName;
    }
}
