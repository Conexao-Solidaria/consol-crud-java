package com.consol.api.util;

public enum DonatarioEnum {

    BASE_URL("/donatarios"),
    POR_ID("/donatarios/{id}"),
    POR_NOME("/donatarios/filtro?nome="),
    POR_DATA("/donatarios/data"),
    POR_PERIODO("/donatarios/periodo"),
    POR_FAMILIAR("/donatarios/familiar");

    DonatarioEnum(String PATH) { this.PATH = PATH; }

    public final String PATH;
}
