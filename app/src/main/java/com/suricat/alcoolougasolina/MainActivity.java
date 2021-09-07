package com.suricat.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText txtEthanolPrice;
    private TextInputEditText txtGasPrice;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEthanolPrice = findViewById(R.id.txtEthanolPrice);
        txtGasPrice = findViewById(R.id.txtGasPrice);
        txtResult = findViewById(R.id.txtResult);
    }

    public void calculate(View v) {
        dismissKeyboard();

        boolean isFilled = validateFields();
        if (!isFilled) {
            setResultText("Por favor preencha os campos corretamente");
            return;
        }

        Double gasPrice = Double.parseDouble(txtGasPrice.getText().toString());
        Double ethanolPrice = Double.parseDouble(txtEthanolPrice.getText().toString());

        if (ethanolPrice / gasPrice >= 0.7) {
            setResultText("É melhor utilizar gasolina!");
        } else {
            setResultText("É melhor utilizar álcool!");
        }
    }

    private void dismissKeyboard() {
        txtEthanolPrice.clearFocus();
        txtGasPrice.clearFocus();

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtGasPrice.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(txtEthanolPrice.getWindowToken(), 0);
    }

    private void setResultText(String text) {
        txtResult.setText(text);
    }

    private boolean validateFields () {
        try{
            String valueEthanol = txtEthanolPrice.toString();
            String valueGas = txtGasPrice.getText().toString();

            return !valueEthanol.equals("") && !valueGas.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
