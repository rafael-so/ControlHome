/**
 * @author Rafael Oliveira
 *
 * Está Activity tem por finalidade apresentar as opções para controlar
 * os componentes externos (lâmpadas).
 *
 * @see android.widget.ExpandableListView
 * @link https://developer.android.com/reference/android/widget/ExpandableListView
 *
 * @see android.support.v4.app.Fragment
 * @link https://developer.android.com/reference/android/support/v4/app/Fragment
 *
 * @see android.view.LayoutInflater
 * @link https://developer.android.com/reference/android/view/LayoutInflater
 *
 * @see android.support.v7.widget.RecyclerView
 * @link https://developer.android.com/guide/topics/ui/layout/recyclerview
 *
 * @see android.widget.Switch
 * @link https://developer.android.com/reference/android/widget/Switch
 *
 * @see android.view.View
 * @link https://developer.android.com/reference/android/view/View
 *
 * @see android.view.ViewGroup
 * @link https://developer.android.com/reference/android/view/ViewGroup
 *
 */

package rafael.controlhome.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rafael.controlhome.modell.ModelRecord;
import rafael.controlhome.modell.dao.DaoAddressIP;
import rafael.controlhome.connection.mqtt.ConnectionMQTT;
import rafael.controlhome.R;


public class FragmentControlHome extends Fragment /*implements GestureDetector.OnGestureListener*/ {

    public ModelRecord modelRecord = new ModelRecord();
    public Date data;
    public String status;
    public static List<ModelRecord> modelRecordList = new ArrayList();

    final String TAG = "FragmentControlHome";
    public DaoAddressIP daoAddressIP;

    Switch luzSala;
    Switch luzCozinha;
    Switch luzBanheiro;
    Switch luzGaragem;
    Button luzJardim;
    Switch luzQuarto1;
    Switch luzQuarto2;

    public static FragmentControlHome newInstance() {
        FragmentControlHome fragment = new FragmentControlHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_control_home, container, false);

        ActionsSwitchButtons(view);
        verificaSwitch();
        return view;
    }

    public void ActionsSwitchButtons(View view){
        luzSala =  view.findViewById(R.id.luzSala);
        luzCozinha =  view.findViewById(R.id.luzCozinha);
        luzBanheiro =  view.findViewById(R.id.luzBanheiro);
        luzGaragem =  view.findViewById(R.id.luzGaragem);
        luzJardim =  view.findViewById(R.id.luzJardi);
        luzQuarto1 =  view.findViewById(R.id.luzQuarto1);
        luzQuarto2 =  view.findViewById(R.id.luzQuarto2);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            luzSala.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzSala.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("SL");
                        } else {
                            status = "Desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("SD");
                        }
                        modelRecord.setNome("Luz Sala");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }

                }
            });

            luzCozinha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzCozinha.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("CL");
                        } else {
                            status = "Desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("CD");
                        }
                        modelRecord.setNome("Luz Cozinha");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }
                }
            });

            luzBanheiro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzBanheiro.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("BL");
                        } else {
                            status = "desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("BD");
                        }
                        modelRecord.setNome("Luz Banheiro");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }
                }
            });

            luzGaragem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzGaragem.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("GL");
                        } else {
                            status = "Desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("GD");
                        }
                        modelRecord.setNome("Luz Garagem");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }
                }
            });

            luzQuarto1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzQuarto1.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("Q1L");

                        } else {
                            status = "desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("Q1D");
                        }
                        modelRecord.setNome("Luz 1º Quarto");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }
                }
            });

            luzQuarto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        modelRecord = new ModelRecord();
                        if (luzQuarto2.isChecked()) {
                            status = "Ligado";
                            modelRecord.setImagem(R.mipmap.ac);
                            ConnectionMQTT.publishMessage("Q2L");

                        } else {
                            status = "desligado";
                            modelRecord.setImagem(R.mipmap.ap);
                            ConnectionMQTT.publishMessage("Q2D");
                        }
                        modelRecord.setNome("Luz 2º Quarto");
                        modelRecord.setData_hora(sdf.format(new Date()));
                        modelRecord.setStatus(status);
                        modelRecordList.add(modelRecord);
                    }

                }
            });

            luzJardim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(connection()) {
                        ConnectionMQTT.publishMessage("JL");
                    }

                }
            });
        //}
    }

    public boolean connection(){
        return ConnectionMQTT.mqttAndroidClient.isConnected();
    }

    public void verificaSwitch(){
        for (int i = 0; i < modelRecordList.size(); i++){
            switch (modelRecordList.get(i).getNome()){
                case "Luz Sala":
                    luzSala.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
                case "Luz Cozinha":
                    luzCozinha.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
                case "Luz Banheiro":
                    luzBanheiro.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
                case "Luz Garagem":
                    luzGaragem.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
                case "Luz 1º Quarto":
                    luzQuarto1.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
                case "Luz 2º Quarto":
                    luzQuarto2.setChecked(modelRecordList.get(i).getStatus() == "Ligado" ? true : false);
                    break;
            }
        }
    }

}
