package org.launchcode.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //this holds the menu items
    //Hibernate populates this automatically
    //based upon relationships in the controller
    @ManyToMany
    private List<Cheese> cheeses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    //no Id setter, because that is a generated value

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void addItem(Cheese item){
        this.cheeses.add(item);
    }

    public Menu(){}

    public Menu(String name) {
        this.name = name;
    }
}
