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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] shapes = {"Circle", "Square", "Triangle", "Rectangle", "Ellipse"};
    String[] colors = {"Red", "Blue", "Green", "Pink", "Yellow"};

    private Spinner shapesSpinner;
    private Spinner colorsSpinner;
    private Button submitButton;
    private LinearLayout displayLayout;

    private ArrayAdapter<String> colorsAdapter; // Fixed colors adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        shapesSpinner = findViewById(R.id.shapesSpinner);
        colorsSpinner = findViewById(R.id.colorsSpinner);
        submitButton = findViewById(R.id.submitButton);
        displayLayout = findViewById(R.id.displayLayout);
        TextView displayTextView = new TextView(this);

        // Set up spinners
        ArrayAdapter<String> shapesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shapes);
        colorsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colors);

        shapesSpinner.setAdapter(shapesAdapter);
        colorsSpinner.setAdapter(colorsAdapter);

        // Set listeners
        shapesSpinner.setOnItemSelectedListener(this);

        // Set up button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySelectedValues();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//         Handle item selection (if needed)
        String selectedShape = shapesSpinner.getSelectedItem().toString();

        // Update color options based on the selected shape
        updateColorsBasedOnShape(selectedShape);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Handle no item selected (if needed)
    }

    private void displaySelectedValues() {
        String selectedShape = shapesSpinner.getSelectedItem().toString();
        String selectedColor = colorsSpinner.getSelectedItem().toString();

        // Create an Intent to start ResultActivity
        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        // Pass the selected shape and color data as extras
        intent.putExtra("SELECTED_SHAPE", selectedShape);
        intent.putExtra("SELECTED_COLOR", selectedColor);

        // Start the ResultActivity
        startActivity(intent);
    }

    private void updateColorsBasedOnShape(String selectedShape) {
        // Create a new ArrayAdapter for colors
        ArrayAdapter<String> newColorsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        // Update color options based on the selected shape
        if (selectedShape.equals("Triangle")) {
            // Allow only Red and Blue for Triangle
            newColorsAdapter.addAll("Red", "Blue");
        } else if (selectedShape.equals("Circle")) {
            // Allow only Blue and Green for Circle
            newColorsAdapter.addAll("Blue", "Green");
        } else if (selectedShape.equals("Square")) {
            // Allow only Green and Pink for Square
            newColorsAdapter.addAll("Green", "Pink");
        } else if (selectedShape.equals("Rectangle")) {
            // Allow only Pink and Yellow for Rectangle
            newColorsAdapter.addAll("Pink", "Yellow");
        } else if (selectedShape.equals("Ellipse")) {
            // Allow only Yellow and Red for Ellipse
            newColorsAdapter.addAll("Yellow", "Red");
        }

        // Set the new adapter for the colorsSpinner
        colorsSpinner.setAdapter(newColorsAdapter);
    }

}

