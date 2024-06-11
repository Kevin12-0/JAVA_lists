package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Money;
import models.moneyJson;

/**
 * main
 */
public class main {

    public static void main(String[] args) throws IOException, InterruptedException {
        /* definir el scanner */
        Scanner read = new Scanner(System.in);
        /* crear una instancia de Gson con politicas de nombrado */
        Gson gsonData = new GsonBuilder().setPrettyPrinting()
                .create();
        List<Money> divisas = new ArrayList<>();
        while (true) {
            System.out.println("Ingrese el numero segun tipo de cambio de noneda que deasea realizar");
            System.out.println("1. MXN a USD");
            System.out.println("2. USD a MXN");
            System.out.println("3. EUR a USD");
            var select = read.nextLine();
            if (select.equalsIgnoreCase("1")) {
                System.out.println("Dijite su monto a convertir");
                Double monto = read.nextDouble();
                String url = "https://v6.exchangerate-api.com/v6/f27ea6ed80ba33882f773537/pair/MXN/USD/" + monto;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                moneyJson moneyResult = gsonData.fromJson(json, moneyJson.class);
                System.err.println(moneyResult);
                Money divisa = new Money(moneyResult);
                System.out.println("La convercion de esta divida es de: " + divisa.getConversion_result());

            } else if (select.equalsIgnoreCase("salir")) {
                System.out.println("Opcion no valida, adios");
                break;
            } else if (select.equalsIgnoreCase("2")) {
                System.out.println("Dijite su monto a convertir");
                Double monto = read.nextDouble();
                String url = "https://v6.exchangerate-api.com/v6/f27ea6ed80ba33882f773537/pair/USD/MXN/" + monto;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                moneyJson moneyResult = gsonData.fromJson(json, moneyJson.class);
                System.err.println(moneyResult);
                Money divisa = new Money(moneyResult);
                System.out.println("La convercion de esta divida es de: " + divisa.getConversion_result());
            } else if (select.equalsIgnoreCase("3")) {
                System.out.println("Dijite su monto a convertir");
                Double monto = read.nextDouble();
                String url = "https://v6.exchangerate-api.com/v6/f27ea6ed80ba33882f773537/pair/EUR/USD/" + monto;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                moneyJson moneyResult = gsonData.fromJson(json, moneyJson.class);
                System.err.println(moneyResult);
                Money divisa = new Money(moneyResult);
                System.out.println("La convercion de esta divida es de: " + divisa.getConversion_result());
            }
        }

        FileWriter logger = new FileWriter("convercionDivisa.json");
        logger.write(gsonData.toJson(divisas));
        logger.close();
    }
}