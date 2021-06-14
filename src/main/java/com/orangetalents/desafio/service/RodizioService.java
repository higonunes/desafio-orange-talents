package com.orangetalents.desafio.service;

import com.orangetalents.desafio.enums.Dia;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class RodizioService {

    public static Dia diaDoRodizio(Integer ano) {
        String valorFinal = ano.toString().substring(3);

        switch (valorFinal) {
            case "0":
            case "1":
                return Dia.SEGUNDA;
            case "2":
            case "3":
                return Dia.TERCA;
            case "4":
            case "5":
                return Dia.QUARTA;
            case "6":
            case "7":
                return Dia.QUINTA;
            case "8":
            case "9":
                return Dia.SEXTA;
        }
        return null;
    }

    public static boolean isDiaRodizio(Integer ano) {
        DayOfWeek d = DayOfWeek.from(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        return diaDoRodizio(ano).getId() == d.getValue();
    }

    public static List<String> digitosFinaisDia() {
        DayOfWeek d = DayOfWeek.from(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        switch (d.getValue()) {
            case 1:
                return Arrays.asList("1", "2");
            case 2:
                return Arrays.asList("3", "4");
            case 3:
                return Arrays.asList("5", "6");
            case 4:
                return Arrays.asList("7", "8");
            case 5:
                return Arrays.asList("9", "0");
        }
        return Arrays.asList();
    }
}
