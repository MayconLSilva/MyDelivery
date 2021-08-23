package com.br.megabytesoftware.mydelivery.Util;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.ContextMenu;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.br.megabytesoftware.mydelivery.Visao.ClienteActivity;

import java.util.Set;
public abstract class Utils {

    ContextCompat contex;

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("["+symbol+"]","");

        return s;
    }

    public static String mask(String format, String text){
        String maskedText="";
        int i =0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }

    //Inicio código fonte chama o envio de SMS do próprio celular com o número do cliente que pegou
    public void enviarSms(String value, Activity activity){

        if (value == ""){
            //Toast.makeText(activity, "Cliente sem número de celular cadastrado",Toast.LENGTH_SHORT).show();
            return;
        }else{
            String celularFormatar = removerAcentos(value);
            String celularChamar = celularFormatar.trim();

            Intent intentSMS = new Intent(Intent.ACTION_VIEW);
            intentSMS.setData(Uri.parse("sms:" + celularChamar));
//            activity.startActivity(intentSMS);
            //startActivity(intentSMS);
        }
    }


    //Fim código fonte chama o envio de SMS do próprio celular com o número do cliente que pegou

    //Inicio do código fonte chama o maps
    public void mapa(String value, Activity activity){

        String endereco = "Rua Tangará, Cornélio Procópio - PR";

        if (value == ""){
            Toast.makeText(activity, "Cliente sem endereço cadastrado",Toast.LENGTH_SHORT).show();
            return;
        }else{
            Intent intentMapa = new Intent(Intent.ACTION_VIEW);
            intentMapa.setData(Uri.parse("geo:0,0?z=14&q="+value));
            activity.startActivity(intentMapa);
        }

    }
    //Fim do código fonte chama o maps

    //Inicio código fonte chama o ligar do próprio celular com o número do cliente que pegou
    public void ligarNumero(String value, Activity activity){

        if (value == ""){
            Toast.makeText(activity, "Cliente sem número de celular cadastrado",Toast.LENGTH_SHORT).show();
            return;
        }else{
            if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE}, 123);
            else {
                String celularFormatar = removerAcentos(value);
                String celularChamar = celularFormatar.trim();

                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel:" + celularChamar));

                activity.startActivity(intentLigar);
            }
        }


    }
    //Inicio código fonte chama o ligar do próprio celular com o número do cliente que pegou

    //Inicio do código fonte remove acentuação
    public String removerAcentos(String acentos){

        acentos = acentos.replace("á", "a");
        acentos = acentos.replace("â", "a");
        acentos = acentos.replace("ã", "a");

        acentos = acentos.replace("é", "e");
        acentos = acentos.replace("í", "i");

        acentos = acentos.replace("ó", "o");
        acentos = acentos.replace("ô", "o");
        acentos = acentos.replace("õ", "o");

        acentos = acentos.replace("ú", "u");
        acentos = acentos.replace("ç", "c");

        acentos = acentos.replace("/","");
        acentos = acentos.replace(")","");
        acentos = acentos.replace("(","");
        acentos = acentos.replace(".","");
        acentos = acentos.replace("-","");

        return acentos;

    }
    //Fim do código fonte remove acentuação



}
