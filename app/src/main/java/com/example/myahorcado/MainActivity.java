package com.example.myahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> lista = Palabras.getPalabras();
    String correcta;
    int oportunidades = 5;
    TextView textView_nIntentos, textView_palabraOculta, estado;
    boolean enJuego = false;
    List<String> caractersCorrectos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_nIntentos = findViewById(R.id.textView_nIntentos);
        textView_palabraOculta = findViewById(R.id.textView_palabraOculta);
        estado = findViewById(R.id.estado);

    }

    public void Verificar(View view) {
        if ( enJuego ){
            textView_palabraOculta.setText("");
            if ( oportunidades >= 1 ){
                Button button = (Button) view;
                String textoBoton = button.getText().toString();

                if ( correcta.contains(textoBoton)){
                    estado.setText("Letra correcta");
                    caractersCorrectos.add(textoBoton);


                    for ( int i = 0; i < correcta.length(); i++ ){
                        String temporal = textView_palabraOculta.getText().toString();

                        for (int j = 0; j < caractersCorrectos.size(); j++ ){
                            if ( (correcta.charAt(i) == caractersCorrectos.get(j).charAt(0)) ){
                                textView_palabraOculta.setText(temporal + caractersCorrectos);
                            }else{
                                textView_palabraOculta.setText(temporal + "-");
                            }
                        }
                    }

                }else{
                    oportunidades--;
                    textView_nIntentos.setText(" "+oportunidades);
                    estado.setText("Letra incorrecta");
                }
            }else{
                enJuego = false;
                Toast.makeText(MainActivity.this, "Inicie un nuevo juego", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void empezar(View view) {
        try {
            oportunidades = 5;
            enJuego = true;

            textView_nIntentos.setText(""+oportunidades);
            textView_palabraOculta.setText("");

            int aleatorio = (int) (( Math.random() * lista.size() ));
            correcta = lista.get(aleatorio);


            caractersCorrectos.clear();
            Toast.makeText(MainActivity.this, "Aleat: " + correcta, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
