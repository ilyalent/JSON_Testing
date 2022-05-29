package com.json_testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.json_testing.entity.Ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputString;
        File inputFile;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String entryString = "Enter valid absolute or relative path for tickets.json or \"exit\" for stop application.";

            System.out.println(entryString);

            while (true) {
                inputString = reader.readLine();
                if (inputString.equals("exit")) System.exit(0);
                inputFile = Paths.get(inputString).toFile();
                if (inputFile.exists()) {
                    break;
                } else {
                    System.out.println(entryString);
                }
            }
        }

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        List<Ticket> ticketList = Arrays.asList(om.reader(Ticket.class).withRootName("tickets")
                .readValue(inputFile, Ticket[].class));

        System.out.println("Listing tickets from json.");
        ticketList.forEach(System.out::println);

        Duration averageFlyTime = TicketUtils.getAverageDuration(ticketList);

        System.out.println("Average flying time is: " + averageFlyTime.toHours() +
                " hours and " + averageFlyTime.toMinutesPart() + " minutes.");

        double percentile90 = 90.0;

        Duration percentile = TicketUtils.getPercentileDuration(ticketList, percentile90);

        System.out.println(percentile90 + "th percentile is: " + percentile.toHours() +
                " hours and " + averageFlyTime.toMinutesPart() + " minutes.");
    }
}
