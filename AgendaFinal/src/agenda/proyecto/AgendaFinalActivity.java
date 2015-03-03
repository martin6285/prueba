package agenda.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AgendaFinalActivity extends Activity {
	//Declaraciones de Tipos
	private Button boton_nota;
	private Button boton_listado;
	private Button boton_acercade;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        crearListado();
        crearEventoCalendario();
        mostrarToast();
    }
    
    private void mostrarToast() {
    	this.boton_acercade = (Button) findViewById(R.id.button1);
         this.boton_acercade.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lanzarAcercaDe();
			}
		});
    }
    
    private void crearEventoCalendario() {
    	this.boton_nota = (Button) findViewById(R.id.boton_nota);
         this.boton_nota.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				crearNotaCalendario();

			}
		});
    }
    
    private void crearListado() {
    	this.boton_listado = (Button) findViewById(R.id.button3);
         this.boton_listado.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				  lanzarListado();
			}
		});
    }
    
    public void lanzarListado() { 
  	  Intent i = new Intent(this, Notepad.class); 
  	  startActivity(i); 
  	}      
    
    public void lanzarAcercaDe() {
    	Intent i = new Intent (this, AcercaDe.class);
    	startActivity(i);
    	
    }   
    
    private void crearNotaCalendario() {
    	Intent l_intent = new Intent(Intent.ACTION_EDIT);
    	//utilizamos la api de calendario proporcionada por android
    	l_intent.setType("vnd.android.cursor.item/event");
    	l_intent.putExtra("Asunto", "Asunto");
    	l_intent.putExtra("Descripcion", "Introduce tu descripcion");
    	l_intent.putExtra("Lugar", "@home");
    	l_intent.putExtra("Fecha Inicio", System.currentTimeMillis());
    	l_intent.putExtra("Fecha Final", System.currentTimeMillis() + 1800*1000);
    	l_intent.putExtra("Todo el dia", 0);
    	//status: 0~ tentative; 1~ confirmed; 2~ canceled
    	l_intent.putExtra("eventStatus", 1);
    	//0~ default; 1~ confidential; 2~ private; 3~ public
    	l_intent.putExtra("visibility", 0);
    	l_intent.putExtra("transparency", 0);
    	//0~ false; 1~ true
    	l_intent.putExtra("Activar Alarma", 1);
    	try {
    		startActivity(l_intent);
    	} catch (Exception e) {
    		Toast.makeText(this.getApplicationContext(), "Error, no se ha encontrado un calendario compatible!", Toast.LENGTH_LONG).show();
    	}
    }
}