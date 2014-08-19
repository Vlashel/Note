package vlashel.com.note.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vlashel.com.note.R;
import vlashel.com.note.model.Note;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 18.08.2014
 */
public class MyArrayAdapter extends ArrayAdapter<Note> {

    private int resource;

    public MyArrayAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;

        Note note = getItem(position);

        String title = note.getTitle();
        //String date = note.getDateTime().toString("dd/MM/yy");

        if (convertView == null) {
            view = new LinearLayout(getContext());
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(resource, view, true);
        } else {
            view = (LinearLayout) convertView;
        }

        TextView dateText = (TextView) view.findViewById(R.id.date);
        TextView rowText = (TextView) view.findViewById(R.id.row);

       // dateText.setText(date);
        rowText.setText(title);

        return view;
    }
}
