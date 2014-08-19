package vlashel.com.note.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import vlashel.com.note.fragment.DescriptionFragment;
import vlashel.com.note.fragment.TitlesFragment;
import vlashel.com.note.model.Note;
import vlashel.com.note.dao.NoteHelper;
import vlashel.com.note.R;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 18.08.2014
 */
public class NewNoteActivity extends Activity {

    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_exit:
                finish();
                return true;
            case R.id.action_save:
                saveNote();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {

        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
       // DateTime date = new DateTime();

        Note note = new Note(title, description);

        NoteHelper noteHelper = new NoteHelper(this);
        noteHelper.insertNote(note);

        finish();
    }
}
