package com.json_testing;

import com.json_testing.entity.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class TicketUtils {

    public static List<Duration> getTicketDurationList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(x -> {
                    LocalDateTime departureDateTime = LocalDateTime.of(x.getDepartureDate(), x.getDepartureTime());
                    LocalDateTime arrivalDateTime = LocalDateTime.of(x.getArrivalDate(), x.getArrivalTime());
                    return Duration.between(departureDateTime, arrivalDateTime);
                })
                .collect(Collectors.toList());
    }

    public static Duration getAverageDuration(List<Ticket> ticketList) {
        OptionalDouble averageOptional = getTicketDurationList(ticketList)
                .stream()
                .mapToLong(Duration::toSeconds)
                .average();

        double averageTravelTimeInSeconds = averageOptional.orElseThrow();

        return Duration.ofSeconds((long) averageTravelTimeInSeconds);
    }

    public static Duration getPercentileDuration(List<Ticket> ticketList, double percentile) {
        List<Duration> sortedDurations = getTicketDurationList(ticketList)
                .stream()
                .sorted()
                .collect(Collectors.toList());

        int percentileIndex = (int) Math.ceil(percentile / 100.0 * sortedDurations.size());

        return sortedDurations.get(percentileIndex);
    }
}
