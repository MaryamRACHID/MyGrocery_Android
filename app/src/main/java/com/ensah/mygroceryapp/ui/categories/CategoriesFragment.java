package com.ensah.mygroceryapp.ui.categories;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.addCategorie;
import com.ensah.mygroceryapp.addProduct;
import com.ensah.mygroceryapp.databinding.FragmentCategoriesBinding;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Categorie;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private FragmentCategoriesBinding binding;
    private DatabaseHelper Helper;
    ListView listView;
    Button buttondisplay;
    ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoriesViewModel categoriesViewModel = new CategoriesViewModel();

        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        categoriesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        listView = (ListView) root.findViewById(R.id.catgListView);
        buttondisplay = (Button) root.findViewById(R.id.idBtnaddcategorie);
        Helper = new DatabaseHelper(getContext());
        List<String> categories = Helper.getAllCategories();
        if(categories!=null){
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categories);
            listView.setAdapter(adapter);
        }
        //<<<< No need for a button see commented out line above where setAdapter is used this works
        buttondisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new addCategorie();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RelativeLayout layout=(RelativeLayout) root.findViewById(R.id.categorie);
                layout.removeAllViewsInLayout();
                fragmentTransaction.add(R.id.ctgr, fragment);
                fragmentTransaction.addToBackStack(String.valueOf(R.id.ctgr));
                fragmentTransaction.commit();
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