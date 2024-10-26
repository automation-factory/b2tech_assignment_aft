package api.data;

import api.common.ApiPojoRequest;
import api.dto.request.BetSlipModel;
import io.restassured.http.Header;
import lombok.NonNull;

public class BetSplitRequest extends ApiPojoRequest<BetSlipModel> {

    public BetSplitRequest(BetSlipModel requestModel) {
        super(requestModel);
        super.setHeader(new Header("brandid", "30"));
    }

    public static @NonNull BetSplitRequest create() {
        var body = BetSlipModel.builder().build();
        return new BetSplitRequest(body);
    }

}
