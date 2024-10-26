package api.dto.response;

import lombok.Data;

@Data
public class BetSlipModel {

    private Result result;
    private Object data;
    private Object dataStructure;
    private Object additionalData;
    private Object userInfo;
    private Boolean isSuccessfull;

    @Data
    public static class Result {
        private String errorDescription;
        private String additionalInfo;
        private Object eventData;
        private Object closedOdds;
        private int errorCode;
        private int resultCode;
        private String errorCodeDescription;
    }
}
