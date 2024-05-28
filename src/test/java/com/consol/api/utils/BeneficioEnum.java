package com.consol.api.utils;

public enum BeneficioEnum {
    BASE_URL("/beneficio"),
    POR_ID("/beneficio/{id}"),
    POR_FILTRO("/beneficio/filtro");


    BeneficioEnum(String PATH) { this.PATH = PATH; }

    public final String PATH;
}
