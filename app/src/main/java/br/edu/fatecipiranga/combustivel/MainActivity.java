package br.edu.fatecipiranga.combustivel;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    private TextView gasPriceTextView;
    private TextView ethanolPriceTextView;
    private Collection<View> ethanolResult = new Vector<>(2);
    private Collection<View> gasResult = new Vector<>(2);

    private double gasPrice;
    private double ethanolPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasPriceTextView = findViewById(R.id.gasPriceTextView);
        ethanolPriceTextView = findViewById(R.id.ethanolPriceTextView);
        ethanolResult.add(findViewById(R.id.resultEthanolTextInputLayout));
        ethanolResult.add(findViewById(R.id.imageEthanolView));
        gasResult.add(findViewById(R.id.resultGasTextInputLayout));
        gasResult.add(findViewById(R.id.imageGasView));

        ethanolResult.forEach(v -> v.setVisibility(View.INVISIBLE));
        gasResult.forEach(v -> v.setVisibility(View.INVISIBLE));

        SeekBar gasSeekBar = findViewById(R.id.gasSeekBar);
        SeekBar ethanolSeekBar = findViewById(R.id.ethanolSeekBar);

        gasSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasPrice = progress / 100d;
                gasPriceTextView.setText(currencyFormat.format(gasPrice));
                if (ethanolPrice > 0)
                    calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ethanolSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ethanolPrice = progress / 100d;
                ethanolPriceTextView.setText(currencyFormat.format(ethanolPrice));
                if (gasPrice > 0)
                    calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void calculate() {
        if (ethanolPrice / gasPrice >= .7) {
            ethanolResult.forEach(v -> v.setVisibility(View.INVISIBLE));
            gasResult.forEach(v -> v.setVisibility(View.VISIBLE));
        } else {
            ethanolResult.forEach(v -> v.setVisibility(View.VISIBLE));
            gasResult.forEach(v -> v.setVisibility(View.INVISIBLE));
        }
    }
}