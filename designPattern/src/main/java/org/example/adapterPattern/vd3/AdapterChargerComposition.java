package org.example.adapterPattern.vd3;

public class AdapterChargerComposition implements AdapterCharger {
    AdapteeVolt adapteeVolt;

    public AdapterChargerComposition(AdapteeVolt adaptee) {
        adapteeVolt = adaptee;
    }

    @Override
    public Volt get12V() {
        return new Volt(adapteeVolt.getVolt().getVol() / 10);
    }

    @Override
    public Volt get6V() {
        return new Volt(adapteeVolt.getVolt().getVol() / 20);
    }
}
