package by.KuvonchbekN.model;

import java.util.UUID;

public class Category extends BaseModel{
    private UUID parentId;
    private boolean isLast;

    public Category(String name, UUID parentId) {
        super(name);
        if(parentId != null)
            this.parentId = parentId;
        this.isLast = true;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
