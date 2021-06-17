package com.orangetalents.desafio.service;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RodizioService {


    public static String nomeDiaRodizio(Integer ano) {
        int dia = regraRodizio(ano);
        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("pt", "BR"));
        String[] dayNames = symbols.getWeekdays();
        return dayNames[dia];
    }

    public static boolean isDiaRodizio(Integer ano) {
        Calendar agora = Calendar.getInstance(new Locale("pt", "BR"));
        return agora.get(Calendar.DAY_OF_WEEK) == regraRodizio(ano);
    }

    public static List<String> digitosFinaisParaHoje() {
        Calendar agora = Calendar.getInstance(new Locale("pt", "BR"));
        switch (agora.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return Arrays.asList("0", "1");
            case Calendar.TUESDAY:
                return Arrays.asList("2", "3");
            case Calendar.WEDNESDAY:
                return Arrays.asList("4", "5");
            case Calendar.THURSDAY:
                return Arrays.asList("6", "7");
            case Calendar.FRIDAY:
                return Arrays.asList("8", "9");
        }
        return Arrays.asList();
    }

    private static int regraRodizio(Integer ano) {
        String valorFinalAno = ano.toString().substring(3);

        switch (valorFinalAno) {
            case "0":
            case "1":
                return Calendar.MONDAY;
            case "2":
            case "3":
                return Calendar.TUESDAY;
            case "4":
            case "5":
                return Calendar.WEDNESDAY;
            case "6":
            case "7":
                return Calendar.THURSDAY;
            case "8":
            case "9":
                return Calendar.FRIDAY;
        }
        return 0;
    }
}
