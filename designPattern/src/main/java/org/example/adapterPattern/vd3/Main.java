package org.example.adapterPattern.vd3;

public class Main {
    public static void main(String[] args) {
        AdapterCharger adapterCharger = new AdapterChargerComposition(new AdapteeVoltImplement());
        Volt v = adapterCharger.get12V();
        Volt v1 = adapterCharger.get6V();
        System.out.println(v);
        System.out.println(v1);
        System.out.println("----");
        AdapterCharger adapterCharger1 = new AdapterChargerObject();
        Volt v2 = adapterCharger1.get6V();
        Volt v3 = adapterCharger1.get12V();
        System.out.println(v2);
        System.out.println(v3);
    }
}
