package com.ensah.mygroceryapp.ui.registers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RegistersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Register");
    }

    public LiveData<String> getText() {
        return mText;
    }
}