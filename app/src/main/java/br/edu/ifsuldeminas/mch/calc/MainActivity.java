package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonZero, buttonUm, buttonDois, buttonTres, buttonQuatro, buttonCinco,
            buttonSeis, buttonSete, buttonOito, buttonNove, buttonVirgula, buttonSoma,
            buttonSubtracao, buttonMultiplicacao, buttonDivisao, buttonPorcento, buttonDelete,
            buttonReset, buttonIgual;
    private TextView textViewResultado, textViewUltimaExpressao;
    private StringBuilder expressao = new StringBuilder();
    private double resultadoAnterior = 0.0;
    private boolean novoCalculo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonZero = findViewById(R.id.buttonZeroID);
        buttonUm = findViewById(R.id.buttonUmID);
        buttonDois = findViewById(R.id.buttonDoisID);
        buttonTres = findViewById(R.id.buttonTresID);
        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSete = findViewById(R.id.buttonSeteID);
        buttonOito = findViewById(R.id.buttonOitoID);
        buttonNove = findViewById(R.id.buttonNoveID);
        buttonVirgula = findViewById(R.id.buttonVirgulaID);
        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonDelete = findViewById(R.id.buttonDeleteID);
        buttonReset = findViewById(R.id.buttonResetID);
        buttonIgual = findViewById(R.id.buttonIgualID);
        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);


        configureButtonListeners();
    }

    private void configureButtonListeners() {
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("0");
            }
        });

        buttonUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("1");
            }
        });

        buttonDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("2");
            }
        });

        buttonTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("3");
            }
        });

        buttonQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("4");
            }
        });

        buttonCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("5");
            }
        });

        buttonSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("6");
            }
        });

        buttonSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("7");
            }
        });

        buttonOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("8");
            }
        });

        buttonNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("9");
            }
        });

        buttonVirgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue(".");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCalc();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetCalc();
            }
        });

        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcExpression();
            }
        });

        buttonSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("+");
            }
        });

        buttonSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("-");
            }
        });

        buttonMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("*");
            }
        });

        buttonDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("/");
            }
        });

        buttonPorcento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue("%");
            }
        });
    }

    private void addValue(String value) {

        if (novoCalculo) {
            expressao.setLength(0);
            textViewResultado.setText("");
            novoCalculo = false;
        }
        expressao.append(value);
        textViewResultado.append(value);
    }

    private void DeleteCalc() {
        if (expressao.length() > 0) {
            expressao.deleteCharAt(expressao.length() - 1);
            textViewResultado.setText(expressao.toString());
        }
    }

    private void ResetCalc() {
        expressao.setLength(0);
        resultadoAnterior = 0.0;
        textViewResultado.setText("0");
        textViewUltimaExpressao.setText("");
        novoCalculo = true;
    }

    private void CalcExpression() {
        Calculable avaliadorExpressao = null;
        try {
            avaliadorExpressao = new ExpressionBuilder(expressao.toString()).build();
            double resultado = avaliadorExpressao.calculate();
            textViewUltimaExpressao.setText(expressao.toString());
            textViewResultado.setText(String.valueOf(resultado));
            resultadoAnterior = resultado;
            novoCalculo = true;
        } catch (Exception e) {
          //  Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Não foi possivel calcular: " + e.getMessage());
        }
    }


    }
