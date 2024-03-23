package org.example;

//? csv file parser

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovementsParser {

    public static void main(String[] args) {

        String path = "path to .csv file\\movementList.csv";
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Double> expence2sum = new HashMap<>();

        String firstLine = null;

        for (String line : lines) {
            if (firstLine == null) {
                firstLine = line;
                continue;
            }
            String[] tokens = line.split(",");
            double expense = Double.parseDouble(tokens[7]);
            if (expense == 0) {
                continue;
            }
            String paymentType = getPaymentType(tokens[5]);
            if (!expence2sum.containsKey(paymentType)) {
                expence2sum.put(paymentType, 0.);
            }
            expence2sum.put(
                    paymentType,
                    expence2sum.get(paymentType) + expense
            );
        }

        for (String paymentType : expence2sum.keySet()) {
            double sum = expence2sum.get(paymentType);
            System.out.println(paymentType + "\t" + sum);
        }
    }

    private static String getPaymentType(String info) {
        String regex = "[^a-zA-Z0-9]([a-zA-Z0-9\s]+)[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\s[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(info);
        return matcher.find()
                ? matcher.group(1).trim()
                : null;
    }
}
//
//SUBWAY	510.0
//RAIKHONA	1645.0
//FSPRG UK	953.44
//YANDEX EDA	823.0
//ZOOMAGAZIN 4	2176.5
//DIXY	281.38
//GOOGLE GOOGLE	139.0
//VPS NET	4409.29
//Alfa Iss	353000.0
//RYABINOVAYA 5	630.0
//KFC ASHAN MAR	375.0
//LOVE REPUBLIC	10815.3
//ZOTMAN	3000.0
//delivery club	1370.0
//KUSCHAVEL	4745.0
//L ETOILE	1263.4
//AWS EMEA	588.63
//YANDEX TAXI	11873.0
//WWW HETZNER D	16795.13
//MOSCOW	51000.0