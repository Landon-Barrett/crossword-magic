package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;
import android.util.Log;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {

    private final int DEFAULT_PUZZLE_ID = 1;
    private Context context;

    private Puzzle puzzle;
    private Integer[] dimensions = {0, 0};
    private Character[][] letters;
    private Integer[][] numbers;
    private String cluesAcross;
    private String cluesDown;

    public CrosswordMagicModel(Context context) {

        //this.context = context;
        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);

    }

    public void getGridDimensionProperty() {
        dimensions[0] = puzzle.getHeight();
        dimensions[1] = puzzle.getWidth();

        firePropertyChange(CrosswordMagicController.GRID_DIMENSION_PROPERTY, null, dimensions);

    }

    public void getGridLettersProperty() {

        letters = puzzle.getLetters();

        firePropertyChange(CrosswordMagicController.GRID_LETTERS_PROPERTY, null, letters);
    }

    public void getGridNumbersProperty() {

        numbers = puzzle.getNumbers();

        firePropertyChange(CrosswordMagicController.GRID_NUMBERS_PROPERTY, null, numbers);
    }

    public void getCluesAcrossProperty() {

        cluesAcross = puzzle.getCluesAcross();

        firePropertyChange(CrosswordMagicController.CLUES_ACROSS_PROPERTY, null, cluesAcross);
    }

    public void getCluesDownProperty() {

        cluesDown = puzzle.getCluesDown();

        firePropertyChange(CrosswordMagicController.CLUES_DOWN_PROPERTY, null, cluesDown);
    }

    public void getTestProperty() {

        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);

    }

}