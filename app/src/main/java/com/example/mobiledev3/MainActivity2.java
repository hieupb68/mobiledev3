package com.example.mobiledev3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] shapes = {"Select Shape", "Circle", "Square", "Triangle", "Rectangle", "Ellipse"};
    String[] colors = {"Select Color", "Red", "Blue", "Green", "Pink", "Yellow"};

    private Spinner shapesSpinner;
    private Spinner colorsSpinner;
    private LinearLayout displayLayout;
    private TextView displayTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        shapesSpinner = findViewById(R.id.shapesSpinner);
        colorsSpinner = findViewById(R.id.colorsSpinner);
        displayLayout = findViewById(R.id.displayLayout);
        displayTextView = new TextView(this);

        // Set up spinners
        ArrayAdapter<String> shapesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shapes);
        ArrayAdapter<String> colorsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colors);

        shapesSpinner.setAdapter(shapesAdapter);
        colorsSpinner.setAdapter(colorsAdapter);

        // Set listeners
        shapesSpinner.setOnItemSelectedListener(this);
        colorsSpinner.setOnItemSelectedListener(this);

        // Find the Submit button by its ID
        Button submitButton = findViewById(R.id.submitButton);

        // Set the visibility of the button to View.GONE to hide it
        submitButton.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // Get the selected shape and color
        String selectedShape = shapesSpinner.getSelectedItem().toString();
        String selectedColor = colorsSpinner.getSelectedItem().toString();

        // Check if both a shape and a color are selected
        if (!selectedShape.equals("Select Shape") && !selectedColor.equals("Select Color")) {
            // Display the result immediately
            // Create an Intent to start ResultActivity
            Intent intent = new Intent(MainActivity2.this, OutputActivity.class);

            // Pass the selected shape and color data as extras
            intent.putExtra("SELECTED_SHAPE", selectedShape);
            intent.putExtra("SELECTED_COLOR", selectedColor);

            // Start the ResultActivity
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Handle no item selected (if needed)
    }

    private void displaySelectedValues(String selectedShape, String selectedColor) {
        String displayText = "Selected Shape: " + selectedShape + "\nSelected Color: " + selectedColor;
        displayTextView.setText(displayText);

        // Clear existing views and add the new TextView
        displayLayout.removeAllViews();
        displayLayout.addView(displayTextView);
    }
}
