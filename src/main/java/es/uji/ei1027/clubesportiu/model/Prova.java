package es.uji.ei1027.clubesportiu.model;

import java.time.LocalDate;

public class Prova {
    private String nom;
    private String descrip;
    private String tipus;
    private LocalDate data;


    public Prova() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "nom='" + nom + "\'" +
                ", descripción='" + descrip + "\'" +
                ", tipus='" + tipus + "\'" +
                ", data=" + data +
                "}";
    }
}
