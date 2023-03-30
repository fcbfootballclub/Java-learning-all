package org.example.adapterPattern.vd3;

public class Volt {
    int vol;

    public Volt(int vol) {
        this.vol = vol;
    }

    public Volt() {
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        return "Volt{" +
                "vol=" + vol +
                '}';
    }
}
