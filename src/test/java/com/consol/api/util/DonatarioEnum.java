package com.consol.api.util;

public enum DonatarioEnum {

    BASE_URL("/donatarios"),
    POR_ID("/donatarios/{id}"),
    POR_NOME("/donatarios/filtro?nome=");


    DonatarioEnum(String PATH) { this.PATH = PATH; }

    public final String PATH;
}
