package com.ensah.mygroceryapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Countproduct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductAdapter extends BaseAdapter {
    Activity acivity;
    List<Countproduct> article;
    LayoutInflater inflater;
    public Set<Countproduct> deletedProduct=new HashSet<>();
    public Set<Countproduct> modifyedProduct=new HashSet<>();
    int istate=0;
    public ProductAdapter(Activity acivite){
        this.acivity=acivite;
    }
    public ProductAdapter(Activity acivite,List<Countproduct> article){
        this.acivity=acivite;
        this.article=article;
        inflater=acivite.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return article.size();
    }

    @Override
    public Object getItem(int i) {
        return article.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.product_items, viewGroup, false);
            holder = new ViewHolder();
            holder.prodName = (TextView)view.findViewById(R.id.prodName);
            holder.boutton = (Button) view.findViewById(R.id.addCounter);
            holder.delete=(Button) view.findViewById(R.id.deletElement);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        Countproduct articleItem = article.get(i);
        holder.prodName.setText(articleItem.getName()+" : "+articleItem.getCount());

        holder.boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleItem.setCount(articleItem.getCount()+1);
                article.set(i,articleItem);
                updateRecords(article);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(articleItem.getCount()>1){
                    articleItem.setCount(articleItem.getCount()-1);
                    article.set(istate,articleItem);
                    for(Countproduct c:deletedProduct){
                        if(!(c.getCount()==articleItem.getCount()) && c.getName()==articleItem.getName())
                        {
                            istate=1;
                        }
                    }
                    if(istate==1){
                    modifyedProduct.add(articleItem)
                    ;}
                    updateRecords(article);
                }
                else{
                    article.remove(articleItem);

                    deletedProduct.add(articleItem);
                    updateRecords(article);
                }
            }
        });
        return  view;
    }
    public void updateRecords(List<Countproduct> article){
        this.article = article;
        notifyDataSetChanged();
    }
    class ViewHolder {
        public Button boutton;
        Button delete;
        TextView prodName;
    }
}
