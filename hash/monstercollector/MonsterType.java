/**
 * MonsterType class of constants (enum) that holds the different types of monsters for the
 * Monster Collector game
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */
public enum MonsterType {
    GRASS("Grass"),
    WOOD("Wood"),
    PLASTIC("Plastic"),
    WATER("Water"),
    FIGHTING("Fighting"),
    STEEL("Steel"),
    FAIRY("Fairy");
    
    private String readable;

    MonsterType(String readable){
        this.readable=readable;
    }

    public String getReadable(){
        return readable;
    }
}