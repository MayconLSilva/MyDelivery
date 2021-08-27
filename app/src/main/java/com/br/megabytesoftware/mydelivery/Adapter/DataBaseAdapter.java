package com.br.megabytesoftware.mydelivery.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDelevery.db";

    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlcliente = "CREATE TABLE tblCliente " +
                "(id_Cliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome_Cliente TEXT," +
                " cpf_Cliente TEXT UNIQUE," +
                " rg_Cliente TEXT," +
                " telefone_Cliente TEXT," +
                " celular_Cliente TEXT UNIQUE," +
                " email_Cliente TEXT,"  +
                " dataCadastro_Cliente TEXT," +
                " dataAlteracao_Cliente TEXT)";

        String sqlclienteEndereco = "CREATE TABLE tblClienteEndereco " +
                "(idClienteEnd INTEGER PRIMARY KEY AUTOINCREMENT," +
                " idCliente INTEGER ," +
                " logradouroClienteEnd TEXT ," +
                " numeroClienteEnd TEXT," +
                " bairroClienteEnd TEXT," +
                " cidadeClienteEnd TEXT," +
                " cepClienteEnd TEXT,"  +
                " principalClienteEnd BOOL,"  +
                " dataCadastroClienteEnd TEXT," +
                " dataAlteracaoClienteEnd TEXT)";

        String sqlentrega = "CREATE TABLE tblentregas " +
                "(idEnt INTEGER PRIMARY KEY AUTOINCREMENT," +
                " dataLancamentoEnt TEXT," +
                " dataSaidaEnt TEXT," +
                " dataEntregueEnt TEXT," +
                " idCliente INTEGER," +
                " idTracking INTEGER," +
                " cargaEnt INTEGER," +
                " valorEnt FLOAT,"+
                " assinaturaEnt BLOB,"+
                " idERPEnt INTEGER,"+
                " ocorrenciaEnt TEXT," +
                " obsEnt TEXT," +
                " logradouroAlternativoEnt TEXT," +
                " numeroAlternativoEnt TEXT," +
                " bairroAlternativoEnt TEXT," +
                " cidadeAlternativoEnt TEXT," +
                " cepAlternativoEnt TEXT)";

        String sqlentregaCancelada = "CREATE TABLE tblentregas_canceladas" +
                "( id_EntregasCancelada INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numeroentrega_EntregaCancelada INTEGER,"+
                "idEntregaERP_EntregaCancelada INTEGER,"+
                "foto1_EntregaCancelada BLOB,"+
                "foto2_EntregaCancelada BLOB,"+
                "foto3_EntregaCancelada BLOB,"+
                "motivo_EntregaCancelada TEXT)";

        String sqlentregaItens = "CREATE TABLE tblentregasitens " +
                "(id_EntregaItens INTEGER PRIMARY KEY AUTOINCREMENT," +
                " idProduto_EntregaItens INTEGER," +
                " idEntrega_EntregaItens INTEGER," +
                " valorProduto_EntregaItens FLOAT," +
                " idEntregaItensERP_EntregaItens INTEGER)";

        String sqlproduto = "CREATE TABLE tblProduto " +
                "( id_Produto INTEGER PRIMARY KEY AUTOINCREMENT," +
                " idProdutoERP_Produto INTEGER," +
                " descricao_Produto TEXT," +
                " valor_Produto FLOAT)";

        String viewEntregas = "CREATE VIEW v_entregas \n" +
                "AS\n" +
                "select idEnt,\n" +
                "       dataLancamentoEnt,\n" +
                "\t   idTracking,\n" +
                "\t   valorEnt,\n" +
                "\t   obsEnt,\n" +
                "\t   nome_Cliente,\n" +
                "\t   case when logradouroAlternativoEnt is null OR logradouroAlternativoEnt = '' then (select logradouroClienteEnd from tblClienteEndereco where idClienteEnd = tblentregas.idCliente and principalClienteEnd = 1) \n" +
                "\t   else logradouroAlternativoEnt end as enderecoEntrega,\n" +
                "\t   case when numeroAlternativoEnt is null OR numeroAlternativoEnt = '' then (select numeroClienteEnd from tblClienteEndereco where idClienteEnd = tblentregas.idCliente and principalClienteEnd = 1) \n" +
                "\t   else numeroAlternativoEnt end as NumeroenderecoEntrega,\n" +
                "\t   case when bairroAlternativoEnt is null  OR bairroAlternativoEnt = '' then (select bairroClienteEnd from tblClienteEndereco where idClienteEnd = tblentregas.idCliente and principalClienteEnd = 1) \n" +
                "\t   else bairroAlternativoEnt end as BairroenderecoEntrega,\n" +
                "\t   case when cidadeAlternativoEnt is null OR cidadeAlternativoEnt = '' then (select cidadeClienteEnd from tblClienteEndereco where idClienteEnd = tblentregas.idCliente and principalClienteEnd = 1) \n" +
                "\t   else cidadeAlternativoEnt end as CidadeenderecoEntrega,\n" +
                "\t   case when cepAlternativoEnt is null OR cepAlternativoEnt = '' then (select cepClienteEnd from tblClienteEndereco where idClienteEnd = tblentregas.idCliente and principalClienteEnd = 1) \n" +
                "\t   else cepAlternativoEnt end as CEPenderecoEntrega\n" +
                "\t from tblentregas\n" +
                "\t left join tblCliente on tblCliente.id_Cliente = tblentregas.idCliente";

        db.execSQL(sqlcliente);
        db.execSQL(sqlentrega);
        db.execSQL(sqlclienteEndereco);
        //db.execSQL(sqlentregaItens);
        //db.execSQL(sqlproduto);
        //db.execSQL(sqlentregaCancelada);

        db.execSQL(viewEntregas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlcliente = "DROP TABLE IF EXISTS tblCliente";
        db.execSQL(sqlcliente);

        String sqlclienteEndereco = "DROP TABLE IF EXISTS tblClienteEndereco";
        db.execSQL(sqlclienteEndereco);

        String sqlentrega = "DROP TABLE IF EXISTS tblentregas";
        db.execSQL(sqlentrega);

        //SCRIPT VIEWS
        String viewEntregas = "DROP TABLE IF EXISTS v_entregas";
        db.execSQL(viewEntregas);

//        String sqlentregaItens = "DROP TABLE IF EXISTS tblentregasitens";
//        db.execSQL(sqlentregaItens);
//
//        String sqlentregaCancelada = "DROP TABLE IF EXISTS tblentregas_canceladas";
//        db.execSQL(sqlentregaCancelada);

        onCreate(db);
    }
}
