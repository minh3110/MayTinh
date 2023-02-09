package com.example.maytinh1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTextView;
    //
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Xử lý các thay đổi giao diện
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnEqual = findViewById(R.id.btnEqual);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                resultTextView.append("1");
                break;
            case R.id.btn2:
                resultTextView.append("2");
                break;
            case R.id.btn3:
                resultTextView.append("3");
                break;
            case R.id.btn4:
                resultTextView.append("4");
                break;
            case R.id.btn5:
                resultTextView.append("5");
                break;
            case R.id.btn6:
                resultTextView.append("6");
                break;
            case R.id.btn7:
                resultTextView.append("7");
                break;
            case R.id.btn8:
                resultTextView.append("8");
                break;
            case R.id.btn9:
                resultTextView.append("9");
                break;
            case R.id.btn0:
                resultTextView.append("0");
                break;
            case R.id.btnClear:
                resultTextView.setText("");
                break;
            case R.id.btnPlus:
                resultTextView.append("+");
                break;
            case R.id.btnMinus:
                resultTextView.append("-");
                break;
            case R.id.btnMultiply:
                resultTextView.append("*");
                break;
            case R.id.btnDivide:
                resultTextView.append("/");
                break;
            case R.id.btnEqual:
                try {
                    double result = eval(resultTextView.getText().toString());
                    resultTextView.setText(String.valueOf(result));
                } catch (Exception e) {
                    resultTextView.setText("Error");
                }
                break;
            default:
                break;
        }
    }
    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) {
                    eatChar();
                }
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) {
                    throw new RuntimeException("Unexpected: " + (char)c);
                }
                return v;
            }

            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') {
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') {
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') {
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') {
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '+' || c == '-') {
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
                if (c == '(') {
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else {
                    int startIndex = this.pos;
                    while ((c >= '0' && c <= '9') || c == '.') {
                        eatChar();
                    }
                    if (pos == startIndex) {
                        throw new RuntimeException("Unexpected: " + (char)c);
                    }
                    v = Double.parseDouble(str.substring(startIndex, pos));
                }

                eatSpace();
                if (c == '^') {
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) {
                    v = -v;
                }
                return v;
            }
        }
        return new Parser().parse();
    }
    }
