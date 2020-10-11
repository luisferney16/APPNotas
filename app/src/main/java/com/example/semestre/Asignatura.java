package com.example.semestre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Asignatura#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Asignatura extends Fragment {

    BaseDatos miBD;
    EditText idt;
    EditText nomt;
    Button btnguardar;
    Button btnListar;
    Button btnActualizar;
    Button btnEliminar;
    ArrayList<String> LisLista;
    ArrayAdapter adaptador;
    ListView lv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Asignatura() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Asignatura.
     */
    // TODO: Rename and change types and number of parameters
    public static Asignatura newInstance(String param1, String param2) {
        Asignatura fragment = new Asignatura();
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
        View view =  inflater.inflate(R.layout.fragment_asignatura, container, false);
        miBD = new BaseDatos(this.getContext(), "APPNOTAS.bd", null, 1);
        idt = view.findViewById(R.id.IDtext);
        nomt = view.findViewById(R.id.nombreAstext);
        btnguardar = view.findViewById(R.id.btnG);
        btnListar = view.findViewById(R.id.btnC);
        btnActualizar = view.findViewById(R.id.btnA);
        btnEliminar = view.findViewById(R.id.btnE);
        lv = view.findViewById(R.id.listv1);
        guardarA();
        listarDatos();
        Actualizar();
        Eliminar();
        return view;


    }
    public void guardarA(){
        btnguardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (idt.getText().toString() == "" || nomt.getText().toString() == ""){
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Debe llenar todos los campos")
                                    .show();
                        }else{
                            boolean datos = miBD.GuardarAsignatura(idt.getText().toString(), nomt.getText().toString());
                            if (datos == true){
                                new SweetAlertDialog(getContext())
                                        .setTitleText("Se ha registrado la asignatura " + nomt.getText().toString())
                                        .show();
                                limpiarcampos();
                            }else{
                                Toast.makeText(getActivity(),"No se guardó", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                }
        );
    }
    public void listarDatos(){
        btnListar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LisLista = miBD.llenarlv();
                        adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, LisLista);
                        lv.setAdapter(adaptador);
                        Toast.makeText(getActivity(),"Entre", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void limpiarcampos(){
        idt.setText("");
        nomt.setText("");
    }
    public void Actualizar(){
        btnActualizar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean datos = miBD.ActualizarA(idt.getText().toString(), nomt.getText().toString());
                        if (datos == true){
                            new SweetAlertDialog(getContext())
                                    .setTitleText("Se ha actualizado la asignatura")
                                    .show();
                            limpiarcampos();
                        }else{
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("No se actualizaron los datos")
                                    .show();
                        }
                    }
                }
        );
    }

    public void Eliminar(){
        btnEliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer dato = miBD.EliminarA(idt.getText().toString());
                        if (dato == 1){
                            new SweetAlertDialog(getContext())
                                    .setTitleText("Se ha borado la asignatura")
                                    .show();
                        }else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("No se borraron los datos")
                                    .show();
                        }
                    }
                }
        );
    }

}