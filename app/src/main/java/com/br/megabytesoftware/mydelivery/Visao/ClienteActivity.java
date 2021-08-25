package com.br.megabytesoftware.mydelivery.Visao;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.br.megabytesoftware.mydelivery.Adapter.ArrayAdapterWithIcon;
import com.br.megabytesoftware.mydelivery.Controle.ClienteControle;
import com.br.megabytesoftware.mydelivery.Modelo.ClienteModelo;
import com.br.megabytesoftware.mydelivery.R;
import com.br.megabytesoftware.mydelivery.Util.MaskEditTextChangedListener;
import com.br.megabytesoftware.mydelivery.Util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    Utils utils;

    //Váriavel guarda valor editar ou salvar
    private int flag=1;
    private String dataAtual;
    private String idClienteClicada;
    private String celularClicado;
    private String enderecoClicado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_teladashboard_cliente);

        //Inicio código pego data atual para salvar quando foi criado e/ou atualizado cliente
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date hoje = new Date();
        dataAtual = df.format(hoje);
        //Fim código pego data atual para salvar quando foi criado e/ou atualizado cliente

        //Inicio do código chama o listar clientes
        listarClientes("");
        //Fim do código chama o listar clientes
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activitycliente, menu);

        //Inicio do menu item SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search_consulta_cliente)
                .getActionView();
        searchView.setQueryHint(getString(R.string.telaListaCliente_SearchClientes)); //Seta o hint do campo
        //Inicio do método fechou searchView
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //Se encerrar o searchView irá listar todos os clientes
                listarClientes("");

                return false;
            }
        });
        //Fim do método fechou searchView
        //Inicio do método pesquisar com searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Aqui chama o pesquisar se clicado na lupa
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                listarClientes(newText);

                return false;
            }
            //Fim do método pesquisar com searchView
        });
        //Fim do menu item SearchView

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

    //Inicio código fonte para cadastrar ou alterar o cliente
    public void DialogNovoEditarCliente() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ClienteActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_cliente, null);

        //Inicio da mascara telefone
        EditText telefoneCliente = (EditText) dialogView.findViewById(R.id.txtTelefoneCliente);
        MaskEditTextChangedListener mascaraTelefone = new MaskEditTextChangedListener("(##)####-####", telefoneCliente);
        telefoneCliente.addTextChangedListener(mascaraTelefone);
        //Fim da mascara telefone

        //Inicio da mascara celular
        EditText celularCliente = (EditText) dialogView.findViewById(R.id.txtCelularCliente);
        MaskEditTextChangedListener mascaraCelular = new MaskEditTextChangedListener("(##)#.####-####", celularCliente);
        celularCliente.addTextChangedListener(mascaraCelular);
        //Fim da mascara celular

        //Inicio da mascara cpf
        EditText cpfCliente = (EditText) dialogView.findViewById(R.id.txtCPF_CNPJCliente);
        MaskEditTextChangedListener mascaraCPF = new MaskEditTextChangedListener("###.###.###-##", cpfCliente);
        cpfCliente.addTextChangedListener(mascaraCPF);
        //Fim da mascara cpf

        final Button btnCancelar = (Button) dialogView.findViewById(R.id.btnCancelar);
        final Button btnSalvar = (Button) dialogView.findViewById(R.id.btnSalvar);
        final Button btnEndereco = (Button) dialogView.findViewById(R.id.btnEndereco);
        final EditText editTextNomeCliente = (EditText) dialogView.findViewById(R.id.txtNomeCliente);
        final EditText editTextCPFCliente = (EditText) dialogView.findViewById(R.id.txtCPF_CNPJCliente);
        final EditText editTexRGIECliente = (EditText) dialogView.findViewById(R.id.txtRG_IECliente);
        final EditText editTextEmailCliente = (EditText) dialogView.findViewById(R.id.txtEmailCliente);
        final EditText editTextTelefoneCliente = (EditText) dialogView.findViewById(R.id.txtTelefoneCliente);
        final EditText editTextCelularCliente = (EditText) dialogView.findViewById(R.id.txtCelularCliente);

        if(flag == 1)
            btnEndereco.setEnabled(false);

        if (flag == 2) {

            final ClienteControle clienteController = new ClienteControle(getApplicationContext());
            final ClienteModelo modeloCliente = clienteController.buscaInformacaoCliente(Integer.parseInt(idClienteClicada));

            editTextNomeCliente.setText(modeloCliente.getNomeCli());
            editTextCPFCliente.setText(modeloCliente.getCpfCli());
            editTextTelefoneCliente.setText(modeloCliente.getTelefoneCli());
            editTextCelularCliente.setText(modeloCliente.getCelularCli());
            editTextEmailCliente.setText(modeloCliente.getEmailCli());
        }

        dialogBuilder.setTitle("NOVO CLIENTE");
        if(flag != 1)
            dialogBuilder.setTitle("EDITAR CLIENTE");

        dialogBuilder.setIcon(R.drawable.dialogcliente_iconetela);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(dialogView);

        final AlertDialog mAlertDialog = dialogBuilder.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                //Inicio do evento do click botão cancelar
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = 1;
                        dialog.dismiss();

                    }
                });
                //Fim do evento do click botão cancelar

                //Inicio do evento do click salvar cliente
                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String clienteNome = editTextNomeCliente.getText().toString();
                        String clienteCPF = editTextCPFCliente.getText().toString();

                        if (flag == 1) {
                            if (clienteNome.equals("") || clienteCPF.equals("")) {
                                Toast.makeText(getApplicationContext(), "Informe um nome e CPF", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                //Regra de negócio para Incluir novos clientes
                                ClienteModelo modeloCliente = new ClienteModelo();
                                modeloCliente.setNomeCli(editTextNomeCliente.getText().toString());
                                modeloCliente.setCpfCli(editTextCPFCliente.getText().toString());
                                modeloCliente.setRgCli(editTexRGIECliente.getText().toString());
                                modeloCliente.setEmailCli(editTextEmailCliente.getText().toString());
                                modeloCliente.setTelefoneCli(editTextTelefoneCliente.getText().toString());
                                modeloCliente.setCelularCli(editTextCelularCliente.getText().toString());
                                modeloCliente.setDataCadastroCli(dataAtual);
                                boolean incluirCliente = new ClienteControle(getApplicationContext()).salvarCliente(modeloCliente);
                                if (incluirCliente) {
                                    Toast.makeText(getApplicationContext(), "CLIENTE INCLUIDO", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "ERRO AO INCLUIR CLIENTE", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else if (flag == 2) {
                            //Regra de negócio para atualizar clientes
                            ClienteModelo modeloCliente = new ClienteModelo();
                            modeloCliente.setNomeCli(editTextNomeCliente.getText().toString());
                            modeloCliente.setCpfCli(editTextCPFCliente.getText().toString());
                            modeloCliente.setRgCli(editTexRGIECliente.getText().toString());
                            modeloCliente.setTelefoneCli(editTextTelefoneCliente.getText().toString());
                            modeloCliente.setCelularCli(editTextCelularCliente.getText().toString());
                            modeloCliente.setEmailCli(editTextEmailCliente.getText().toString());
                            modeloCliente.setIdCli(Integer.parseInt(idClienteClicada));
                            modeloCliente.setDataAlteracaoCli(dataAtual);
                            boolean updateCliente = new ClienteControle(getApplicationContext()).atualizarCliente(modeloCliente);
                            if (updateCliente) {
                                Toast.makeText(getApplicationContext(), "CLIENTE ATUALIZADO", Toast.LENGTH_SHORT).show();
                                //listarClientes();
                                flag = 1;
                            } else {
                                Toast.makeText(getApplicationContext(), "ERRO AO ATUALIZAR CLIENTE", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialog.dismiss();
                        listarClientes("");
                    }
                });
                //Fim do evento do click salvar cliente
            }
        });
        mAlertDialog.show();
    }
    //Fim código fonte para cadastrar ou alterar o cliente

    //Inicio código fonte lista de clientes
    public void listarClientes(String value) {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosCliente);
        linearLayoutRecords.removeAllViews();

        List<ClienteModelo> modeloClientes = new ClienteControle(this).listarClientes(value);

        if (modeloClientes.size() > 0){
            for (final ClienteModelo obj : modeloClientes){
                int id = obj.getIdCli();
                String nomeCli = obj.getNomeCli();
                String cpfCli = obj.getCpfCli();
                String rgIeCli = obj.getRgCli();
                String celularCli = obj.getCelularCli();
                String telefoneCli = obj.getTelefoneCli();
                String emailCli = obj.getEmailCli();
                String dataTemp = obj.getDataCadastroCli();

                String textViewContents = "Nome: " + nomeCli + "\n" +
                        "CPF/CNPJ: " + cpfCli + "   RG/I.E" + rgIeCli +  "\n" +
                        "E-mail: " + emailCli +  "\n" +
                        "Celular: " + celularCli + " Telefone: ";

                TextView textViewClienteItem = new TextView(this);
                textViewClienteItem.setPadding(0, 10, 0, 10);
                textViewClienteItem.setText(textViewContents);
                textViewClienteItem.setTextColor(Color.parseColor("#000000"));
                textViewClienteItem.setTag(Integer.toString(id));
                linearLayoutRecords.addView(textViewClienteItem);


                //textViewClienteItem.setOnLongClickListener(new RetrieveOnLongClickListener());
                textViewClienteItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(getApplicationContext(),"Função não implementada nesta versão",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                //Inicio do método click no item da tabela
                textViewClienteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Pega valores e guarda nas váriaveis
                        idClienteClicada = v.getTag().toString();
                        celularClicado = obj.getCelularCli();
                        enderecoClicado = obj.getEnderecoCli() + ","+obj.getNumeroEnderecoCli()+"-"+obj.getBairroCli()+","+obj.getCidadeCli();


                        //Inicio chama método dialog
                        dialogOpcoes();
                        //Fim chama método dialog
                    }
                });
                //Fim do método click no item da tabela
            }
        }else{

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Nenhum Cliente Cadastrado");
        }


    }
    //Fim código fonte lista de clientes

    //Inicio código fonte dialog opções "Editar" , "Excluir" , "Mapa", "E-mail", "Ligar", "SMS"
    public void dialogOpcoes() {

        //Inicio do código ref. ao dialog com opções do clique listView diário
        final String[] items = new String[]{"EDITAR", "DELETAR", "SMS", "LIGAR", "MAPA", "E-MAIL","ENDEREÇO"};
        final Integer[] icons = new Integer[]{R.drawable.telalistacliente_dialogopcoes_iconeditar, R.drawable.telalistacliente_dialogopcoes_icondeletar, R.drawable.telalistacliente_dialogopcoes_iconsms, R.drawable.telalistacliente_dialogopcoes_iconligar, R.drawable.telalistacliente_dialogopcoes_iconmapa, R.drawable.telalistacliente_dialogopcoes_iconemail,R.drawable.telalistacliente_dialogopcoes_iconendereco};
        ListAdapter adapter = new ArrayAdapterWithIcon(this, items, icons);
        new AlertDialog.Builder(ClienteActivity.this)
                .setTitle("OPÇÕES CLIENTE")
                .setIcon(R.drawable.telalistacliente_dialogopcoes_iconopcoes)
                .setNegativeButton("SAIR", null)
                .setCancelable(false)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            flag = 2;
                            //Chama o editar
                            DialogNovoEditarCliente();
                        }
                        if (item == 1) {
                            //Chama o deletar
                            deletarCliente();
                        }
                        if (item == 2) {
                            //Chama o SMS
                            utils.enviarSms(celularClicado,ClienteActivity.this);
                        }
                        if (item == 3) {
                            //Chama o ligar
                            utils.ligarNumero(celularClicado,ClienteActivity.this);
                        }
                        if (item == 4) {
                            //Chama o mapa
                            utils.mapa(enderecoClicado,ClienteActivity.this);
                        }
                        if (item == 5) {
                            //Chama o e-mail
                            Toast.makeText(getApplicationContext(), "Função não implementada nesta versão", Toast.LENGTH_SHORT).show();
                        }
                        if(item == 6){
                            Toast.makeText(getApplicationContext(), "Função não implementada nesta versão", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
        //Fim do código ref. ao dialog com opções do clique listView diário
    }
    //Fim código fonte dialog opções "Editar" , "Excluir" , "Mapa", "E-mail", "Ligar", "SMS"

    //Inicio código fonte deleta cliente
    public void deletarCliente(){

        boolean clienteDeletado = new ClienteControle(ClienteActivity.this).deletarCliente(Integer.parseInt(idClienteClicada));

        if (clienteDeletado){
            Toast.makeText(getApplicationContext(), "CLIENTE DELETADO.", Toast.LENGTH_SHORT).show();
            listarClientes("");
        }else {
            Toast.makeText(getApplicationContext(), "ERRO AO DELETAR CLIENTE.", Toast.LENGTH_SHORT).show();
        }

    }
    //Fim código fonte deleta cliente




}
