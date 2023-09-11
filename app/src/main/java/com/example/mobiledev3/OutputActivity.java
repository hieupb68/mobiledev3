package com.example.mobiledev3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        // Get the selected shape and color data from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String selectedShape = extras.getString("SELECTED_SHAPE");
            String selectedColor = extras.getString("SELECTED_COLOR");

            // Display the selected shape and color in the output layout
            TextView shapeTextView = findViewById(R.id.shapeTextView);
            TextView colorTextView = findViewById(R.id.colorTextView);

            shapeTextView.setText("Selected Shape: " + selectedShape);
            colorTextView.setText("Selected Color: " + selectedColor);
        }
    }
}
