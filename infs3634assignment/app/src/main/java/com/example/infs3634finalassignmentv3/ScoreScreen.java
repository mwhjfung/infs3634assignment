package com.example.infs3634finalassignmentv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreScreen extends AppCompatActivity {
    //Sets up the views and buttons to be used
    TextView score_textview;
    Button home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        //Assigns the views and buttons on the layout file
        home_button = findViewById(R.id.home_button);
        score_textview = findViewById(R.id.score_textview);

        //Grabs the final_score string from the bundle that was with the activity at creation
        Bundle extras = getIntent().getExtras();
        String final_score = extras.getString("final_score");

        //Set the score_textview to display the users score out of 20 questions
        score_textview.setText("You got " +final_score  + "/20 Questions Correct");

        //A button to take the user home so they can do the quiz again
        home_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goHome();
            }
        });

    }

    public void goHome() {
        //The goHome intent function to take the user the home page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
