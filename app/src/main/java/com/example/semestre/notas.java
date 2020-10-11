package com.example.semestre;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link notas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notas extends Fragment implements View.OnClickListener{
//444444444444444444444444444444444444444444444444444
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BaseDatos miBD;
    Button btonVista1;
    Button btonVista2;
    Button btonVista3;
    Button btnGC1;
    View L1;
    View L2;
    View L3;
    Spinner spinner;
    ArrayList<String> ComboAsignatura;
    ArrayList<Asignatura> Asignaturaslista;

    EditText NotaTtextC1;
    EditText PorcTtextC1;
    EditText notaQtextC1;
    EditText porcQtextC1;
    EditText notaPTtextC1;
    EditText porcPTtextC1;
    EditText notaPPtextC1;
    EditText porcPPtextC1;
    Button btncorte1;
    //Corte 2 ---------------
    EditText notaTtextC2, PorcTtextC2, notaQtextC2, porcQtextC2, notaPTtextC2, porcPTtextC2, notaPPtextC2, porcPPtextC2;
    //Corte 3
    EditText notaTtextC3, PorcTtextC3, notaQtextC3, porcQtextC3, notaPTtextC3, porcPTtextC3, notaPPtextC3, porcPPtextC3;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public notas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notas.
     */
    // TODO: Rename and change types and number of parameters
    public static notas newInstance(String param1, String param2) {
        notas fragment = new notas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notas, container, false);
        miBD = new BaseDatos(this.getContext(), "APPNOTAS.bd", null, 1);
        spinner = view.findViewById(R.id.comboID);
        btonVista1 = view.findViewById(R.id.btncorte1);
        btonVista2 = view.findViewById(R.id.btnCorte2);
        L1 = view.findViewById(R.id.Linear1);
        L2 = view.findViewById(R.id.Linear2);
        L3 = view.findViewById(R.id.Linear3);
        llenarSpinner();
        ArrayAdapter<CharSequence> adaptador = new
                ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,ComboAsignatura);
        spinner.setAdapter(adaptador);
        btnGC1 = view.findViewById(R.id.btnGC1);
        //Corte1
        NotaTtextC1 = view.findViewById(R.id.NotaTtextC1);
        PorcTtextC1 = view.findViewById(R.id.PorcTtextC1);
        notaQtextC1 = view.findViewById(R.id.notaQtextC1);
        porcQtextC1 = view.findViewById(R.id.porcQtextC1);
        notaPTtextC1 = view.findViewById(R.id.notaPTtextC1);
        porcPTtextC1 = view.findViewById(R.id.porcPTtextC1);
        notaPPtextC1 = view.findViewById(R.id.notaPPtextC1);
        porcPPtextC1 = view.findViewById(R.id.porcPPtextC1);
        //Corte2
        notaTtextC2 = view.findViewById(R.id.notaTtextC2);
        PorcTtextC2 = view.findViewById(R.id.porcentajeTtextC2);
        notaQtextC2 = view.findViewById(R.id.notaQtextC2);
        porcQtextC2 = view.findViewById(R.id.porcentajeQtextC2);
        notaPTtextC2 = view.findViewById(R.id.notaPTtextC2);
        porcPTtextC2 = view.findViewById(R.id.porcentajePTtextC2);
        notaPPtextC2 = view.findViewById(R.id.notaPPtextC2);
        porcPPtextC2 = view.findViewById(R.id.porcentajePPtextC2);
        //Corte3
        notaTtextC3 = view.findViewById(R.id.notaTtextC3);
        PorcTtextC3 = view.findViewById(R.id.porcentajeTtextC3);
        notaQtextC3 = view.findViewById(R.id.notaQtextC3);
        porcQtextC3 = view.findViewById(R.id.porcentajeQtextC3);
        notaPTtextC3 = view.findViewById(R.id.notaPTtextC3);
        porcPTtextC3 = view.findViewById(R.id.porcentajePPtextC3);
        notaPPtextC3 = view.findViewById(R.id.notaPPtextC3);
        porcPPtextC3 = view.findViewById(R.id.porcentajePPtextC3);
        guardarNota();
        view.findViewById(R.id.btncorte1).setOnClickListener(this);
        view.findViewById(R.id.btnCorte2).setOnClickListener(this);
        view.findViewById(R.id.btnCorte3).setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncorte1:
                if(L1.getVisibility() == View.GONE)
                {
                    L1.setVisibility(View.VISIBLE);
                    L2.setVisibility(View.GONE);
                    L3.setVisibility(View.GONE);

                }else
                {
                   L1.setVisibility(View.GONE);
                }
                break;
            case R.id.btnCorte2:
                if(L2.getVisibility() == View.GONE)
                {
                    L2.setVisibility(View.VISIBLE);
                    L1.setVisibility(View.GONE);
                    L3.setVisibility(View.GONE);

                }else
                {
                    L2.setVisibility(View.GONE);
                }
                break;
            case R.id.btnCorte3:
                if(L3.getVisibility() == View.GONE)
                {
                    L3.setVisibility(View.VISIBLE);
                    L1.setVisibility(View.GONE);
                    L2.setVisibility(View.GONE);

                }else
                {
                    L3.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void llenarSpinner(){
        ComboAsignatura = new ArrayList<String>();
        Cursor cursor = miBD.ListarAs();
        ComboAsignatura.add("Seleccione");
        while (cursor.moveToNext()){
            ComboAsignatura.add(cursor.getString(0));
        }
    }
    public void guardarNota() {
         btnGC1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ptc1, ptc2, ptc3;
                        //Convertir datos Corte 1
                        Double nt,nq,npt,npp;
                        Integer pt, pq, ppt,ppp;
                        nt = Double.parseDouble(NotaTtextC1.getText().toString());
                        pt = Integer.parseInt(PorcTtextC1.getText().toString());
                        nq = Double.parseDouble(notaQtextC1.getText().toString());
                        pq = Integer.parseInt(porcQtextC1.getText().toString());
                        npt = Double.parseDouble(notaPTtextC1.getText().toString());
                        ppt = Integer.parseInt(porcPTtextC1.getText().toString());
                        npp = Double.parseDouble(notaPPtextC1.getText().toString());
                        ppp = Integer.parseInt(porcPPtextC1.getText().toString());
                        Double defi = Calcular_nota(nt, pt, nq, pq, npt, ppt, npp, ppp);
                        ptc1 = VerificarP(pt,pq,ppt,ppp);
                        //Convertir datos Corte 2
                        Double nt2,nq2,npt2,npp2;
                        Integer pt2, pq2, ppt2,ppp2;
                        nt2 = Double.parseDouble(notaTtextC2.getText().toString());
                        pt2 = Integer.parseInt(PorcTtextC2.getText().toString());
                        nq2 = Double.parseDouble(notaQtextC2.getText().toString());
                        pq2 = Integer.parseInt(porcQtextC2.getText().toString());
                        npt2 = Double.parseDouble(notaPTtextC2.getText().toString());
                        ppt2 = Integer.parseInt(porcPTtextC2.getText().toString());
                        npp2 = Double.parseDouble(notaPPtextC2.getText().toString());
                        ppp2 = Integer.parseInt(porcPPtextC2.getText().toString());
                        Double defi2 = Calcular_nota(nt2, pt2, nq2, pq2, npt2, ppt2, npp2, ppp2);
                        ptc2 = VerificarP(pt2,pq2,ppt2,ppp2);
                        //Convertir datos Corte 3
                        Double nt3,nq3,npt3,npp3;
                        Integer pt3, pq3, ppt3,ppp3;
                        nt3 = Double.parseDouble(notaTtextC3.getText().toString());
                        pt3 = Integer.parseInt(PorcTtextC3.getText().toString());
                        nq3 = Double.parseDouble(notaQtextC3.getText().toString());
                        pq3 = Integer.parseInt(porcQtextC3.getText().toString());
                        npt3 = Double.parseDouble(notaPTtextC3.getText().toString());
                        ppt3 = Integer.parseInt(porcPTtextC3.getText().toString());
                        npp3 = Double.parseDouble(notaPPtextC3.getText().toString());
                        ppp3 = Integer.parseInt(porcPPtextC3.getText().toString());
                        Double defi3 = Calcular_nota(nt3, pt3, nq3, pq3, npt3, ppt3, npp3, ppp3);

                        ptc3 = VerificarP(pt3,pq3,ppt3,ppp3);
                        if (ptc1 == true && ptc2 == true && ptc3==true){

                            boolean datos = miBD.GuardarNotas(spinner.getSelectedItem().toString(),defi, defi2, defi3);
                            if (datos == true){
                                new SweetAlertDialog(getContext())
                                        .setTitleText("Se ha registrado la nota de los cortes")
                                        .show();
                            }else{
                                Toast.makeText(getActivity(),"No se guardó", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Verifique")
                                    .setContentText("Los Porcentajes no están bien definidos - "+ppp+"-"+ppt+"-"+pq+"-"+pt)
                                    .show();
                        }

                        }
                }
        );
    }
    public Double Calcular_nota(Double nT, Integer PT, Double nQ, Integer PQ, Double nPT, Integer PPT, Double nPP, Integer PPP){
        Double talleres, quices, parcialT, parcialP;
        talleres = (nT * PT);
        quices = (nQ * PQ);
        parcialT = (nPT * PPT);
        parcialP = (nPP * PPP);
        return (talleres + quices + parcialT + parcialP)/100;
    }
    public boolean VerificarP(Integer p1, Integer p2, Integer p3, Integer p4){
        Integer pT = p1 + p2 +p3 + p4;
        if (pT == 100){
            return true;
        }else{
            return false;
        }

    }
}