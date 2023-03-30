package org.example.adapterPattern.vd3;

public class Main {
    public static void main(String[] args) {
        AdapterCharger adapterCharger = new AdapterChargerComposition(new AdapteeVoltImplement());
        Volt v = adapterCharger.get12V();
        Volt v1 = adapterCharger.get6V();
        System.out.println(v);
        System.out.println(v1);
    }
}
