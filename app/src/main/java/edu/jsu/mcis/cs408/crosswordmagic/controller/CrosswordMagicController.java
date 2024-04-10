package edu.jsu.mcis.cs408.crosswordmagic.controller;

import edu.jsu.mcis.cs408.crosswordmagic.model.CrosswordMagicModel;

public class CrosswordMagicController extends AbstractController {

    public static final String TEST_PROPERTY = "TestProperty";
    public static final String GRID_DIMENSION_PROPERTY = "GridDimensionProperty";
    public static final String GRID_LETTERS_PROPERTY = "GridLettersProperty";
    public static final String GRID_NUMBERS_PROPERTY = "GridNumbersProperty";
    private CrosswordMagicModel model;

    public void getTestProperty(String value) {
        getModelProperty(TEST_PROPERTY);
    }

    public void getGridDimensions() {
        getModelProperty(GRID_DIMENSION_PROPERTY);
    }

    public void getGridLetters() {
        getModelProperty(GRID_LETTERS_PROPERTY);
    }

    public void getGridNumbers() {
        getModelProperty(GRID_NUMBERS_PROPERTY);
    }

}