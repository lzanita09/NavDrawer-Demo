package com.reindeercrafts.navdrawerdemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.reindeercrafts.navdrawerdemo.R;

/**
 * Created by zhelu on 3/5/14.
 */
public class DrawerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String[] VIEW_INDICES =
            new String[]{"Top level view 1", "Top level view 2", "Top level view 3", "Top level view 4"};

    private ListView mListView;


    private OnDrawerFragmentItemClickedListener onDrawerFragmentItemClickedListener;


    public void setOnDrawerFragmentItemClickedListener(
            OnDrawerFragmentItemClickedListener onDrawerFragmentItemClickedListener) {
        this.onDrawerFragmentItemClickedListener = onDrawerFragmentItemClickedListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate parent view for this fragment.
        View root = inflater.inflate(R.layout.drawer_fragment_layout, container, false);

        mListView = (ListView) root.findViewById(R.id.list_view);

        initListView();

        return root;
    }

    private void initListView() {
        mListView.setAdapter(
                new ArrayAdapter<String>(getActivity(), R.layout.drawer_item_layout, VIEW_INDICES));

        mListView.setOnItemClickListener(this);
    }


    /**
     * Called when the ListView item is clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Here we call the listener's onDrawerItemClicked() function
        // to pass the click action to the activity.
        onDrawerFragmentItemClickedListener.onDrawerItemClicked(position);
    }



    /**
     * Simple interface for communication between navigation drawer and
     * the main fragment.
     */
    public interface OnDrawerFragmentItemClickedListener {

        void onDrawerItemClicked(int position);
    }
}
