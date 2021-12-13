package com.example.fortunewheel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import com.adefruandta.spinningwheel.SpinningWheelView;
import com.example.fortunewheel.databinding.ActivitySecondBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    Intent intent;
    Intent intent1;
    String sectorData;
    String[] wholeList;
    String[] sectorList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
          db = FirebaseFirestore.getInstance();
          binding.wheel2.setEnabled(false);
         Resources res = getResources();
         sectorList = res.getStringArray(R.array.sectorsList);
        wholeList= res.getStringArray(R.array.wholeList);

        intent = getIntent();
        intent1 = new Intent(SecondActivity.this,ThirdActivity.class);
        sectorData= intent.getStringExtra("data");
        getData();


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.wheel2.rotate(500,7000,50);
            }
        });

        binding.wheel2.setOnRotationListener(new SpinningWheelView.OnRotationListener() {
            @Override
            public void onRotation() {
                binding.button.setEnabled(false);
                MediaPlayer song = MediaPlayer.create(SecondActivity.this, R.raw.wheelsound);
                song.start();

            }

            @Override
            public void onStopRotation(Object item) {

                MediaPlayer song = MediaPlayer.create(SecondActivity.this, R.raw.celebrate);
                song.start();

                CountDownTimer countDownTimer = new CountDownTimer(1500,1500) {
                    @Override
                    public void onTick(long l) {
                    }

                    @Override
                    public void onFinish() {
                        intent1.putExtra("data",item.toString());
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                    }
                }.start();
            }
        });
    }
    public void getDataTest(String a){
        db.collection("SecondWheelList").document(a).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Map<String,Object> data = value.getData();
                ArrayList<String > sectors = (ArrayList<String>) data.get(a);
                ArrayList<String > links = (ArrayList<String>) data.get("links");
                ArrayList<String> images = (ArrayList<String>) data.get("images");
                intent1.putStringArrayListExtra("links",links);
                intent1.putStringArrayListExtra("lastList",sectors);
                intent1.putStringArrayListExtra("images",images);
                binding.wheel2.setItems(sectors);

            }
        });
    }
    public void getData(){
        for (int i =0;i<10;i++){
            if (sectorData.matches(sectorList[i])){
                getDataTest(wholeList[i]);
            }
        }
    }
}