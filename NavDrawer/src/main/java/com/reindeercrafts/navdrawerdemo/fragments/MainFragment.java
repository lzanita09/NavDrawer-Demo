package com.reindeercrafts.navdrawerdemo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reindeercrafts.navdrawerdemo.R;

/**
 * The content fragment of the app, used to demostrate the
 * transition to different views using navigation drawer
 *
 * Created by zhelu on 3/5/14.
 */
public class MainFragment extends Fragment {

    // Bundle key.
    public static final String VIEW_INDEX_ARG = "ViewIndex";


    // The only component in the fragment, only for indicating
    // the views.
    private TextView mTextView;


    private int mViewIndex;

    /**
     * Set arguments for this fragment instance.
     *
     * @param args  Bundle object containing arguments (key-value pair).
     */
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        // Retrieves view index from given Bundle objects.
        mViewIndex = args.getInt(VIEW_INDEX_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.main_fragment_layout, container, false);

        mTextView = (TextView) root.findViewById(R.id.textView);

        mTextView.setText(getString(R.string.top_level_view) + " #" + mViewIndex);

        return root;
    }
}
