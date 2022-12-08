package com.ensah.mygroceryapp.ui.custProduct;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustProductsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CustProductsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}