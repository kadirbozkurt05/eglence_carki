package com.example.fortunewheel;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.fortunewheel.databinding.ActivityThirdBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
public class ThirdActivity extends AppCompatActivity {
    private ActivityThirdBinding binding;
    FirebaseFirestore db;
    String lastItem;
    ArrayList<String> links;
    ArrayList<String > lastList;
    ArrayList<String> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        lastList= new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        links = intent.getStringArrayListExtra("links");
        images = intent.getStringArrayListExtra("images");
        lastItem = intent.getStringExtra("data");
        lastList = intent.getStringArrayListExtra("lastList");


        binding.viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, binding.viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(500, 5000L);

        for (int i =0;i<lastList.size();i++){
            if (lastItem.matches(lastList.get(i))){
                Picasso.get().load(images.get(i)).into(binding.imageView2);
                binding.textView.setText(lastItem);

                Animation sgAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_grow);
                binding.textView.startAnimation(sgAnimation);
                int finalI = i;
                CountDownTimer countDownTimer = new CountDownTimer(6000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                        Toast.makeText(ThirdActivity.this, "ZİYERET ETMEK İÇİN RESME TIKLAYABİLİRSİNİZ!", Toast.LENGTH_LONG).show();

                       /* Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                        openURL.setData(Uri.parse(links.get(finalI)));
                        startActivity(openURL);*/
                    }
                }.start();

                binding.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                        openURL.setData(Uri.parse(links.get(finalI)));
                        startActivity(openURL);
                    }
                });

                binding.imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                        openURL.setData(Uri.parse(links.get(finalI)));
                        startActivity(openURL);
                    }
                });



            }
        }
    }


    }



