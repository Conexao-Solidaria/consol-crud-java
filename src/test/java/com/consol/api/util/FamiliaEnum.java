package com.consol.api.util;

public enum FamiliaEnum {

    BASE_URL("/familias"),
    POR_ID("/familias/{id}");

    FamiliaEnum(String PATH) {
        this.PATH = PATH;
    }

    public final String PATH;
}