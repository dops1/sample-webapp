package com.company.foo;


public class ConfigFile {

    private String name;
    private String contents;

    public ConfigFile(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString(){
        return name + ";";
    }
}
