package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

//not persistent
public class AddMenuItemForm {

    private Menu menu;

    // to contain the options offered to the user
    private Iterable<Cheese> cheeses;

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public AddMenuItemForm(){}

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses){
        this.menu = menu;
        this.cheeses = cheeses;
    }
}
