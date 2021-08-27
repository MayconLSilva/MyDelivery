package com.br.megabytesoftware.mydelivery.Visao;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.megabytesoftware.mydelivery.Controle.ClienteControle;
import com.br.megabytesoftware.mydelivery.Controle.EntregaControle;
import com.br.megabytesoftware.mydelivery.Modelo.ClienteModelo;
import com.br.megabytesoftware.mydelivery.Modelo.EntregaModelo;
import com.br.megabytesoftware.mydelivery.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class EntregaActivity extends AppCompatActivity {


    private ListView listview;
    public ArrayList<String> arrayListServicos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_teladashboard_entregas);

        listview = (ListView) findViewById(R.id.lvEntrega);

        ListarEntregas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activityentrega, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.idfiltroGrafico) {
            //filtrarEntregas();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ListarEntregas(){

        List<EntregaModelo> listEntregas = new EntregaControle(getBaseContext()).listarEntregas();
        if (listEntregas.size() > 0){
            for (final EntregaModelo obj : listEntregas){
                int id = obj.getIdEntrega();

                arrayListServicos.add("ID " + obj.getIdEntrega() + " - " + "Data lanc: " + obj.getDataLancamentoEntrega() + "\n" +
                                      "Nome: " + obj.getNomeCliente() + " - " + "R$: " +  obj.getValorEntrega() + "\n" +
                                      "Endereço: " + obj.getEnderecoCliente() + ", Nº " + obj.getNumeroEnderecoCliente() + "\n"+
                                      "Bairro: " + obj.getBairroCliente() + "\n" +
                                      "Cidade: " + obj.getCidadeCliente() + "CEP: " + obj.getCepCliente() + "\n" +
                                      "Obs.: " + obj.getObsEntrega());

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListServicos);
        listview.setAdapter(adapter);
    }



}
