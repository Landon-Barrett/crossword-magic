package edu.jsu.mcis.cs408.crosswordmagic.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.opencsv.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.jsu.mcis.cs408.crosswordmagic.R;
import edu.jsu.mcis.cs408.crosswordmagic.model.Puzzle;
import edu.jsu.mcis.cs408.crosswordmagic.model.PuzzleListItem;
import edu.jsu.mcis.cs408.crosswordmagic.model.Word;
import edu.jsu.mcis.cs408.crosswordmagic.model.WordDirection;

public class PuzzleDAO {

    private final DAOFactory daoFactory;

    PuzzleDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(HashMap<String, String> params) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, params);
        db.close();
        return result;

    }

    public int create(SQLiteDatabase db, HashMap<String, String> params) {

        /* use this method if there IS already a SQLiteDatabase open */

        String name = daoFactory.getProperty("sql_field_name");
        String description = daoFactory.getProperty("sql_field_description");
        String height = daoFactory.getProperty("sql_field_height");
        String width = daoFactory.getProperty("sql_field_width");

        ContentValues values = new ContentValues();
        values.put(name, params.get(name));
        values.put(description, params.get(description));
        values.put(height, Integer.parseInt(params.get(height)));
        values.put(width, Integer.parseInt(params.get(width)));

        int key = (int)db.insert(daoFactory.getProperty("sql_table_puzzles"), null, values);

        return key;

    }

    public Puzzle find(int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        Puzzle result = find(db, id);
        db.close();
        return result;

    }

    public Puzzle find(SQLiteDatabase db, int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        Puzzle puzzle = null;

        String query = daoFactory.getProperty("sql_get_puzzle");
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            HashMap<String, String> params = new HashMap<>();

            String name = daoFactory.getProperty("sql_field_name");
            String description = daoFactory.getProperty("sql_field_description");
            String height = daoFactory.getProperty("sql_field_height");
            String width = daoFactory.getProperty("sql_field_width");

            params.put(daoFactory.getProperty("sql_field_name"), cursor.getString(cursor.getColumnIndexOrThrow(name)));
            params.put(daoFactory.getProperty("sql_field_description"), cursor.getString(cursor.getColumnIndexOrThrow(description)));
            params.put(daoFactory.getProperty("sql_field_height"), cursor.getString(cursor.getColumnIndexOrThrow(height)));
            params.put(daoFactory.getProperty("sql_field_width"), cursor.getString(cursor.getColumnIndexOrThrow(width)));

            if (!params.isEmpty())
                puzzle = new Puzzle(params);

            /* get list of words (if any) to add to puzzle */

            query = daoFactory.getProperty("sql_get_words");
            cursor = db.rawQuery(query, new String[]{ String.valueOf(id) });

            if (cursor.moveToFirst()) {

                cursor.moveToFirst();

                do {

                    params = new HashMap<>();

                    String puzzleid = daoFactory.getProperty("sql_field_puzzleid");
                    String row = daoFactory.getProperty("sql_field_row");
                    String column = daoFactory.getProperty("sql_field_column");
                    String box = daoFactory.getProperty("sql_field_box");
                    String direction = daoFactory.getProperty("sql_field_direction");
                    String word = daoFactory.getProperty("sql_field_word");
                    String clue = daoFactory.getProperty("sql_field_clue");

                    params.put(daoFactory.getProperty("sql_field_id"), String.valueOf(id));
                    params.put(daoFactory.getProperty("sql_field_puzzleid"), cursor.getString(cursor.getColumnIndexOrThrow(puzzleid)));
                    params.put(daoFactory.getProperty("sql_field_row"), cursor.getString(cursor.getColumnIndexOrThrow(row)));
                    params.put(daoFactory.getProperty("sql_field_column"), cursor.getString(cursor.getColumnIndexOrThrow(column)));
                    params.put(daoFactory.getProperty("sql_field_box"), cursor.getString(cursor.getColumnIndexOrThrow(box)));
                    params.put(daoFactory.getProperty("sql_field_direction"), cursor.getString(cursor.getColumnIndexOrThrow(direction)));
                    params.put(daoFactory.getProperty("sql_field_word"), cursor.getString(cursor.getColumnIndexOrThrow(word)));
                    params.put(daoFactory.getProperty("sql_field_clue"), cursor.getString(cursor.getColumnIndexOrThrow(clue)));

                    if (!params.isEmpty())
                        puzzle.addWordToPuzzle(new Word(params));

                }
                while ( cursor.moveToNext() );

                cursor.close();

            }

            /* get list of already-guessed words (if any) from "guesses" table */

            query = daoFactory.getProperty("sql_get_guesses");
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {

                cursor.moveToFirst();

                do {

                    Integer box = cursor.getInt(4);
                    WordDirection direction = WordDirection.values()[cursor.getInt(5)];

                    if (puzzle != null)
                        puzzle.addWordToGuessed(box + direction.toString());

                }
                while ( cursor.moveToNext() );

                cursor.close();

            }

        }

        return puzzle;

    }

    public ArrayList list() {

        SQLiteDatabase db = daoFactory.getWritableDatabase();

        PuzzleListItem puzzleListItem = null;
        ArrayList<PuzzleListItem> puzzleList = new ArrayList<>();

        String query = daoFactory.getProperty("sql_get_puzzle_list");
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            String name = daoFactory.getProperty("sql_field_name");
            String puzzleId = daoFactory.getProperty("sql_field_id");
            do {

                String puzzleName = cursor.getString(cursor.getColumnIndexOrThrow(name));
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(puzzleId)));

                puzzleListItem = new PuzzleListItem(id, puzzleName);
                puzzleList.add(puzzleListItem);
            }
            while( cursor.moveToNext() );
            cursor.close();
        }
        db.close();

        return puzzleList;
    }

}