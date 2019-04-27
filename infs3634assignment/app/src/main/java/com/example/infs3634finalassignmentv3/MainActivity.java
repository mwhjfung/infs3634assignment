package com.example.infs3634finalassignmentv3;
//import Bundle and Intent Library
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//This is the launch page of the App
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associates the button with the startActivity intent function.
        final Button quiz_button = findViewById(R.id.quiz_button);

        quiz_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goQuiz();
            }
        });
    }

    //Setting up the intent function to launch the Quiz Activity
    public void goQuiz() {
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

}
