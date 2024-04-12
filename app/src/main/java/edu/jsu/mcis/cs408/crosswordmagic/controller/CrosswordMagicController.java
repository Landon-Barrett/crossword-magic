package edu.jsu.mcis.cs408.crosswordmagic.controller;

import edu.jsu.mcis.cs408.crosswordmagic.model.CrosswordMagicModel;

public class CrosswordMagicController extends AbstractController {

    public static final String TEST_PROPERTY = "TestProperty";
    public static final String GRID_DIMENSION_PROPERTY = "GridDimensionProperty";
    public static final String GRID_LETTERS_PROPERTY = "GridLettersProperty";
    public static final String GRID_NUMBERS_PROPERTY = "GridNumbersProperty";
    public static final String CLUES_ACROSS_PROPERTY = "CluesAcrossProperty";
    public static final String CLUES_DOWN_PROPERTY = "CluesDownProperty";
    public static final String GUESSED_WORD_PROPERTY = "GuessedWordProperty";
    public static final String BOX_NUMBER_PROPERTY = "BoxNumberProperty";
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

    public void getCluesAcross() { getModelProperty(CLUES_ACROSS_PROPERTY); }

    public void getCluesDown() { getModelProperty(CLUES_DOWN_PROPERTY); }

    public void setGuessedWord(String word) { setModelProperty(GUESSED_WORD_PROPERTY, word); }

    public void getGuessedWord() { getModelProperty(GUESSED_WORD_PROPERTY); }

    public void setBoxNumber(Integer num) { setModelProperty(BOX_NUMBER_PROPERTY, num); }


}