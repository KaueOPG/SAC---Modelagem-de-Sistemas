package com.kaueopg.sac.v1.Model;

public class Consulta {
    private String data;
    private String horario;
    private boolean status;
    private String cpfMedico;
    private String cpfPaciente;

    public Consulta() {}

    public Consulta(String data, String horario, String cpfPaciente, String cpfMedico) {
        this.data = data;
        this.horario = horario;
        this.cpfPaciente = cpfPaciente;
        this.cpfMedico = cpfMedico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }
}
