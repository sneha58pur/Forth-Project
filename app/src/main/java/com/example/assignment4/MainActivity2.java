

package com.example.assignment4;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Get strings from resources
        String[] title = getResources().getStringArray(R.array.tech_array);
        String[] subtitle = {
                "price:200", "price:300",
                "price:230", "price:240", "price:500",
        };

        // Image resources for the list items
        Integer[] imgid = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground };

        // Set the adapter
        TechAdapter adapter = new TechAdapter(this, title, subtitle, imgid);
        listView.setAdapter(adapter);
    }
}