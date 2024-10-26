package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Event {
    private String teamHome;
    private String teamAway;
    private List<Double> odds;
}