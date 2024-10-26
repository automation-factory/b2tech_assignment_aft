package api.wrapper;

import api.dto.response.BetSlipModel;
import io.restassured.response.Response;

public class BetSlipWrapper extends ApiResponseWrapper<BetSlipWrapper, BetSlipModel> {

    public BetSlipWrapper(Response response) {
        super(response);
    }

    @Override
    public BetSlipModel body() {
        return verifyOkStatusCode().body(BetSlipModel.class);
    }

}
