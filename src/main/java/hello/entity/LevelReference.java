package hello.entity;

import lombok.Data;

@Data
public class LevelReference {


    private String level;

    private Double minVal;

    private Double maxVal;

    public LevelReference(String level, Double minVal, Double maxVal) {
        this.level = level;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
}
