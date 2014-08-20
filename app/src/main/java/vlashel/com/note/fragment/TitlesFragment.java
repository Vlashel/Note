package vlashel.com.note.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import vlashel.com.note.model.Note;
import vlashel.com.note.dao.NoteHelper;
import vlashel.com.note.R;
import vlashel.com.note.activity.DescriptionActivity;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 14.08.2014
 */
public class TitlesFragment extends ListFragment {

    private ArrayList<Note> notes;

    private ArrayAdapter<Note> adapter;

    private boolean mDuelPane;

    private int mCurCheckPosition = 0;
    private int noteId;

    public int getNoteId() {
        return noteId;
    }

    public ArrayAdapter<Note> getAdapter() {
        return adapter;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void onResume() {
        super.onResume();


        initAdapter();

       /* NoteHelper noteHelper = new NoteHelper(getActivity());

        notes = noteHelper.getAllNotes();

        setNotes(notes);

        adapter.notifyDataSetChanged();*/

    }

    public void initAdapter() {
        NoteHelper noteHelper = new NoteHelper(getActivity());

        notes = noteHelper.getAllNotes();

        adapter = new ArrayAdapter<Note>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1, notes
        );

        setListAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        View detailsFrame = getActivity().findViewById(R.id.description);

        mDuelPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            noteId = savedInstanceState.getInt("noteId", 1);
        }

        if (mDuelPane) {

            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            showDetails(mCurCheckPosition, noteId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
        outState.putInt("noteId", noteId);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Note note = adapter.getItem(position);

        this.noteId = note.getId();
        showDetails(position, note.getId());
    }

    void showDetails(int index, Integer noteId) {

        mCurCheckPosition = index;

        if (mDuelPane) {

            getListView().setItemChecked(index, true);

            DescriptionFragment description = (DescriptionFragment)
                    getFragmentManager().findFragmentById(R.id.description);

            if (description == null || description.getShownIndex() != index) {

                description = DescriptionFragment.newInstance(index, noteId);

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                // Replace any other Fragment with  new Description Fragment with the right data
                ft.replace(R.id.description, description);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            Intent intent = new Intent();

            intent.setClass(getActivity(), DescriptionActivity.class);

            intent.putExtra("index", index);
            intent.putExtra("noteId", noteId);

            startActivity(intent);
        }
    }
}
