package com.osp.sape.maestros.rutinas;

//CLASE NECESARIA PARA ALGUNAS COMVERSIONES EN RUTINASSERVLET
public class HoraCliente
{

    public HoraCliente(String h, String v)
    {
        hora = h;
        valor = v;
    }

    public String getHora()
    {
        return hora;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public String getValor()
    {
        return valor;
    }

    public void setValor(String valor)
    {
        this.valor = valor;
    }

    private String hora;
    private String valor;
}
