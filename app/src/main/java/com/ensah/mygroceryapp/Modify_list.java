package com.ensah.mygroceryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Course;
import com.ensah.mygroceryapp.models.CourseArticle;

import java.util.List;

public class Modify_list extends Fragment {
    EditText nameList, description;
    Button saveModification;
    ListView productsListView;
    CustomAdapter adapter;

    public Modify_list() {
        // Required empty public constructor
    }

    private DatabaseHelper Helper;

    public static Modify_list newInstance(String param1, String param2) {
        Modify_list fragment = new Modify_list();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modify_list, container, false);
        nameList = (EditText) v.findViewById(R.id.list_name_modifie);
        description = (EditText) v.findViewById(R.id.list_description_modifi);
        saveModification = (Button) v.findViewById(R.id.idModifylist);
        productsListView = (ListView) v.findViewById(R.id.listItem);
        Helper = new DatabaseHelper(getContext());
        List<Article> a = Helper.getAllArticles1();
        CustomAdapter adapter = new CustomAdapter(getActivity(), a);
        final int[] state = {0};
        if (a != null) {
            productsListView.setAdapter(adapter);
        }

        List<Article> produitsList = adapter.selected;
        Helper = new DatabaseHelper(getContext());
        String nameListe = String.valueOf(nameList.getText());
        String descriptionList = String.valueOf(description.getText());
        if (!TextUtils.isEmpty(nameListe) && !TextUtils.isEmpty(descriptionList)) {
            Course c = new Course(nameListe, descriptionList);
            if (Helper.createCourse(c)) {
                Toast.makeText(getActivity(), "UPDATED!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "UPDATE failed!", Toast.LENGTH_SHORT).show();
            }
            if (produitsList != null) {
                Course currentCourse = Helper.getCourseByName(c.getName());
                if (currentCourse != null) {
                    for (int i = 0; i < produitsList.size(); i++) {
                        CourseArticle ca = new CourseArticle(currentCourse.getId(), produitsList.get(i).getId(), 1);
                        if (Helper.createCourseArticle(ca)) {
                            Toast.makeText(getActivity(), "succefuly added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "failed adding course article!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "failed getting the course!", Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            Toast.makeText(getActivity(), "messing field!", Toast.LENGTH_SHORT).show();

        }
        return v;
    }
}