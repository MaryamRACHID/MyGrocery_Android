package com.ensah.mygroceryapp.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ensah.mygroceryapp.Add_list;
import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.addCategorie;
import com.ensah.mygroceryapp.databinding.FragmentListsBinding;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.CourseArticle;
import com.ensah.mygroceryapp.ui.registers.RegistersFragment;
import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.List;

public class ListsFragment extends Fragment {
    private FragmentListsBinding binding;
    ArrayAdapter<String> adapter;
    Button addList;
    ListView list;
    private DatabaseHelper Helper;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListsViewModel listsViewModel = new ListsViewModel();

        binding = FragmentListsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        list=(ListView) root.findViewById(R.id.ListView);
        listsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        addList=root.findViewById(R.id.idBnAddtList);
        Helper= new DatabaseHelper(getContext());
        List<String> displayList=Helper.getAllCourses();
//        List<String> displayList=new ArrayList<>();
//        List<CourseArticle> cra=Helper.getAllCourseArticles();
//        for(int i=0;i<cra.size();i++){
//            displayList.add(Helper.getCourseById(cra.get(i).getCourse_id()).toString());
//        }
        if(displayList!=null){
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, displayList);
            list.setAdapter(adapter);
        }
        else{
            Toast.makeText(getActivity(), "No lists", Toast.LENGTH_SHORT).show();
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String selectedFromList = (String) (list.getItemAtPosition(position));
                Fragment fragment = new RegistersFragment();
                Bundle arg=new Bundle();
                arg.putString("id",selectedFromList);
                fragment.setArguments(arg);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.list, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Add_list();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RelativeLayout layout=(RelativeLayout) root.findViewById(R.id.displayList);
                layout.removeAllViewsInLayout();
                fragmentTransaction.add(R.id.list, fragment);
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