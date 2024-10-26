package api.client;

import api.common.BaseApiRequests;
import api.common.IRequestData;
import api.endpoint.BetSlipEndpoints;
import api.wrapper.BetSlipWrapper;

public class BetSlipClient {

    public BetSlipWrapper sendBetSplit(IRequestData requestData, String schemaPath) {
        var endpoint = BetSlipEndpoints.POST_BET_SLIP_DATA.getEndpoint();
        var response = BaseApiRequests.postRequest(endpoint, requestData, schemaPath);
        return new BetSlipWrapper(response);
    }

}
