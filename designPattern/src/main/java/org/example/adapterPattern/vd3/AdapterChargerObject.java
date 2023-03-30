package org.example.adapterPattern.vd3;

public class AdapterChargerObject extends AdapteeVoltImplement implements AdapterCharger {
    @Override
    public Volt get12V() {
        return new Volt(getVolt().getVol()/10);
    }

    @Override
    public Volt get6V() {
        return new Volt(getVolt().getVol()/20);
    }
}
