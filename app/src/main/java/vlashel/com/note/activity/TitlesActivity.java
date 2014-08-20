package vlashel.com.note.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLOutput;

import vlashel.com.note.dao.NoteHelper;
import vlashel.com.note.R;
import vlashel.com.note.fragment.TitlesFragment;


public class TitlesActivity extends Activity  {

    private NoteHelper noteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        noteHelper = new NoteHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.titles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_exit:
                finish();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_delete:
                deleteNote();
                return true;
            case R.id.action_new:
                openNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openNewNote() {
        Intent intent = new Intent();
        intent.setClass(this, NewNoteActivity.class);
        startActivity(intent);
    }

    private void deleteNote() {
        TitlesFragment titlesFragment = (TitlesFragment) getFragmentManager().findFragmentById(R.id.titles);
        int id =  titlesFragment.getNoteId();
        noteHelper.deleteNote(id);
        titlesFragment.initAdapter();
    }

}
