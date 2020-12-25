package com.example.mvvmdaggerapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.mvvmdaggerapi.model.Repos;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText edt_Search;
    private RecyclerView recycleView;
    private ReposAdapter adapter;
    private List<Repos> repos,listSearch;
    private ReposViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }

    private void initView() {
        recycleView = findViewById(R.id.rv_listAll);
        edt_Search = findViewById(R.id.edt_search);
        recycleView.addItemDecoration(new DividerItemDecoration(recycleView.getContext(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recycleView.setLayoutManager(layoutManager);

        getAllItem();
        edt_Search.addTextChangedListener(this);
    }

    private void getAllItem() {
        viewModel = new ReposViewModel();
        viewModel.getExampleList().observe(this, new Observer<List<Repos>>() {
            @Override
            public void onChanged(List<Repos> repos) {
                adapter = new ReposAdapter(MainActivity.this,repos);
                recycleView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        listSearch = viewModel.findByName(editable);
        adapter.filterList(viewModel.findByName(editable));
    }
}