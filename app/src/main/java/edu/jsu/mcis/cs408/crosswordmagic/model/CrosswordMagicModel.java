package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import edu.jsu.mcis.cs408.crosswordmagic.R;
import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {

    private final int DEFAULT_PUZZLE_ID = 1;

    private Puzzle puzzle;
    private Integer[] dimensions = {0, 0};
    private Character[][] letters;
    private Integer[][] numbers;
    private String cluesAcross;
    private String cluesDown;
    private Integer boxNumber;
    private String guessedWord;
    private String incorrectGuess;
    private Context context;

    public CrosswordMagicModel(Context context) {


        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);
        this.incorrectGuess = context.getResources().getString(R.string.word_guessed_incorrect);
        this.context = context;

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

    public void setGuessedWordProperty(String guessedWord) {
        guessedWord = guessedWord.toUpperCase();

        this.guessedWord = guessedWord;
    }

    public void getGuessedWordProperty() {

        WordDirection result = puzzle.checkGuess(boxNumber, guessedWord);

        if (result == null) {

            String text = this.incorrectGuess;
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }

        firePropertyChange(CrosswordMagicController.GUESSED_WORD_PROPERTY, null, result);





    }

    public void setBoxNumberProperty (Integer boxNum) {
        this.boxNumber = boxNum;
    }

    public void getTestProperty() {

        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);

    }

}