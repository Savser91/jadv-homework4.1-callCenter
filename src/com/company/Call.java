package com.company;

public class Call {
    private String name;
    public Call(String name) {
        this.name = name;
    }

    public String getName() {
        String output = "";
        output += "Звонок №" + name;
        return output;
    }
}
