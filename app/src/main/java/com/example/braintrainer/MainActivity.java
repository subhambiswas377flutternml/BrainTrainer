package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String name;
    int ans,check_ans; // to store and check the correct answer of each case

    EditText nameEditText;
    Button startButton;

    TextView timeTextView;
    TextView scoreTextView;
    TextView operationTextView;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    TextView opTextView;

    TextView resultTextView;
    Button restart;

    CountDownTimer cdt;

    boolean is_done;
    boolean is_started;

    int qno;
    int score;

    public void showScore()
    {
        Toast msg = Toast.makeText(this,nameEditText.getText().toString()+", You scored "+Integer.toString(score)+" out of 50.",Toast.LENGTH_LONG);
        msg.show();
    }

    public void publish()
    {
        timeTextView.animate().alpha(0).setDuration(500);
        scoreTextView.animate().alpha(0).setDuration(500);
        operationTextView.animate().alpha(0).setDuration(500);

        button1.animate().alpha(0).setDuration(500);
        button2.animate().alpha(0).setDuration(500);
        button3.animate().alpha(0).setDuration(500);
        button4.animate().alpha(0).setDuration(500);

        opTextView.animate().alpha(0).setDuration(500);

        resultTextView.animate().alpha(1).scaleX(0.5f).scaleY(0.5f).setDuration(4000);


        restart.animate().alpha(1).translationY(-400f).setDuration(4500);
        resultTextView.setText(Integer.toString(score/5)+"/10");

        new CountDownTimer(4800,1000)
        {
            public void onTick(long y)
            {
                // do nothing
            }

            public void onFinish()
            {
                showScore();
            }
        }.start();

    }

    public void sumGenerator()
    {
        Character operator[] = {'+','-','*','/'};

        int a,b,x,y;
        String t;
        int indx;

        Random r = new Random();
        a = r.nextInt(4); // for determining random operator
        x = r.nextInt(50); // for determining random operand
        y = r.nextInt(50); // for determining random operator
        x+=1;
        y+=1;
        indx = r.nextInt(4); // for determining random index for right answer

        t = Integer.toString(x)+operator[a]+Integer.toString(y);
        operationTextView.setText(t);
        switch(a)
        {
            case 0:ans = x+y;
                    break;
            case 1:ans = x-y;
                    break;
            case 2:ans = x*y;
                    break;
            case 3:ans = x/y;
                    break;
        }

        switch(indx)
        {
            case 0:
                button1.setText(Integer.toString(ans));
                button2.setText(Integer.toString((r.nextInt(20))+ans-20));
                button3.setText(Integer.toString((r.nextInt(20))+ans-20));
                button4.setText(Integer.toString((r.nextInt(20))+ans));
                break;
            case 1:
                button1.setText(Integer.toString((r.nextInt(20))+ans));
                button2.setText(Integer.toString(ans));
                button3.setText(Integer.toString((r.nextInt(20))+ans));
                button4.setText(Integer.toString((r.nextInt(20))+ans-20));
                break;
            case 2:
                button1.setText(Integer.toString((r.nextInt(20))+ans-20));
                button2.setText(Integer.toString((r.nextInt(20))+ans));
                button3.setText(Integer.toString(ans));
                button4.setText(Integer.toString((r.nextInt(20))+ans));
                break;
            case 3:
                button1.setText(Integer.toString((r.nextInt(20))+ans));
                button2.setText(Integer.toString((r.nextInt(20))+ans-20));
                button3.setText(Integer.toString((r.nextInt(20))+ans-20));
                button4.setText(Integer.toString(ans));
                break;

        }

    }

    public void countDown()
    {
        cdt = new CountDownTimer(20000,1000)
        {
            public void onTick(long timeleft)
            {
                timeTextView.setText(String.valueOf(timeleft/1000));
            }

            public void onFinish()
            {
                if(is_done)
                {
                    // do nothing
                }
                else
                {
                    opTextView.setVisibility(View.VISIBLE);
                    opTextView.setText("TimeOut!");
                    new CountDownTimer(5000,1000)
                    {
                        public void onTick(long tm)
                        {
                            // do nothing
                        }
                        public void onFinish()
                        {
                            cdt.cancel();
                            publish();
                        }
                    }.start();
                }
            }
        };

        cdt.start();
    }

    public void mainOperation()
    {
        opTextView.setVisibility(View.INVISIBLE);
        is_done = false;
        qno+=1;
        if(qno==11)
        {
            publish();
            return;
        }
        scoreTextView.setText(Integer.toString(qno)+"/"+Integer.toString(score));
        sumGenerator();
        countDown();
    }

    public void bt1(View view) throws Exception
    {
        if(is_done)
        {
            return;
        }
        is_done = true;
        String s = button1.getText().toString();
        check_ans = Integer.parseInt(s);
        opTextView.setVisibility(View.VISIBLE);
        if(ans==check_ans)
        {
            opTextView.setText("Correct!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    // do nothing
                }
                public void onFinish()
                {
                    score+=5;
                    scoreTextView.setText(Integer.toString(qno)+"/"+Integer.toString(score));
                    cdt.cancel();
                    mainOperation();
                }
            }.start();

        }
        else
        {
            opTextView.setText("Wrong!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    cdt.cancel();
                }
                public void onFinish()
                {
                    publish();
                }
            }.start();
        }
    }

    public void bt2(View view) throws Exception
    {
        if(is_done)
        {
            return;
        }
        is_done = true;
        String s = button2.getText().toString();
        check_ans = Integer.parseInt(s);
        opTextView.setVisibility(View.VISIBLE);
        if(ans==check_ans)
        {
            opTextView.setText("Correct!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    // do nothing
                }
                public void onFinish()
                {
                    score+=5;
                    scoreTextView.setText(Integer.toString(qno)+"/"+Integer.toString(score));
                    cdt.cancel();
                    mainOperation();
                }
            }.start();
        }
        else
        {
            opTextView.setText("Wrong!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    cdt.cancel();
                }
                public void onFinish()
                {
                    publish();
                }
            }.start();
        }
    }

    public void bt3(View view) throws Exception
    {
        if(is_done)
        {
            return;
        }
        is_done = true;
        String s = button3.getText().toString();
        check_ans = Integer.parseInt(s);
        opTextView.setVisibility(View.VISIBLE);
        if(ans==check_ans)
        {
            opTextView.setText("Correct!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    // do nothing
                }
                public void onFinish()
                {
                    score+=5;
                    scoreTextView.setText(Integer.toString(qno)+"/"+Integer.toString(score));
                    cdt.cancel();
                    mainOperation();
                }
            }.start();
        }
        else
        {
            opTextView.setText("Wrong!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    cdt.cancel();
                }
                public void onFinish()
                {
                    publish();
                }
            }.start();
        }
    }

    public void bt4(View view) throws Exception
    {
        if(is_done)
        {
            return;
        }
        is_done = true;
        String s = button4.getText().toString();
        check_ans = Integer.parseInt(s);
        opTextView.setVisibility(View.VISIBLE);
        if(ans==check_ans)
        {
            opTextView.setText("Correct!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    // do nothing
                }
                public void onFinish()
                {
                    score+=5;
                    scoreTextView.setText(Integer.toString(qno)+"/"+Integer.toString(score));
                    cdt.cancel();
                    mainOperation();
                }
            }.start();
        }
        else
        {
            opTextView.setText("Wrong!");
            new CountDownTimer(5000,1000)
            {
                public void onTick(long tm)
                {
                    cdt.cancel();
                }
                public void onFinish()
                {
                    publish();
                }
            }.start();
        }
    }

    public void transition(View view)
    {
        resultTextView.animate().alpha(0).scaleX(1f).scaleY(1f).setDuration(100);
        restart.animate().alpha(0).translationY(400f).setDuration(100);


        timeTextView.animate().alpha(1).setDuration(2800);
        scoreTextView.animate().alpha(1).setDuration(2800);
        operationTextView.animate().alpha(1).setDuration(2800);

        button1.animate().alpha(1).setDuration(2800);
        button2.animate().alpha(1).setDuration(2800);
        button3.animate().alpha(1).setDuration(2800);
        button4.animate().alpha(1).setDuration(2800);

        opTextView.animate().alpha(1).setDuration(2800);

        qno = 0;
        score = 0;

        is_done = false;

        cdt.cancel();

        mainOperation();

    }
    public void start(View view)
    {


        name = nameEditText.getText().toString();

        nameEditText.animate().alpha(0).translationY(-700f).setDuration(500);
        startButton.animate().alpha(0).translationY(-1500f).setDuration(200);

        transition(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        operationTextView = (TextView) findViewById(R.id.operationTextView);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        opTextView = (TextView) findViewById(R.id.opTextView);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        restart = (Button) findViewById(R.id.restart);

        qno = 0;
        score = 0;
        is_done = false;
        is_started = true;

        // for initializing
        cdt = new CountDownTimer(1000,1000)
        {
            public void onTick(long xy)
            {
                //do nothing
            }
            public void onFinish()
            {
                //do nothing
            }
        }.start();

    }
}