package vlashel.com.note.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import vlashel.com.note.dao.NoteHelper;
import vlashel.com.note.fragment.DescriptionFragment;
import vlashel.com.note.R;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 14.08.2014
 */
public class DescriptionActivity extends Activity{

    private int noteId;
    private NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteHelper = new NoteHelper(this);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {

            finish();
            return;
        }

        if (savedInstanceState == null) {

            DescriptionFragment descriptionFragment = new DescriptionFragment();

            noteId = getIntent().getExtras().getInt("noteId");

            descriptionFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content, descriptionFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.description, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void deleteNote() {
        noteHelper.deleteNote(noteId);
        finish();
    }


}
