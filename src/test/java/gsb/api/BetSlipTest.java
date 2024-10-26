package gsb.api;

import api.client.BetSlipClient;
import api.data.BetSplitRequest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class BetSlipTest {

    @Test
    public void validateInvalidBookingCode() {
        final var schemaPath = "schemas/betSlipSchema.json";
        var betSplitRequest = BetSplitRequest.create(); // With incorrect booking code
        var betSlipResponse = new BetSlipClient().sendBetSplit(betSplitRequest, schemaPath).body();

        log.info("isSuccessful: {}", betSlipResponse.getIsSuccessfull());
        log.info("errorCode: {}", betSlipResponse.getResult().getErrorCode());
        log.info("errorCodeDescription: {}", betSlipResponse.getResult().getErrorCodeDescription());

        Assert.assertFalse(betSlipResponse.getIsSuccessfull(), "Expected isSuccessfull to be false.");
        Assert.assertEquals(betSlipResponse.getResult().getErrorCode(), 201, "Expected error code to be 201.");
        Assert.assertEquals(betSlipResponse.getResult().getErrorCodeDescription(), "Order not found", "Expected error description to be 'Order not found'.");
    }

}
