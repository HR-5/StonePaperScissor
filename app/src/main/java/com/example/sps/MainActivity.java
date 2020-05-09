package com.example.sps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int pno;
    int rno;
    int round;
    int flag;
    String name1;
    String name2;
    String chance;
    String s;
    String st1;
    String st2;
    int player;
    int pl1;
    int pl2;
    int sc1;
    int sc2;
    TextView ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag = 0;
        ch = (TextView) findViewById(R.id.chance);
        if(savedInstanceState!=null){
            flag = savedInstanceState.getInt("flag");
            if(flag == 1){
                single(null);
            }
            else if(flag == 2){
                multi(null);
            }
            else if(flag == 3){
                name1 = savedInstanceState.getString("name1");
                name2 = savedInstanceState.getString("name2");
                chance = savedInstanceState.getString("ch");
                sc2 = savedInstanceState.getInt("sc2");
                sc1 = savedInstanceState.getInt("sc1");
                st1 = savedInstanceState.getString("st1");
                st2 = savedInstanceState.getString("st2");
                round = savedInstanceState.getInt("round");
                rno = savedInstanceState.getInt("rno");
                player = savedInstanceState.getInt("pl");
                pno = savedInstanceState.getInt("pno");
                pl2 = savedInstanceState.getInt("pl2");
                pl1 = savedInstanceState.getInt("pl1");
                setContentView(R.layout.single);
                TextView s1 = (TextView) findViewById(R.id.s1);
                TextView s2 = (TextView) findViewById(R.id.s2);
                TextView rou = (TextView) findViewById(R.id.rno);
                ch = (TextView) findViewById(R.id.chance);
                String n1 = name1 + "'s Score:" +sc1;
                String n2 = name2 + "'s Score:" +sc2;
                s1.setText(n1);
                s2.setText(n2);
                ch.setText(chance);
                String r = "Round:" + round;
                rou.setText(r);
                if(round>=rno) {
                    r = "Round:" + (round-1);
                    rou.setText(r);
                    result();
                }
            }
        }

    }

    public void onSaveInstanceState(@NonNull Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt("flag", flag);
        outstate.putString("name1", name1);
        outstate.putString("name2", name2);
        outstate.putString("ch", chance);
        outstate.putString("res", s);
        outstate.putString("st1", st1);
        outstate.putString("st2", st2);
        outstate.putInt("sc1",sc1);
        outstate.putInt("sc2",sc2);
        outstate.putInt("pl",player);
        outstate.putInt("pno",pno);
        outstate.putInt("rno",rno);
        outstate.putInt("round",round);
        outstate.putInt("pl1",pl1);
        outstate.putInt("pl2",pl2);
    }

    public void home(View view){
        flag = 0;
        setContentView(R.layout.activity_main);
        Button si = (Button) findViewById(R.id.single);
        Button mu = (Button) findViewById(R.id.multi);
        Button play = (Button) findViewById(R.id.play);
        TextView or = (TextView) findViewById(R.id.or);
        EditText p1 = (EditText) findViewById(R.id.p1);
        EditText p2 = (EditText) findViewById(R.id.p2);
        ImageButton img = (ImageButton) findViewById(R.id.home);
        EditText ro = (EditText) findViewById(R.id.rounds);
        si.setVisibility(View.VISIBLE);
        mu.setVisibility(View.VISIBLE);
        or.setVisibility(View.VISIBLE);
        p1.setVisibility(View.INVISIBLE);
        p2.setVisibility(View.INVISIBLE);
        ro.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
    }

    public void single(View view) {
        flag = 1;
        Button si = (Button) findViewById(R.id.single);
        Button mu = (Button) findViewById(R.id.multi);
        Button play = (Button) findViewById(R.id.play);
        TextView or = (TextView) findViewById(R.id.or);
        EditText p1 = (EditText) findViewById(R.id.p1);
        EditText ro = (EditText) findViewById(R.id.rounds);
        ImageButton img = (ImageButton) findViewById(R.id.home);
        si.setVisibility(View.INVISIBLE);
        mu.setVisibility(View.INVISIBLE);
        or.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.VISIBLE);
        ro.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        pno = 1;
    }

    public void multi(View view) {
        flag = 2;
        Button si = (Button) findViewById(R.id.single);
        Button mu = (Button) findViewById(R.id.multi);
        Button play = (Button) findViewById(R.id.play);
        TextView or = (TextView) findViewById(R.id.or);
        EditText p1 = (EditText) findViewById(R.id.p1);
        EditText p2 = (EditText) findViewById(R.id.p2);
        EditText ro = (EditText) findViewById(R.id.rounds);
        ImageButton img = (ImageButton) findViewById(R.id.home);
        si.setVisibility(View.INVISIBLE);
        mu.setVisibility(View.INVISIBLE);
        or.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        ro.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        pno = 2;
    }

    public void play(View view) {
        flag = 3;
        EditText p1 = (EditText) findViewById(R.id.p1);
        EditText ro = (EditText) findViewById(R.id.rounds);
        name1 = p1.getText().toString();
        if (pno == 2) {
            EditText p2 = (EditText) findViewById(R.id.p2);
            name2 = p2.getText().toString();
        } else name2 = "AI";
        String rou = "";
        rou = ro.getText().toString();
        String z = "";
        if (name1.equals(z) || name2.equals(z) || rou.equals(z)) {
            Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        } else {
            rno = Integer.parseInt(rou);
            if (rno == 0) {
                Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
            } else {
                setContentView(R.layout.single);
                player = 1;
                sc1 = 0;
                sc2 = 0;
                round = 1;
                TextView s1 = (TextView) findViewById(R.id.s1);
                TextView s2 = (TextView) findViewById(R.id.s2);
                ch = (TextView) findViewById(R.id.chance);
                String n1 = name1 + "'s Score:" + sc1;
                String n2 = name2 + "'s Score:" + sc2;
                chance = name1 + "'s Chance";
                s1.setText(n1);
                s2.setText(n2);
                ch.setText(chance);
                player = 1;
                sc1 = 0;
                sc2 = 0;
                round = 1;
            }
        }
    }

    public void stone(View view) {
        if (pno == 2) {
            if (player == 1) {
                pl1 = 1;
                player++;
                chance = name2 + "'s Chance";
                ch.setText(chance);
            } else {
                pl2 = 1;
                round++;
                evaluate();
                player--;
            }
        } else {
            pl1 = 1;
            int[] cho = {1, 4};
            Random rnd = new Random();
            int j = rnd.nextInt(2);
            if (j == 0) {
                int i = rnd.nextInt(2);
                pl2 = cho[i];
            } else pl2 = 2;
            TextView choice = (TextView) findViewById(R.id.ai);
            choice.setVisibility(View.VISIBLE);
            String p = "AI's Choice:" + choose();
            choice.setText(p);
            round++;
            evaluate();
        }
    }

    public String choose() {
        if (pl2 == 1) return "Stone";
        else if (pl2 == 2) return "Paper";
        else if (pl2 == 4) return "Scissor";
        return "";
    }

    public void paper(View view) {
        if (pno == 2) {
            if (player == 1) {
                pl1 = 2;
                chance = name2 + "'s Chance";
                ch.setText(chance);
                player++;
            } else {
                pl2 = 2;
                player--;
                round++;
                evaluate();

            }
        } else {
            pl1 = 2;
            int[] cho = {1, 2};
            Random rnd = new Random();
            int j = rnd.nextInt(2);
            if (j == 0) {
                int i = rnd.nextInt(2);
                pl2 = cho[i];
            } else pl2 = 4;
            TextView choice = (TextView) findViewById(R.id.ai);
            choice.setVisibility(View.VISIBLE);
            String p = "AI's Choice:" + choose();
            choice.setText(p);
            round++;
            evaluate();
        }
    }

    public void sci(View view) {
        if (pno == 2) {

            if (player == 1) {
                pl1 = 4;
                player++;
                chance = name2 + "'s Chance";
                ch.setText(chance);
            } else {
                pl2 = 4;
                round++;
                evaluate();
                player--;
            }
        } else {
            pl1 = 4;
            int[] cho = {2, 4};
            Random rnd = new Random();
            int j = rnd.nextInt(2);
            if (j == 0) {
                int i = rnd.nextInt(2);
                pl2 = cho[i];
            } else pl2 = 1;
            TextView choice = (TextView) findViewById(R.id.ai);
            choice.setVisibility(View.VISIBLE);
            String p = "AI's Choice:" + choose();
            choice.setText(p);
            round++;
            evaluate();
        }
    }

    public void evaluate() {
        int dif = pl1 - pl2;
        TextView s1 = (TextView) findViewById(R.id.s1);
        TextView s2 = (TextView) findViewById(R.id.s2);
        if (Math.abs(dif) == 1) {
            if (dif > 0) {
                sc1++;
                st1 = name1 + "'s Score:" + sc1;
                s1.setText(st1);
            } else {
                sc2++;
                st2 = name2 + "'s Score:" + sc2;
                s2.setText(st2);
            }
        } else if (Math.abs(dif) == 2) {
            if (dif > 0) {
                sc1++;
                st1 = name1 + "'s Score:" + sc1;
                s1.setText(st1);
            } else {
                sc2++;
                st2 = name2 + "'s Score:" + sc2;
                s2.setText(st2);
            }
        } else if (Math.abs(dif) == 3) {
            if (dif < 0) {
                sc1++;
                st1 = name1 + "'s Score:" + sc1;
                s1.setText(st1);
            } else {
                sc2++;
                st2 = name2 + "'s Score:" + sc2;
                s2.setText(st2);
            }
        }
        String r = "Round:";
        TextView rou = (TextView) findViewById(R.id.rno);
        if ((round - 1) < rno) {
            r = r + (round);
            rou.setText(r);
            chance = name1 + "'s Chance";
            ch.setText(chance);
        } else {
            result();
        }

    }
    public void result(){
        TextView res = (TextView) findViewById(R.id.result);
        ImageButton i1 = (ImageButton) findViewById(R.id.st);
        ImageButton i2 = (ImageButton) findViewById(R.id.pa);
        ImageButton i3 = (ImageButton) findViewById(R.id.sc);
        i1.setClickable(false);
        i2.setClickable(false);
        i3.setClickable(false);
        res.setVisibility(View.VISIBLE);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            assert vibrator != null;
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        if (sc1 > sc2) {
            s = name1 + " Won";
            res.setText(s);
        } else if (sc1 < sc2) {
            s = name2 + " Won";
            res.setText(s);
        } else {
            s = "Match Tied";
            res.setText(s);
        }

    }
}

