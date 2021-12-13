package com.example.fortunewheel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import com.example.fortunewheel.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     private ActivityMainBinding binding;

    FirebaseFirestore db ;
    String[] sectorList;
    Intent intent;
    String name;
    int size;
    ArrayList<String> firstWheelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        name ="";
        Resources res = getResources();
        sectorList = res.getStringArray(R.array.sectorsList);
        firstWheelList = new ArrayList<>();
        binding.button2.setVisibility(View.GONE);
        db = FirebaseFirestore.getInstance();
        intent = new Intent(MainActivity.this,SecondActivity.class);
        size=0;
       getList();
    }

   public void getList(){
        db.collection("WheelLists").document("WheelLists").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                Map<String,Object> data = value.getData();
                firstWheelList = (ArrayList<String>) data.get("WheelLists");
                size = firstWheelList.size();



                if (size>=1){
                    binding.button.setVisibility(View.VISIBLE);
                    binding.textView.setVisibility(View.VISIBLE);
                    binding.textView.setText(firstWheelList.get(0).toString());

                }
                if (size>1){
                    binding.button2.setVisibility(View.VISIBLE);
                    binding.textView2.setVisibility(View.VISIBLE);
                    binding.textView2.setText(firstWheelList.get(1).toString());

                }
                if (size>2){
                    binding.button3.setVisibility(View.VISIBLE);
                    binding.textView3.setVisibility(View.VISIBLE);
                    binding.textView3.setText(firstWheelList.get(2).toString());

                }
                if (size>3){
                    binding.button4.setVisibility(View.VISIBLE);
                    binding.textView4.setVisibility(View.VISIBLE);
                    binding.textView4.setText(firstWheelList.get(3).toString());

                }
                if (size>4){
                    binding.button5.setVisibility(View.VISIBLE);
                    binding.textView5.setVisibility(View.VISIBLE);
                    binding.textView5.setText(firstWheelList.get(4).toString());

                }
                if (size>5){
                    binding.button6.setVisibility(View.VISIBLE);
                    binding.textView6.setVisibility(View.VISIBLE);
                    binding.textView6.setText(firstWheelList.get(5).toString());

                }
                if (size>6){
                    binding.button7.setVisibility(View.VISIBLE);
                    binding.textView7.setVisibility(View.VISIBLE);
                    binding.textView7.setText(firstWheelList.get(6).toString());

                }
                if (size>7){
                    binding.button8.setVisibility(View.VISIBLE);
                    binding.textView8.setVisibility(View.VISIBLE);
                    binding.textView8.setText(firstWheelList.get(7).toString());

                }
                if (size>8){
                    binding.button9.setVisibility(View.VISIBLE);
                    binding.textView9.setVisibility(View.VISIBLE);
                    binding.textView9.setText(firstWheelList.get(8).toString());

                }
                if (size>9){
                    binding.button10.setVisibility(View.VISIBLE);
                    binding.textView10.setVisibility(View.VISIBLE);
                    binding.textView10.setText(firstWheelList.get(9).toString());

                }
            }
        });
    }

    public void firstButtonClicked(View view){
        sendDataToSecondActivity(0);
    }
    public void secondButtonClicked(View view){
        sendDataToSecondActivity(1);
    }
    public void thirdButtonClicked(View view){
        sendDataToSecondActivity(2);
    }
    public void fourthButtonClicked(View view){
        sendDataToSecondActivity(3);
    }
    public void fifthButtonClicked(View view){
        sendDataToSecondActivity(4);
    }
    public void sixthButtonClicked(View view){
        sendDataToSecondActivity(5);
    }
    public void seventhButtonClicked(View view){
        sendDataToSecondActivity(6);
    }
    public void eightButtonClicked(View view){
        sendDataToSecondActivity(7);
    }
    public void ninethButtonClicked(View view){
        sendDataToSecondActivity(8);
    }
    public void tenthButtonClicked(View view){
        sendDataToSecondActivity(9);
    }

    public void sendDataToSecondActivity(int i){
        intent.putExtra("data",sectorList[i]);
        startActivity(intent);
        System.out.println(sectorList[i]);
    }
}