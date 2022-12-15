package com.ensah.mygroceryapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Course;
import com.ensah.mygroceryapp.models.CourseArticle;

import java.util.ArrayList;
import java.util.List;

public class Add_list extends Fragment {
    public Add_list() {
        // Required empty public constructor
    }

    public static Add_list newInstance(String param1, String param2) {
        Add_list fragment = new Add_list();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    private DatabaseHelper Helper;
    EditText nameList;
    EditText descriptionList;
    Button addlst;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_list, container, false);
        nameList=(EditText) view.findViewById(R.id.list_name);
        descriptionList=(EditText)view.findViewById(R.id.list_description);
        addlst=(Button)view.findViewById(R.id.idAddlist);
        ListView lst=(ListView) view.findViewById(R.id.listIte);
        Helper = new DatabaseHelper(getContext());
        List<Article> a=Helper.getAllArticles1();
        CustomAdapter adapter = new CustomAdapter(getActivity(), a);
        final int[] state = {0};
        if(a!=null){
            lst.setAdapter(adapter);
        }

        addlst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom=String.valueOf(nameList.getText());
                String description=String.valueOf(descriptionList.getText());
                List<Article> produitList=adapter.selected;
                Helper = new DatabaseHelper(getContext());
                if(!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(description)){
                    Course c=new Course(nom,description);
                    if(Helper.createCourse(c)){
                        Toast.makeText(getActivity(), "succefuly added!", Toast.LENGTH_SHORT).show();
                        state[0] =1;
                    }
                    else {
                        Toast.makeText(getActivity(), "failed!", Toast.LENGTH_SHORT).show();}
                    if(produitList!=null && state[0]==1) {
                        Course currentCourse=Helper.getCourseByName(c.getName());
                        if(currentCourse!=null){
                        for (int i = 0; i < produitList.size(); i++) {
                            CourseArticle ca = new CourseArticle(currentCourse.getId(), produitList.get(i).getId(), 1);
                            if(Helper.createCourseArticle(ca)){
                                Toast.makeText(getActivity(), "succefuly added!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "failed adding course article!", Toast.LENGTH_SHORT).show();
                            }
                        }}
                        else{
                            Toast.makeText(getActivity(), "failed getting the course!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else{
                    Toast.makeText(getActivity(), "messing field!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }
}