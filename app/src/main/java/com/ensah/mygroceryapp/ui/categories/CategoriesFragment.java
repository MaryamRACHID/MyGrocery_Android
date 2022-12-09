package com.ensah.mygroceryapp.ui.categories;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.databinding.FragmentCategoriesBinding;
import com.ensah.mygroceryapp.db.DatabaseHelper;

public class CategoriesFragment extends Fragment {

    private FragmentCategoriesBinding binding;
    Cursor c;
    SimpleCursorAdapter adapter;
    ListView listView;
    Button buttondisplay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoriesViewModel categoriesViewModel = new CategoriesViewModel();

        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        categoriesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        listView = (ListView) root.findViewById(R.id.catgListView);
        buttondisplay = (Button) root.findViewById(R.id.idBtnadd);

        DatabaseHelper myDbHelper = new DatabaseHelper (getActivity ());

        //<<<< No need for a button see commented out line above where setAdapter is used this works
        buttondisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}