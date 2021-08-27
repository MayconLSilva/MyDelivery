package com.br.megabytesoftware.mydelivery.Controle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.megabytesoftware.mydelivery.Adapter.DataBaseAdapter;
import com.br.megabytesoftware.mydelivery.Modelo.EntregaModelo;

import java.util.ArrayList;
import java.util.List;

public class EntregaControle extends DataBaseAdapter {

    public EntregaControle(Context context) {
        super(context);
        //Integração com o banco de dados
    }

    public List<EntregaModelo> listarEntregas(){

        List<EntregaModelo> listEntregas = new ArrayList<>();
//        String sql = "\n" +
//                "select idEnt, \n" +
//                "       dataLancamentoEnt, \n" +
//                "\t     idTracking, \n" +
//                "\t     valorEnt, \n" +
//                "\t     obsEnt,\n" +
//                "\t     nome_Cliente\n" +
//                "from tblentregas\n" +
//                "left join tblCliente on tblCliente.id_Cliente = tblentregas.idCliente\n";
        String sql = "select * from v_entregas";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()) {

            do {

                int idEntrega = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEnt")));
                String dataLancamento = (cursor.getString(cursor.getColumnIndex("dataLancamentoEnt")));
 //               int idTracking = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idTracking")));
                String obsEnt = cursor.getString(cursor.getColumnIndex("obsEnt"));
                float valorEnt = cursor.getFloat(cursor.getColumnIndex("valorEnt"));

                EntregaModelo modeloEntrega = new EntregaModelo();
                modeloEntrega.setIdEntrega(idEntrega);
                modeloEntrega.setDataLancamentoEntrega(dataLancamento);
 //               modeloEntrega.setIdTracking(idTracking);
                modeloEntrega.setObsEntrega(obsEnt);
                modeloEntrega.setValorEntrega(String.valueOf(valorEnt));
                //
                modeloEntrega.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_Cliente")));
                modeloEntrega.setEnderecoCliente(cursor.getString(cursor.getColumnIndex("enderecoEntrega")));
                modeloEntrega.setNumeroEnderecoCliente(cursor.getString(cursor.getColumnIndex("NumeroenderecoEntrega")));
                modeloEntrega.setBairroCliente(cursor.getString(cursor.getColumnIndex("BairroenderecoEntrega")));
                modeloEntrega.setCepCliente(cursor.getString(cursor.getColumnIndex("CEPenderecoEntrega")));
                modeloEntrega.setCidadeCliente(cursor.getString(cursor.getColumnIndex("CidadeenderecoEntrega")));

                listEntregas.add(modeloEntrega);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listEntregas;
    }

}