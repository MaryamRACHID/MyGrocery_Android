package com.ensah.mygroceryapp.ui.custCategories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustCategoriesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CustCategoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}