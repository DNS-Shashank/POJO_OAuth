package POJO;

import java.util.List;

public class addPlace {

private addPlace_location location;
private int accuracy;
private String name;
private String phone_number;
private String address;
private List<String> types;
private String website;
private String language;

public addPlace_location getLocation() {
    return location;
}

public void setLocation(addPlace_location location) {
    this.location = location;
}

public int getAccuracy() {
    return accuracy;
}

public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getPhone_number() {
    return phone_number;
}

public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public List<String> getTypes() {
    return types;
}

public void setTypes(List<String> types) {
    this.types = types;
}

public String getWebsite() {
    return website;
}

public void setWebsite(String website) {
    this.website = website;
}

public String getLanguage() {
    return language;
}

public void setLanguage(String language) {
    this.language = language;
}

public void setTypes(String string, String string2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setTypes'");
}
}
