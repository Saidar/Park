package ru.parking.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum VehicleEnum {
    DEFAULT(0),
    CAR(1),
    TRUCK(2);

    private static final Map<Integer,VehicleEnum> lookup = new HashMap<>();
    private int code;

    static {
        for(VehicleEnum v : EnumSet.allOf(VehicleEnum.class))
            lookup.put(v.getCode(), v);
    }

    VehicleEnum(int code) {
        this.code = code;
    }

    public int getCode() { return code; }

    public static VehicleEnum get(int code) {
        return lookup.get(code);
    }
}
