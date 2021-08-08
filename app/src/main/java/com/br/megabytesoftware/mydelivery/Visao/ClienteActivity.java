package com.br.megabytesoftware.mydelivery.Visao;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.br.megabytesoftware.mydelivery.Controle.ClienteControle;
import com.br.megabytesoftware.mydelivery.Modelo.ClienteModelo;
import com.br.megabytesoftware.mydelivery.R;
import com.br.megabytesoftware.mydelivery.Util.MaskEditTextChangedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    //Váriavel guarda valor editar ou salvar
    private int flag=1;
    private String dataAtual;
    private String idClienteClicada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_teladashboard_cliente);

        //Inicio código pego data atual para salvar quando foi criado e/ou atualizado cliente
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date hoje = new Date();
        dataAtual = df.format(hoje);
        //Fim código pego data atual para salvar quando foi criado e/ou atualizado cliente
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activitycliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search_consulta_cliente:
                return true;
            case R.id.newCliente:
                DialogNovoEditarCliente();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Inicio código fonte para cadastrar o cliente
    public void DialogNovoEditarCliente() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ClienteActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_cliente, null);

        //Inicio da mascara telefone
        EditText telefoneCliente = (EditText) dialogView.findViewById(R.id.LayoutTelefoneCliente);
        MaskEditTextChangedListener mascaraTelefone = new MaskEditTextChangedListener("(##)####-####", telefoneCliente);
        telefoneCliente.addTextChangedListener(mascaraTelefone);
        //Fim da mascara telefone

        //Inicio da mascara celular
        EditText celularCliente = (EditText) dialogView.findViewById(R.id.LayoutCelularCliente);
        MaskEditTextChangedListener mascaraCelular = new MaskEditTextChangedListener("(##)#.####-####", celularCliente);
        celularCliente.addTextChangedListener(mascaraCelular);
        //Fim da mascara celular

        //Inicio da mascara cpf
        EditText cpfCliente = (EditText) dialogView.findViewById(R.id.LayoutCPFCliente);
        MaskEditTextChangedListener mascaraCPF = new MaskEditTextChangedListener("###.###.###-##", cpfCliente);
        cpfCliente.addTextChangedListener(mascaraCPF);
        //Fim da mascara cpf

        final EditText editTextNomeCliente = (EditText) dialogView.findViewById(R.id.LayoutNomeCliente);
        final EditText editTextCPFCliente = (EditText) dialogView.findViewById(R.id.LayoutCPFCliente);
        final EditText editTextEnderecoCliente = (EditText) dialogView.findViewById(R.id.LayoutEnderecoCliente);
        final EditText editTextNumeroEnderecoCliente = (EditText) dialogView.findViewById(R.id.LayoutNumeroEnderecoCliente);
        final EditText editTextBairroCliente = (EditText) dialogView.findViewById(R.id.LayoutBairroCliente);
        final Spinner spinnerUFCliente = (Spinner) dialogView.findViewById(R.id.LayoutUFCliente);
        final EditText editTextCidadeCliente = (EditText) dialogView.findViewById(R.id.LayoutCidadeCliente);
        final EditText editTextTelefoneCliente = (EditText) dialogView.findViewById(R.id.LayoutTelefoneCliente);
        final EditText editTextCelularCliente = (EditText) dialogView.findViewById(R.id.LayoutCelularCliente);
        final EditText editTextEmailCliente = (EditText) dialogView.findViewById(R.id.LayoutEmailCliente);

        //Inicio do código fonte pupular combobox com as siglas do UF
        List<String> list = new ArrayList<String>();
        list.add("AC");
        list.add("AL");
        list.add("AM");
        list.add("AL");
        list.add("BA");
        list.add("CE");
        list.add("DF");
        list.add("GO");
        list.add("MA");
        list.add("MT");
        list.add("MS");
        list.add("MG");
        list.add("PA");
        list.add("PB");
        list.add("PE");
        list.add("PI");
        list.add("PR");
        list.add("RJ");
        list.add("RN");
        list.add("RS");
        list.add("RO");
        list.add("RR");
        list.add("SC");
        list.add("SE");
        list.add("SP");
        list.add("TO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUFCliente.setAdapter(dataAdapter);
        //Final do código fonte pupular combobox com as siglas do UF

        if (flag == 2) {
            final ClienteControle clienteController = new ClienteControle(getApplicationContext());
            final ClienteModelo modeloCliente = clienteController.buscaInformacaoCliente(Integer.parseInt(idClienteClicada));

            editTextNomeCliente.setText(modeloCliente.getNomeCli());
            editTextCPFCliente.setText(modeloCliente.getCpfCli());
            editTextEnderecoCliente.setText(modeloCliente.getEnderecoCli());
            editTextNumeroEnderecoCliente.setText(modeloCliente.getNumeroEnderecoCli());
            editTextBairroCliente.setText(modeloCliente.getBairroCli());
            //spinnerUFCliente.setSelected(modeloCliente.getUfCli());
            editTextCidadeCliente.setText(modeloCliente.getCidadeCli());
            editTextTelefoneCliente.setText(modeloCliente.getTelefoneCli());
            editTextCelularCliente.setText(modeloCliente.getCelularCli());
            editTextEmailCliente.setText(modeloCliente.getEmailCli());
        }
        dialogBuilder.setTitle("NOVO CLIENTE");
        dialogBuilder.setIcon(R.drawable.dialogcliente_iconetela);
        dialogBuilder.setNegativeButton("CANCELAR", null);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setPositiveButton("SALVAR", null);
        dialogBuilder.setNeutralButton("BUSCAR ENDEREÇO", null);

        final AlertDialog mAlertDialog = dialogBuilder.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                Button e = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                e.setLeft(25);

                //Inicio do código fonte método botão salvar
                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setLeft(25);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clienteNome = editTextNomeCliente.getText().toString();
                        String clienteCPF = editTextCPFCliente.getText().toString();

                        if (flag == 1) {
                            if (clienteNome.equals("") || clienteCPF.equals("")) {
                                Toast.makeText(getApplicationContext(), "Informe um nome e CPF", Toast.LENGTH_SHORT).show();
                            } else {
                                //Regra de negócio para Incluir novos clientes
                                ClienteModelo modeloCliente = new ClienteModelo();
                                modeloCliente.setNomeCli(editTextNomeCliente.getText().toString());
                                modeloCliente.setCpfCli(editTextCPFCliente.getText().toString());
                                modeloCliente.setEnderecoCli(editTextEnderecoCliente.getText().toString());
                                modeloCliente.setNumeroEnderecoCli(editTextNumeroEnderecoCliente.getText().toString());
                                modeloCliente.setBairroCli(editTextBairroCliente.getText().toString());
                                modeloCliente.setUfCli(spinnerUFCliente.getSelectedItem().toString());
                                modeloCliente.setCidadeCli(editTextCidadeCliente.getText().toString());
                                modeloCliente.setTelefoneCli(editTextTelefoneCliente.getText().toString());
                                modeloCliente.setCelularCli(editTextCelularCliente.getText().toString());
                                modeloCliente.setEmailCli(editTextEmailCliente.getText().toString());
                                modeloCliente.setDataCadastroCli(dataAtual);
                                boolean incluirCliente = new ClienteControle(getApplicationContext()).salvarCliente(modeloCliente);
                                if (incluirCliente) {
                                    Toast.makeText(getApplicationContext(), "CLIENTE INCLUIDO", Toast.LENGTH_SHORT).show();
                                    //listarClientes();
                                } else {
                                    Toast.makeText(getApplicationContext(), "ERRO AO INCLUIR CLIENTE", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else if (flag == 2) {
                            //Regra de negócio para atualizar clientes
                            ClienteModelo modeloCliente = new ClienteModelo();
                            modeloCliente.setNomeCli(editTextNomeCliente.getText().toString());
                            modeloCliente.setCpfCli(editTextCPFCliente.getText().toString());
                            modeloCliente.setEnderecoCli(editTextEnderecoCliente.getText().toString());
                            modeloCliente.setNumeroEnderecoCli(editTextNumeroEnderecoCliente.getText().toString());
                            modeloCliente.setBairroCli(editTextBairroCliente.getText().toString());
                            modeloCliente.setUfCli(spinnerUFCliente.getSelectedItem().toString());
                            modeloCliente.setCidadeCli(editTextCidadeCliente.getText().toString());
                            modeloCliente.setTelefoneCli(editTextTelefoneCliente.getText().toString());
                            modeloCliente.setCelularCli(editTextCelularCliente.getText().toString());
                            modeloCliente.setEmailCli(editTextEmailCliente.getText().toString());
                            modeloCliente.setIdCli(Integer.parseInt(idClienteClicada));
                            modeloCliente.setDataAlteracaoCli(dataAtual);
                            boolean updateCliente = new ClienteControle(getApplicationContext()).atualizarCliente(modeloCliente);
                            if (updateCliente) {
                                Toast.makeText(getApplicationContext(), "CLIENTE ATUALIZADO", Toast.LENGTH_SHORT).show();
                                //listarClientes();
                                flag =1;
                            } else {
                                Toast.makeText(getApplicationContext(), "ERRO AO ATUALIZAR CLIENTE", Toast.LENGTH_SHORT).show();
                            }
                        }

                        dialog.dismiss();

                    }
                });
                //Fim do código fonte pupular combobox com as siglas do UF

                /*
                //Inicio chama buscar endereço do cliente pela localização do GPS
                Button c = mAlertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);

                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //BuscarLocalizacaoGPS();
                        editTextEnderecoCliente.setText(enderecoCli);
                        editTextNumeroEnderecoCliente.setText(numeroEnderecoCli);
                        editTextBairroCliente.setText(bairroCli);
                        editTextCidadeCliente.setText(cidadeCli);
                        EncontraUF(estadoCli);
                        spinnerUFCliente.setSelected(Boolean.parseBoolean(ufCli));

                    }
                });
                //Fim chama buscar endereço do cliente pela localização do GPS
                */
            }
        });
        mAlertDialog.show();

    }
    //Fim código fonte para cadastrar o cliente

}
