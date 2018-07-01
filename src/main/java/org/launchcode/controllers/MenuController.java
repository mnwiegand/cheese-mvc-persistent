package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("cheeses", cheeseDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add (Model model){
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                           @Valid @ModelAttribute Menu menu, Errors errors){
    if (errors.hasErrors()){
        model.addAttribute("title", "Add menu");
        return "menu/add";
    }

    menuDao.save(menu);
    // Hibernate generates the menu id

    return "redirect:view/" + menu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());

        return "menu/view";
    }
    
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    //why did I have public Object addItem()?
    public String addItem(Model model, @PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm aForm = new AddMenuItemForm(menu, cheeses);

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", aForm);
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @Valid @ModelAttribute AddMenuItemForm aForm, Errors errors, Integer menuId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Adding an Item Again");
            model.addAttribute("form", aForm);
            return "menu/add-item";
        }

        Cheese aCheese = cheeseDao.findOne(aForm.getCheeseId());
        Menu aMenu = menuDao.findOne(aForm.getMenuId());
        aMenu.addItem(aCheese);
        menuDao.save(aMenu);

        return "redirect:/menu/view/" + aMenu.getId();

    }
}
