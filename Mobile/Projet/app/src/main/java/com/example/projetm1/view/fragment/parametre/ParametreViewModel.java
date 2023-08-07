package com.example.projetm1.view.fragment.parametre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParametreViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ParametreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is parametre fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}