package org.dalpra.acme.jsf.task.bean;


import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dalpra.acme.jsf.task.controller.TaskController;
import org.dalpra.acme.jsf.task.entity.Task;

@Named
@ViewScoped
public class TaskBean implements Serializable {

    private static final long serialVersionUID = 1431687449318411750L;

    private List<Task> allTasks;

    @Inject
    private TaskController controller;

    private String title;

    private String id;

    @PostConstruct
    private void postConstruct() {
        refresh();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void delete() {
        try {
            controller.delete(Long.valueOf(id));
            refresh();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task " + id + " deleted"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error deleting the Task by Id. " + e.getLocalizedMessage()));
        }
    }

    public void add() {
        try {
            controller.add(title);
            refresh();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task with title " + title + " created"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error adding a new Task. " + e.getLocalizedMessage()));
        }
    }

    public void update() {
        try {
            controller.update(Long.valueOf(id), title);
            refresh();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task " + id + " updated"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error updating the Task by Id. " + e.getLocalizedMessage()));
        }
    }

    private void refresh() {
        this.allTasks = controller.loadAll();
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }
}
