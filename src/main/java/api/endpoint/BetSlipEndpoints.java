package api.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BetSlipEndpoints implements ApiEndpoint {
    POST_BET_SLIP_DATA("services/clapi/api/Bet/GetBetSlipDataCL");

    private final String endpoint;
}
