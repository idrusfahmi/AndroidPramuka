package com.akagami.absensipramuka.ui.iuran;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IuranViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IuranViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() { return mText; }
}
