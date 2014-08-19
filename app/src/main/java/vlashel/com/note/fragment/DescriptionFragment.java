package vlashel.com.note.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.SQLOutput;

import vlashel.com.note.model.Note;
import vlashel.com.note.dao.NoteHelper;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 14.08.2014
 */
public class DescriptionFragment extends Fragment {

    public static DescriptionFragment newInstance(int index, Integer noteId) {
        DescriptionFragment f = new DescriptionFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putInt("noteId", noteId);

        f.setArguments(args);
        return f;
    }

    public int getNoteId() {
        return getArguments().getInt("noteId", 1);
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView description = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        description.setPadding(padding, padding, padding, padding);

        description.setGravity(Gravity.TOP);

        Integer id = getNoteId();


        NoteHelper noteHelper = new NoteHelper(getActivity());



        Note note = noteHelper.getNote(id);

        if (note != null) {
            description.setText(note.getDescription());
        } else {
            description.setText("Create a note");
        }

        return description;
    }
}
