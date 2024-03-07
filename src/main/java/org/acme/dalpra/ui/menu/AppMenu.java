package org.acme.dalpra.ui.menu;

import com.google.common.collect.Lists;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named
@ApplicationScoped
public class AppMenu {
    List<MenuCategory> menuCategories;
    List<MenuItem> menuItems;

    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();
        menuItems = new ArrayList<>();

        List<MenuItem> generalMenuItems = new ArrayList<>();
        generalMenuItems.add(new MenuItem("Get Started", "/getstarted"));
        generalMenuItems.add(new MenuItem("Persone", "/listapersone"));
        generalMenuItems.add(new MenuItem("Setup", "/setup"));
        generalMenuItems.add(new MenuItem("Setup2", Lists.newArrayList(
                new MenuItem("Get Started 2", "/getstarted"),
                new MenuItem("Get Started 3", "/getstarted")
        )));
        menuCategories.add(new MenuCategory("General", generalMenuItems));

        for (MenuCategory category: menuCategories) {
            for (MenuItem menuItem: category.getMenuItems()) {
                menuItem.setParentLabel(category.getLabel());
                if (menuItem.getUrl() != null) {
                    menuItems.add(menuItem);
                }
                if (menuItem.getMenuItems() != null) {
                    for (MenuItem item: menuItem.getMenuItems()) {
                        item.setParentLabel(menuItem.getLabel());
                        if (item.getUrl() != null) {
                            menuItems.add(item);
                        }
                    }
                }
            }
        }
    }

    public List<MenuItem> completeMenuItem(String query) {
        String queryLowerCase = query.toLowerCase();
        List<MenuItem> filteredItems = new ArrayList<>();
        for (MenuItem item: menuItems) {
            if (item.getUrl() != null && (item.getLabel().toLowerCase().contains(queryLowerCase) || item.getParentLabel().toLowerCase().contains(queryLowerCase))) {
                filteredItems.add(item);
            }
            if (item.getBadge() != null) {
                if (item.getBadge().toLowerCase().contains(queryLowerCase)){
                    filteredItems.add(item);
                }
            }
        }
        filteredItems.sort(Comparator.comparing(MenuItem::getParentLabel));
        return filteredItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuCategory> getMenuCategories() {
        return menuCategories;
    }
}