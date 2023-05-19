package com.example.carapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carapp.Adapters.ScoreAdapter;
import com.example.carapp.Logic.Score;
import com.example.carapp.R;
import com.example.carapp.Utilities.DataManager;
import com.example.carapp.interfaces.ScoreCallback;

public class ScoreListFragment extends Fragment {

    RecyclerView main_LST_scores;



    public ScoreListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_score_list, container, false);
        findViews(view);
        initView();

        return view;
    }

    private void initView() {
        ScoreAdapter scoreAdapter=new ScoreAdapter(DataManager.getInstance().getScoresList().getScoreList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_scores.setLayoutManager(linearLayoutManager);
        main_LST_scores.setAdapter(scoreAdapter);

    }



    private void findViews(View view) {
        main_LST_scores = view.findViewById(R.id.main_LST_movies);
    }

}