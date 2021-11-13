package by.KuvonchbekN.model;

import java.util.UUID;

public abstract class BaseModel {
    protected final UUID id;
    protected boolean isActive;
    protected String name;


    {
        this.id = UUID.randomUUID();
        this.isActive = true;
    }

    public BaseModel(String name) {
        this.name = name;

    }

    public BaseModel() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
