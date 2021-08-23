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

    private int resultadoInsertUpdate;
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
        values.put("dataAlteracao_Cliente", modeloCliente.getDataAlteracaoCli());
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
}
