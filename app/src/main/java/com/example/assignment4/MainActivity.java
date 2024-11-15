package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> groupData;
    HashMap<String, List<String>> childData;
    int lastExpandedGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.go);

        // Set an OnClickListener for the button
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                // Create an Intent to navigate to the NextActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });


        expandableListView = findViewById(R.id.expandableListView);

        prepareData(); // Method to prepare the group and child data

        // Create and set the custom adapter for the ExpandableListView
        DestiAdapter adapter = new DestiAdapter(this, groupData, childData);
        expandableListView.setAdapter(adapter);

        // Group click listener (to show the group name in a toast)
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            String groupText = groupData.get(groupPosition);
            Toast.makeText(getApplicationContext(), groupText, Toast.LENGTH_SHORT).show();
            return false; // Return false to allow the group to expand or collapse
        });

        // Group collapse listener (to show which group was collapsed)
        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            String groupText = groupData.get(groupPosition);
            Toast.makeText(getApplicationContext(), groupText + " is Collapsed", Toast.LENGTH_SHORT).show();
        });

        // Child click listener (to show the child name in a toast)
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String childText = childData.get(groupData.get(groupPosition)).get(childPosition);
            Toast.makeText(getApplicationContext(), childText, Toast.LENGTH_SHORT).show();
            return false;
        });

        // Group expand listener (to collapse previously expanded group)
        expandableListView.setOnGroupExpandListener(groupPosition -> {
            if (lastExpandedGroup != -1 && lastExpandedGroup != groupPosition) {
                expandableListView.collapseGroup(lastExpandedGroup);
            }
            lastExpandedGroup = groupPosition;
        });
    }

    // Prepare data for groups (destinations) and children (places under each destination)
    private void prepareData() {
        // Example data
        String[] groupArray = getResources().getStringArray(R.array.destinations_array); // Load from resources (strings.xml)
        String[] childArray = getResources().getStringArray(R.array.dress_array); // Load from resources (strings.xml)

        groupData = new ArrayList<>();
        childData = new HashMap<>();

        // Loop through groups and add corresponding children
        for (String group : groupArray) {
            groupData.add(group); // Add destination to groupData

            // Add some example children for each group
            List<String> childList = new ArrayList<>();
            childList.add(childArray[0]); // You can dynamically add different places for each destination
            childData.put(group, childList); // Add children to corresponding group
        }
    }
}

