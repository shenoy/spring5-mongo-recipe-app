package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by jt on 6/13/17.
 */

public class UnitOfMeasure {

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
