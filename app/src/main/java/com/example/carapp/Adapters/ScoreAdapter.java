package com.example.carapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carapp.Fragments.MapFragment;
import com.example.carapp.Logic.Score;
import com.example.carapp.R;
import com.example.carapp.interfaces.MapCallback;
import com.example.carapp.interfaces.ScoreCallback;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoresViewHolder>  {

    private ArrayList<Score> adapterScoreList ;

    public ScoreAdapter(ArrayList<Score> scoreArrayList){this.adapterScoreList=scoreArrayList;}

    public MapCallback mapCallback;


    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item,parent,false);
        ScoresViewHolder scoresViewHolder = new ScoresViewHolder(view);
        return scoresViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        Score score=getItem(position);
        holder.scoreTV.setText("Score: "+score.getUserScore());
        holder.nameTV.setText("Name: "+score.getUserName());
    }


    @Override
    public int getItemCount() {
        return this.adapterScoreList.size();
    }

    private Score getItem(int position) {
        return this.adapterScoreList.get(position);
    }

    public void setScoreCallback(ScoreCallback scoreCallback) {
        this.scoreCallback=scoreCallback;
    }


    public class ScoresViewHolder extends RecyclerView.ViewHolder{

        private TextView numberTV;
        private TextView nameTV;
        private TextView scoreTV;
        public ScoresViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.name_LBL);
            scoreTV=itemView.findViewById(R.id.score_LBL);

            itemView.setOnClickListener(v -> {
                if (scoreCallback != null)
                    scoreCallback.getScore(getItem(getAdapterPosition()));
            });


        }
    }

    private ScoreCallback scoreCallback=new ScoreCallback() {
        @Override
        public void getScore(Score score) {
            MapFragment mapFragment=new MapFragment();

        }
    };

    private void setMapCallback(MapCallback mapCallback){
        this.mapCallback=mapCallback;
    }



}
