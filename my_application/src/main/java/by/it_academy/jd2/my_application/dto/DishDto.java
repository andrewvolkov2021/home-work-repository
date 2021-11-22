package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Component;

import java.util.List;

public class DishDto {

    private String name;
    private List<Component> components;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
