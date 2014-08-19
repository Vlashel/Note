package vlashel.com.note.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vlashel.com.note.model.Note;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 18.08.2014
 */
public class NoteHelper extends SQLiteOpenHelper {

    public NoteHelper(Context context) {
        super(context, "notedb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE notes" +
                " ( noteId INTEGER PRIMARY KEY, title TEXT, " +
                "description TEXT )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists";

        db.execSQL(query);

        onCreate(db);
    }

    public void insertNote(Note note) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
    //    values.put("date", note.getDateTime().toString("YYYY-MM-DD HH:MM:SS"));

        database.insert("notes", null, values);

        database.close();
    }

    public int updateNote(Note note) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        //values.put("date", note.getDateTime().toString("YYYY-MM-DD HH:MM:SS"));

        return database.update("notes", values, "noteId" + " = ?", new String[] { note.getId().toString() });
    }


    public void deleteNote(Integer id) {

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM  notes where noteId='" + id + "'";

        database.execSQL(deleteQuery);

        database.close();
    }

    public ArrayList<Note> getAllNotes() {

        ArrayList<Note> noteArrayList = new ArrayList<Note>();

        String selectQuery = "SELECT * FROM notes";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                String description = cursor.getString(2);
              //  Date date = null;
              /*  try {
                    date = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(cursor.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

               // DateTime dateTime = new DateTime(date);

                Note note = new Note(id, title, description);

                noteArrayList.add(note);
            } while (cursor.moveToNext());
        }

        database.close();

        return noteArrayList;
    }

    public Note getNote(Integer id) {

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM notes where noteId='" + id + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        Note note = null;

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                //Date date = null;
               /* try {
                    date = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(cursor.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

              //  DateTime dateTime = new DateTime(date);

                note = new Note(id, title, description);

            } while (cursor.moveToNext());
        }

        database.close();
        return note;
    }
}
