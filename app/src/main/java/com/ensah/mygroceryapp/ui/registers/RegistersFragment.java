package com.ensah.mygroceryapp.ui.registers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.ensah.mygroceryapp.ProductAdapter;
import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.databinding.FragmentRegistersBinding;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Countproduct;
import com.ensah.mygroceryapp.Modify_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegistersFragment extends Fragment {

    private FragmentRegistersBinding binding;
    ListView listArray;
    private DatabaseHelper Helper;
    ProductAdapter adapter;
    Button modify;
    Button addtolist;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegistersViewModel registersViewModel =
                new ViewModelProvider(this).get(RegistersViewModel.class);
        Bundle arg = this.getArguments();
        Helper= new DatabaseHelper(getContext());
        Map<Article, Integer> articles=new HashMap<>();

        binding = FragmentRegistersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (arg != null) {
            String idCourse = arg.getString("id");
            String name=idCourse.split("\n")[0];
            articles=Helper.getArticlesOfCourse(name);
            if(articles!=null){
            listArray=root.findViewById(R.id.registersList);
            List<Countproduct> pro=new ArrayList<>();
            for (Map.Entry m : articles.entrySet()) {
                Article i= (Article) m.getKey();
                Countproduct c=new Countproduct(i.getName(),(Integer) m.getValue());
                pro.add(c);
            }
            adapter=new ProductAdapter(getActivity(),pro);
            listArray.setAdapter(adapter);
                Toast.makeText(getActivity(), ""+pro.size(), Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(getActivity(), "no products for this list!", Toast.LENGTH_SHORT).show();

            modify=root.findViewById(R.id.btnAddList);
            Set<Countproduct> dc=adapter.deletedProduct;
            Set<Countproduct> mf=adapter.modifyedProduct;
            modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (Countproduct s : dc) {
                      Helper.deleteArticleToCourse(name,s.getName());
                    }
                    for (Countproduct m : mf) {

                    }
                }
            });
            addtolist=root.findViewById(R.id.AddproductsTOlist);
            addtolist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new Modify_list();
                    Bundle arg=new Bundle();
                    arg.putString("id",idCourse);
                    fragment.setArguments(arg);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.register, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}