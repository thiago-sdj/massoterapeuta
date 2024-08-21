package com.example.massoterapeuta;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;


public class EditarSessaoActivity extends AppCompatActivity {


    private SessaoEntidade sessaoEntidade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sessao);

        Long idSessao = getIntent().getLongExtra("idSessao", 1);
        SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        sessaoViewModel.retornarSessaoSelecionada(idSessao).observe(this, sessao -> {
            if (sessao != null){
                sessaoEntidade = sessao;
                initializeComponents(sessaoEntidade);
            }
            else{
                Toast.makeText(
                        getApplicationContext(),
                        "Não foi possível carregar sessão",
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initializeComponents(SessaoEntidade sessao) {

        TextInputEditText inputEditData = findViewById(R.id.cadastro_data);
        inputEditData.setText(String.valueOf(sessao.getDataInicio()));
        inputEditData.setInputType(InputType.TYPE_NULL);
        inputEditData.setClickable(true);
        inputEditData.setFocusable(true);
        inputEditData.setOnClickListener(view -> showDatePickerDialog(inputEditData));


        TextInputEditText inputEditHoraInicio = findViewById(R.id.cadastro_horaInicio);
        inputEditHoraInicio.setText(String.valueOf(sessao.getHoraInicio()));
        inputEditHoraInicio.setInputType(InputType.TYPE_NULL);
        inputEditHoraInicio.setClickable(true);
        inputEditHoraInicio.setFocusable(true);
        inputEditHoraInicio.setOnClickListener(view -> showTimePickerDialog(inputEditHoraInicio));

        TextInputEditText inputEdiHoraFim = findViewById(R.id.cadastro_horaFim);
        inputEdiHoraFim.setClickable(false);
        inputEdiHoraFim.setFocusable(false);

        TextInputEditText inputEditPeso = findViewById(R.id.cadastro_peso);
        inputEditPeso.setText(String.valueOf(sessao.getPesoSessao()));


        String[] statusOptions = {"MARCADA", "REALIZADA", "CANCELADA"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_item_selecionado, statusOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Spinner spinner = (Spinner) findViewById(R.id.status_sessao);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sessao.setStatus((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Não foi selecionado", Toast.LENGTH_SHORT).show();
            }
        });


        MaterialButton botaoSalvar = findViewById(R.id.form_buttonSaveSessao);
        botaoSalvar.setOnClickListener(view -> {

            if (sessao.getStatus() == "REALIZADA"){
                String pin = gerarPin(4);
                enviarSms(sessao.getIdContrato(), pin);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Insira o PIN:");

                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pinDigitado = input.getText().toString();
                        if (pinDigitado.equals(pin)){
                            salvarSessao(sessao);
                        }
                        else{
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Pin incorreto",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
            else{
                salvarSessao(sessao);

            }

        });


    }

    private void salvarSessao(SessaoEntidade sessao){
        TextInputEditText inputEditDataAtualizada = findViewById(R.id.cadastro_data);


        TextInputEditText inputEditHoraInicioAtualizada = findViewById(R.id.cadastro_horaInicio);


        TextInputEditText inputEditPesoAtualizado = findViewById(R.id.cadastro_peso);



        String data = String.valueOf(inputEditDataAtualizada.getText());
        String horaInicio = String.valueOf(inputEditHoraInicioAtualizada.getText());
        String pesoString = inputEditPesoAtualizado.getText().toString();
        Float peso = Float.parseFloat(pesoString);


        sessao.setDataInicio(data);
        sessao.setHoraInicio(horaInicio);
        sessao.setPesoSessao(peso);



        SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        sessaoViewModel.retornarServicoTempoUsandoIdContrato(sessao.getIdContrato()).observe(this, tempo -> {
            if (tempo != null){
                String pattern = "HH:mm";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalTime horaFim = LocalTime.parse(horaInicio, formatter);
                horaFim = horaFim.plusMinutes(tempo);
                sessao.setHoraFim(horaFim.toString());
                sessaoViewModel.atualizar(sessao);
                Intent intent = new Intent(EditarSessaoActivity.this, SessaoSelecionadaAcitivity.class);
                intent.putExtra("idSessao", sessao.getIdSessao());
                startActivity(intent);
            }
            else{
                Toast.makeText(
                        getApplicationContext(),
                        "Não foi possível carregar tempo de duração do serviço para atualizar hora do fim da sessão",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void enviarSms(Long idContrato, String pin){
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EditarSessaoActivity.this, new String [] {Manifest.permission.SEND_SMS}, 0);
        }
        else{
            SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
            sessaoViewModel.retornarTelefoneClienteUsandoIdContrato(idContrato).observe(this, telefone -> {
                if (telefone != null){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(telefone, null,"Código de confirmação: " + pin, null, null);
                    Toast.makeText(
                            getApplicationContext(),
                            "Mensagem de confirmação enviada para " + telefone,
                            Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Erro. Não foi possível encontrar o número de telefone.",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public static String gerarPin (int length) {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }

    private void showDatePickerDialog(TextInputEditText inputEditData) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault());
                    String formattedDate = selectedDate.format(formatter).toString();
                    inputEditData.setText(formattedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void showTimePickerDialog(TextInputEditText inputEditHoraInicio){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    LocalTime selectedTime = LocalTime.of(selectedHour, selectedMinute);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
                    String formattedTime = selectedTime.format(formatter);
                    inputEditHoraInicio.setText(formattedTime);
                },
                hour, minute, true);
        timePickerDialog.show();
    }

}