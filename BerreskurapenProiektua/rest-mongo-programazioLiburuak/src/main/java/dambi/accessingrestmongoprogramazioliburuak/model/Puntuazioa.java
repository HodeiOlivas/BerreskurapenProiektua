package dambi.accessingrestmongoprogramazioliburuak.model;

public class Puntuazioa {

    private String name;
    private int puntuazioa;

    public Puntuazioa() {
        this.name = "";
        this.puntuazioa = 0;
    }

    public Puntuazioa(String name, int puntuazioa) {
        this.name = name;
        this.puntuazioa = puntuazioa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntuazioa() {
        return puntuazioa;
    }

    public void setPuntuazioa(int puntuazioa) {
        this.puntuazioa = puntuazioa;
    }

}
