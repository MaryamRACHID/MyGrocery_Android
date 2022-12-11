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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Categorie;
import com.ensah.mygroceryapp.ui.categories.CategoriesFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addCategorie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addCategorie extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addCategorie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addCategorie.
     */
    // TODO: Rename and change types and number of parameters
    public static addCategorie newInstance(String param1, String param2) {
        addCategorie fragment = new addCategorie();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Button addctgr;
    EditText name;
    EditText description;
    private DatabaseHelper Helper;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_add_categorie, container, false);
         addctgr=view.findViewById(R.id.idAddc);
         name=(EditText) view.findViewById(R.id.nameCategorie);
         description=(EditText) view.findViewById(R.id.descriptionCategorie);
         addctgr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String nom= String.valueOf(name.getText());
                 String descrpt= String.valueOf(description.getText());
                 Helper = new DatabaseHelper(getContext());
                 if(!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(descrpt)){
                     Categorie c=new Categorie(nom,descrpt);
                     if(Helper.createCategories(c)){
                         Toast.makeText(getActivity(), "succefuly added!", Toast.LENGTH_SHORT).show();
                         Fragment fragment = new CategoriesFragment();
                         FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                         FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                         fragmentTransaction.replace(R.id.addcategorie, fragment);
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