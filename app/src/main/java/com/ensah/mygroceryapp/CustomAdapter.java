package com.ensah.mygroceryapp;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.ensah.mygroceryapp.models.Article;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Activity acivite;
    List<Article> articles;
    LayoutInflater inflater;
    List<Article> selected=new ArrayList<>();

    public CustomAdapter(Activity acivite){
        this.acivite=acivite;
    }
    public CustomAdapter(Activity acivite,List<Article> articles){
        this.acivite=acivite;
        this.articles=articles;
        inflater=acivite.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int i) {
        return articles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public boolean isChecked(int position) {
        return articles.get(position).getSelectionState();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.raw_item, viewGroup, false);

            holder = new ViewHolder();

            holder.productName = (TextView)view.findViewById(R.id.productName);
            holder.checkbox = (CheckBox) view.findViewById(R.id.checkBox);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        Article article = articles.get(i);
        holder.productName.setText(article.getName());
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean currentState=article.getSelectionState();
                boolean newState = !currentState;
                article.setSelectionState(newState);

                if(article.getSelectionState()==true){
                    selected.add(article);
                }
                else{
                    if(selected.contains(article) && newState==false){
                        selected.remove(article);
                    }
                }
            }
        });
        return view;
    }

    public void updateRecords(List<Article> articles){
        this.articles = articles;

        notifyDataSetChanged();
    }

    class ViewHolder {
        public CheckBox checkbox;
        TextView productName;
    }
}
