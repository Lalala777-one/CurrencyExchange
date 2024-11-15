package model;

public class Currency {

    private String name;
    private String code;

    Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
