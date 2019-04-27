package com.example.infs3634finalassignmentv3;

//Importing all the necessary libraries
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    //Here, we set up the variables that are gonna be used in the app

    List<QuestionInformation> question_list;            //This is the list of Questions and Answers that we pull from
    String selected_answer;                             //This is string how we know what answer was selected
    Button[] options = new Button[4];                   //To make it easier to check our answers we put our Buttons in this array
    ArrayList<Integer> hasBeenAsked = new ArrayList<>();//This array stores the question id's that have been already asked

    int id;                                             //This is id of the question the user is going to be answering
    int q_count;                                        //This keeps track of the number of questions the user has answered
    int correct_answers;                                //This keeps track of the number of correct answers
    int wrong_answers;                                  //This keeps track of the number of correct answers


    Button option1_button;
    Button option2_button;
    Button option3_button;
    Button option4_button;
    Button next_button;
    TextView question_textview;
    TextView question_count_textview;
    TextView correct_textview;
    TextView wrong_textview;
    LinearLayout row1;
    LinearLayout row2;
    LinearLayout row3;
    LinearLayout row4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //The following code associates the Buttons and Views set up with the buttons and views from the layout resource file
        question_textview = findViewById(R.id.question_textview);
        question_count_textview = findViewById(R.id.question_count_textview);
        correct_textview = findViewById(R.id.correct_textview);
        wrong_textview = findViewById(R.id.wrong_textview);
        option1_button = findViewById(R.id.option1_button);
        option2_button = findViewById(R.id.option2_button);
        option3_button = findViewById(R.id.option3_button);
        option4_button = findViewById(R.id.option4_button);
        next_button = findViewById(R.id.next_button);
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);

        //We assign out buttons to the Button array created earlier
        options[0] = option1_button;
        options[1] = option2_button;
        options[2] = option3_button;
        options[3] = option4_button;

        /*This line ensure the next_button is invisibl as the user doesn't see until
         they select an answer */
        next_button.setVisibility(View.INVISIBLE);

        //The setQuiz() function is run which loads the quiz with the necessary information.
        setQuiz();

        /*Each button runs the same functions but passes it different variables. It sets
        selected_answer to whatever the Text of the button is. It then executes the checkQuestion
        function and passes it the selected_answer string. Finally it makes the next_button visible
        so the user can go to the next question */

        option1_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected_answer = option1_button.getText().toString();
                checkQuestion(selected_answer);
                next_button.setVisibility(View.VISIBLE);
            }
        });
        option2_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected_answer = option2_button.getText().toString();
                checkQuestion(selected_answer);
                next_button.setVisibility(View.VISIBLE);
            }
        });

        option3_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected_answer = option3_button.getText().toString();
                checkQuestion(selected_answer);
                next_button.setVisibility(View.VISIBLE);
            }
        });

        option4_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected_answer = option3_button.getText().toString();
                checkQuestion(selected_answer);
                next_button.setVisibility(View.VISIBLE);
            }
        });
        /*The next_button executes the nextQuestion function and then immediately makes itself invisible.
        This loads up the next question while giving the user time to realize what they got wrong or right*/
        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextQuestion();
                next_button.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setQuiz(){
        /*The setQuiz function runs at the launch of the Quiz activity. It pulls all the questions
        from QuestionInformation and adds them to question_list ArrayList. */

        question_list = new ArrayList<>();
        for (int i = 0; i < new QuizBank().answers.length; i++){
            question_list.add(new QuestionInformation(new QuizBank().q_id[i], new QuizBank().questions[i], new QuizBank().answers[i]));
        }
        /*The function then sets the question count to 1 and for the first question sets the id to 2.
        This is a default question*/
        q_count = 1;
        id = 2;

        //The end of the setQuiz function assigns text to the question TextView and option Buttons
        //The first question is always the same.
        question_textview.setText(question_list.get(id).getQuestion());
        option1_button.setText(question_list.get(0).getAnswer());
        option2_button.setText(question_list.get(1).getAnswer());
        option3_button.setText(question_list.get(id).getAnswer());
        option4_button.setText(question_list.get(3).getAnswer());
    }

    public void checkQuestion(String answer_name) {
        /*The check question function first goes through all 4 buttons in the activity by looping
        over the options Button array. As it loops over it figures out which is the correct answer
        and changes the background to green while changing the background of all others to red*/

        for(int x=0; x<options.length; x++){
            if(options[x].getText().toString() == question_list.get(id).getAnswer()){
                /*This sets the LinearLayout parent of the current button to have a green background
                since it is the correct answer*/
                LinearLayout parent_linear = (LinearLayout) options[x].getParent();
                parent_linear.setBackgroundResource(R.drawable.roundedborder_correct);
            } else {
                /*This sets the LinearLayout parent of the other buttons to have a red background
                since they are the wrong answer*/
                LinearLayout parent_linear = (LinearLayout) options[x].getParent();
                parent_linear.setBackgroundResource(R.drawable.roundedborder_wrong);
            }
        }
        //This for loop disables all the buttons after the user has chosen their answer
        for(int x=0; x<options.length; x++){
            options[x].setEnabled(false);
        }

        /*This "if statement" does a simple check to determine if the correct answer was picked.
        Since the checkQuestion function takes in a String answer_name, which is determined by the
        getText of the button it was sent from, it just checks if that is equal to the answer
        for the current question*/
        if (answer_name == question_list.get(id).getAnswer()) {
            correct_answers = correct_answers + 1; //This adds to number of questions you got correct
            correct_textview.setText("Correct: " + Integer.toString(correct_answers)); //Updates the TextView to display this increase
        } else {
            wrong_answers = wrong_answers +  1; //This adds to number of questions you got wrong
            wrong_textview.setText("Wrong: " + Integer.toString(wrong_answers)); //Updates the TextView to display this increase
        }
        hasBeenAsked.add(id); //Adds the question id to the hasBeenAsked array so they won't be repeated
    }

    public void nextQuestion(){
        /*This is the function that pulls random questions from question_list. It also updates all the
        necessary TextView and Buttons with the new question while updating the number of questions
        already answered*/

        Random r_id = new Random(); //The random that lets us pull a random question from the question list
        Random r_qid = new Random(); //The random that decides which option to assign the correct answer

        /*Before anything happens this checks if more than 20 questions have been answered.
        If they have go to the ScoreScreenActivity and send it the number of correct answers the
        user got*/
        if (q_count >= 20) {
            endQuiz(Integer.toString(correct_answers));
        }

        //this for loop re-enables the option buttons for the next question
        for(int x=0; x<options.length; x++){
            options[x].setEnabled(true);
        }

        //Here we genearate a random integer between 0 and 40 and assign it to id
        //We also check if the id generated is already in the hasBeenAsked array
        int check_id;
        do {
            check_id = r_id.nextInt(40);
        } while (hasBeenAsked.contains(check_id)); //if it has we generate a new one
        id = check_id;

        //We also generate a random integer between 0 and 4
        int qid = r_qid.nextInt(4);

        //Increments q_count and updates the Textview with the Current Question Number
        q_count = q_count + 1;
        String question_count;
        question_count = "Question " + Integer.toString(q_count);
        question_count_textview.setText(question_count);

        /*The rest of the code updates the Question Textview with the new question while randomly
        selecting a button to assign as the correct answer. */
        question_textview.setText(question_list.get(id).getQuestion());

        if (qid == 0) {
            /*Depending on which button was picked it assigns the answer to that specific button.
            The other buttons are assigned a random answer from the question_list except for the
            answer for the current question. This is done with the exceptRand function written at
            the end of the activity*/
            option1_button.setText(question_list.get(id).getAnswer());
            option2_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option3_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option4_button.setText(question_list.get(exceptRand(id)).getAnswer());
        }else if(qid == 1) {
            option2_button.setText(question_list.get(id).getAnswer());
            option1_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option3_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option4_button.setText(question_list.get(exceptRand(id)).getAnswer());
        } else if (qid == 2){
            option3_button.setText(question_list.get(id).getAnswer());
            option1_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option2_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option4_button.setText(question_list.get(exceptRand(id)).getAnswer());
        }else if(qid == 3){
            option4_button.setText(question_list.get(id).getAnswer());
            option2_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option3_button.setText(question_list.get(exceptRand(id)).getAnswer());
            option1_button.setText(question_list.get(exceptRand(id)).getAnswer());
        }
        //Finally at the end of the function the rows are reverted back to their original colors.
        row1.setBackgroundResource(R.drawable.roundedborder1);
        row2.setBackgroundResource(R.drawable.roundedborder2);
        row3.setBackgroundResource(R.drawable.roundedborder3);
        row4.setBackgroundResource(R.drawable.roundedborder4);
    }

    public int exceptRand(int rand){
        /*This function keeps generating a random integer until it gets an integer that
        is not equal to one passed to it.*/
        Random rando = new Random();
        int exceptint;
        do {
            exceptint = rando.nextInt(40);
        } while (exceptint == rand);
        return exceptint;
    }

    public void endQuiz(String score) {
        /*The endQuiz function is triggered after 20 questions have been answered. It launches the
        ScoreScreen activity and takes in a String that will be filled with the correct answer
        score. This string is sent to the ScoreScreen activity on its launch*/

        Intent intent = new Intent(this, ScoreScreen.class);
        Bundle extras = new Bundle();
        extras.putString("final_score", score);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
