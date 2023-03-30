package org.example.adapterPattern.vd3;

public class AdapteeVoltImplement implements AdapteeVolt {
    @Override
    public Volt getVolt() {
        return new Volt(120);
    }
}
