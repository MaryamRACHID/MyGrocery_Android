package com.ensah.mygroceryapp.ui.products;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.addProduct;
import com.ensah.mygroceryapp.databinding.FragmentProductsBinding;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;

import java.util.List;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    Cursor c;
    SimpleCursorAdapter adapter;
    ListView productslist;
    Button button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductsViewModel productsViewModell = new ProductsViewModel();
        ArrayAdapter<Article> adapter;
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        productsViewModell.getText().observe(getViewLifecycleOwner(), textView::setText);
        DatabaseHelper myDbHelper = new DatabaseHelper (getActivity ());
        productslist = (ListView) root.findViewById(R.id.productListView);
        button = (Button) root.findViewById(R.id.btnAddProduct);
        List<Article> list=myDbHelper.getAllArticles();
        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        productslist.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new addProduct();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.product, fragment);
                fragmentTransaction.addToBackStack(null);
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