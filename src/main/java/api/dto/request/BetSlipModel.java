package api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BetSlipModel {
    @Builder.Default
    private String barcode = null;
    @Builder.Default
    private String bookingCode = "11111111";
    @Builder.Default
    private String orderId = null;
}
