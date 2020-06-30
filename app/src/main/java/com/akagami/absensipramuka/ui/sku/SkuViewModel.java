package com.akagami.absensipramuka.ui.sku;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SkuViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SkuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

