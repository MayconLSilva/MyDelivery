package com.br.megabytesoftware.mydelivery.Controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.br.megabytesoftware.mydelivery.Adapter.DataBaseAdapter;
import com.br.megabytesoftware.mydelivery.Modelo.ClienteModelo;

import java.util.ArrayList;
import java.util.List;

public class ClienteControle extends DataBaseAdapter {

    public ClienteControle(Context context){
        super(context);
        //Integração com o banco de dados
    }

    //Inicio do código fonte salvar cliente
    public boolean salvarCliente (ClienteModelo modeloCliente){

        ContentValues values = new ContentValues();
        values.put("nome_Cliente",modeloCliente.getNomeCli());
        values.put("cpf_Cliente", modeloCliente.getCpfCli());
        values.put("rg_Cliente", modeloCliente.getRgCli());
        values.put("email_Cliente", modeloCliente.getEmailCli());
        values.put("telefone_Cliente", modeloCliente.getTelefoneCli());
        values.put("celular_Cliente", modeloCliente.getCelularCli());
        values.put("dataCadastro_Cliente", modeloCliente.getDataCadastroCli());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean incluirCliente = db.insert("tblCliente", null, values) > 0;
        db.close();

        return incluirCliente;
    }
    //Fim do código fonte salvar cliente

    //Inicio do código fonte atualizar cliente
    public boolean atualizarCliente (ClienteModelo modeloCliente){

        ContentValues values = new ContentValues();
        values.put("nome_Cliente", modeloCliente.getNomeCli());
        values.put("cpf_Cliente", modeloCliente.getCpfCli());
        values.put("rg_Cliente", modeloCliente.getRgCli());
        values.put("email_Cliente", modeloCliente.getEmailCli());
        values.put("telefone_Cliente", modeloCliente.getTelefoneCli());
        values.put("celular_Cliente", modeloCliente.getCelularCli());
        values.put("dataAlteracao_Cliente", modeloCliente.getDataAlteracaoCli());
        String where = "id_Cliente = ?";

        String[] whereArgs = {Integer.toString(modeloCliente.getIdCli())};
        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateCliente = db.update("tblCliente", values, where, whereArgs) > 0;
        db.close();

        return updateCliente;
    }
    //Fim do código fonte atualizar cliente

    public List<ClienteModelo> listarClientes(String value){

        String stringSQL = "SELECT * FROM tblcliente ORDER by nome_Cliente ASC";
        if(value != "")
            stringSQL = "SELECT * FROM tblcliente where nome_Cliente LIKE " + "'"+ value +"%'";

        List<ClienteModelo> ListaClientes = new ArrayList<>();
        String sql = stringSQL ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()) {

            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_Cliente")));
                String nome = cursor.getString(cursor.getColumnIndex("nome_Cliente"));
                String cpf = cursor.getString(cursor.getColumnIndex("cpf_Cliente"));
                String rg = cursor.getString(cursor.getColumnIndex("rg_Cliente"));
                String email = cursor.getString(cursor.getColumnIndex("email_Cliente"));
                String telefone = cursor.getString(cursor.getColumnIndex("telefone_Cliente"));
                String celular = cursor.getString(cursor.getColumnIndex("celular_Cliente"));
                String dataCadastro = cursor.getString(cursor.getColumnIndex("dataCadastro_Cliente"));

                ClienteModelo modeloCliente = new ClienteModelo();
                modeloCliente.setIdCli(id);
                modeloCliente.setNomeCli(nome);
                modeloCliente.setCpfCli(cpf);
                modeloCliente.setRgCli(rg);
                modeloCliente.setEmailCli(email);
                modeloCliente.setTelefoneCli(telefone);
                modeloCliente.setCelularCli(celular);
                modeloCliente.setDataCadastroCli(dataCadastro);
                ListaClientes.add(modeloCliente);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ListaClientes;
    }

    //Inicio do código fonte deletar cliente pela id
    public boolean deletarCliente (int clienteID){

        boolean clienteDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        clienteDeletado = db.delete("tblcliente", "id_Cliente ='" + clienteID + "'", null) > 0;
        db.close();

        return clienteDeletado;
    }
    //Fim do código fonte deletar cliente pela id

    //Inicio do código fonte busca informações do cliente pela id
    public ClienteModelo buscaInformacaoCliente(int clienteID){

        ClienteModelo modeloCliente = new ClienteModelo();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT *FROM tblCliente WHERE id_Cliente = "+clienteID;

        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            String nome = cursor.getString(cursor.getColumnIndex("nome_Cliente"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf_Cliente"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone_Cliente"));
            String celular = cursor.getString(cursor.getColumnIndex("celular_Cliente"));
            String email = cursor.getString(cursor.getColumnIndex("email_Cliente"));
            String rg = cursor.getString(cursor.getColumnIndex("rg_Cliente"));

            modeloCliente = new ClienteModelo();

            modeloCliente.setIdCli(clienteID);
            modeloCliente.setNomeCli(nome);
            modeloCliente.setCpfCli(cpf);
            modeloCliente.setRgCli(rg);
            modeloCliente.setEmailCli(email);
            modeloCliente.setTelefoneCli(telefone);
            modeloCliente.setCelularCli(celular);

        }

        return modeloCliente;
    }
    //Fim do código fonte busca informações do cliente pela id

    //Inicio do código fonte salvar endereço do cliente
    public boolean salvarClienteEndereco (ClienteModelo modeloCliente){

        ContentValues values = new ContentValues();
        values.put("idCliente",modeloCliente.getIdCli());
        values.put("logradouroClienteEnd", modeloCliente.getEnderecoCli());
        values.put("numeroClienteEnd", modeloCliente.getNumeroEnderecoCli());
        values.put("bairroClienteEnd", modeloCliente.getBairroCli());
        values.put("cidadeClienteEnd", modeloCliente.getCidadeCli());
        values.put("cepClienteEnd", modeloCliente.getCepCli());
        values.put("principalClienteEnd", modeloCliente.getEnderecoPrincipalCli());
        values.put("dataCadastroClienteEnd", modeloCliente.getDataCadastroCli());
        SQLiteDatabase db = this.getWritableDatabase();

        boolean incluirClienteEndereco = db.insert("tblClienteEndereco", null, values) > 0;
        db.close();

        return incluirClienteEndereco;
    }
    //Fim do código fonte salvar endereço do cliente

    //Inicio do código fonte busca informações do cliente pela id
    public ClienteModelo buscaEndereco(int clienteID,String filtroPrincipal){

        String filtroConsulta = "";
        if(filtroPrincipal == "S")
            filtroConsulta = " and principalClienteEnd = 1";

        ClienteModelo modeloCliente = new ClienteModelo();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from tblClienteEndereco where idCliente = "+clienteID + filtroConsulta;

        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            String logradouro = cursor.getString(cursor.getColumnIndex("logradouroClienteEnd"));
            String numerologradouro = cursor.getString(cursor.getColumnIndex("numeroClienteEnd"));
            String bairro = cursor.getString(cursor.getColumnIndex("bairroClienteEnd"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidadeClienteEnd"));
            String cep = cursor.getString(cursor.getColumnIndex("cepClienteEnd"));
            Boolean principal = Boolean.valueOf(cursor.getString(cursor.getColumnIndex("principalClienteEnd")));
            int idEndereco = cursor.getInt(cursor.getColumnIndex("idClienteEnd"));

            modeloCliente = new ClienteModelo();
            modeloCliente.setIdenderecoCli(idEndereco);
            modeloCliente.setIdCli(clienteID);
            modeloCliente.setEnderecoCli(logradouro);
            modeloCliente.setNumeroEnderecoCli(numerologradouro);
            modeloCliente.setBairroCli(bairro);
            modeloCliente.setCepCli(cep);
            modeloCliente.setCidadeCli(cidade);
            modeloCliente.setEnderecoPrincipalCli(principal);

            System.out.println("Principal " + principal);

        }

        return modeloCliente;
    }
    //Fim do código fonte busca informações do cliente pela id



}
