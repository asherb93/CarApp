package com.example.carapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carapp.Logic.Score;
import com.example.carapp.R;
import com.example.carapp.interfaces.ScoreCallback;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoresViewHolder>  {

    private ArrayList<Score> adapterScoreList ;

    public ScoreAdapter(ArrayList<Score> scoreArrayList){this.adapterScoreList=scoreArrayList;}

    private ScoreCallback scoreCallback;



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
        holder.scoreTV.setText(""+score.getUserScore());
        holder.nameTV.setText(""+score.getUserName());
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
            numberTV=itemView.findViewById(R.id.number_LBL);
            nameTV=itemView.findViewById(R.id.name_LBL);
            scoreTV=itemView.findViewById(R.id.score_LBL);

            itemView.setOnClickListener(v -> {
                if (scoreCallback != null)
                    scoreCallback.getScore(getItem(getAdapterPosition()));
            });

        }
    }


}
