package com.ensah.mygroceryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Categorie;
import com.ensah.mygroceryapp.ui.categories.CategoriesFragment;
import com.ensah.mygroceryapp.ui.products.ProductsFragment;

import java.util.List;
public class addProduct extends Fragment {


    public addProduct() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static addProduct newInstance(String param1, String param2) {
        addProduct fragment = new addProduct();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    Spinner categories;
    EditText nomProduit,unite;
    ArrayAdapter<String> adapter;
    private DatabaseHelper Helper;
    Button addprodTOdb;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_product, container, false);
//        Spinner
        categories = (Spinner) view.findViewById(R.id.categorie_spinner);
        Helper = new DatabaseHelper(getContext());
        List<String> cat = Helper.getAllCategoriesNames();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cat);
        categories.setAdapter(adapter);
//        AddProduct
        nomProduit=(EditText) view.findViewById(R.id.name);
        unite=(EditText) view.findViewById(R.id.unite);
        addprodTOdb=(Button) view.findViewById(R.id.idAddprod);

        addprodTOdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameprod= String.valueOf(nomProduit.getText());
                String uniteprod=String.valueOf(unite.getText());
                String ctg = categories.getSelectedItem().toString();
                if(!TextUtils.isEmpty(nameprod) && !TextUtils.isEmpty(uniteprod) && !TextUtils.isEmpty(ctg)){
                    Article a=new Article(nameprod,uniteprod,ctg);
                    if(Helper.createArticle(a)){
                        Toast.makeText(getActivity(), "succefuly added!", Toast.LENGTH_SHORT).show();
                        Fragment fragment = new ProductsFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.idPro, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else {
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(getActivity(), "messing field!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;

    }
}