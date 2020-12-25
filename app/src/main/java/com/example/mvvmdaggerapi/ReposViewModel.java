package com.example.mvvmdaggerapi;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdaggerapi.model.Repos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposViewModel extends ViewModel {
    private List<Repos> listSearch;
    private MutableLiveData<List<Repos>> exampleList;
    private List<Repos> examples;

    public LiveData<List<Repos>> getExampleList() {
        if(exampleList == null){
            exampleList = new MutableLiveData<>();
            loadByRetrofit();
        }
        return exampleList;
    }

    private void loadByRetrofit() {
        examples = new ArrayList<>();
        MyRetrofit.getInStance().getAllItem().enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                getExamples(response);

            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {

            }
        });
    }

    private void getExamples(Response<List<Repos>> response) {
        exampleList.setValue(response.body());
        examples = response.body();
    }
    public List<Repos> findByName(Editable editable) {
        listSearch = new ArrayList<>();
        for (Repos repos : examples){
            String name = repos.getName();
            String full = repos.getFullName();
            if(name.toLowerCase().contains(editable) || full.toLowerCase().contains(editable)){
                listSearch.add(repos);
            }
        }
        return listSearch;
    }
}
