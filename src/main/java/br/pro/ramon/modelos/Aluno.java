package br.pro.ramon.modelos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Aluno implements Serializable {

    private String nome;
    private double p1, p2;

    protected Aluno() {
    }

    public Aluno(String nome, double p1, double p2) throws NotaInvalidaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException();
        } else if (p1 < 0 || p1 > 10 || p2 < 0 || p2 > 10) {
            throw new NotaInvalidaException();
        }

        this.nome = nome;
        this.p1 = p1;
        this.p2 = p2;
    }

    @XmlTransient
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    @XmlElement
    public double getMedia() {
        return (p1 + p2) / 2;
    }

    @XmlAttribute
    public boolean getAprovado() {
        return getMedia() >= 6;
    }

}
