package es.lareira.denodo.adapter.api;

import es.lareira.denodo.generated.api.PurchasesApi;
import es.lareira.denodo.generated.model.AgeRange;
import es.lareira.denodo.generated.model.GetPurchasesPageableParameter;
import es.lareira.denodo.generated.model.Purchase;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PurchaseController implements PurchasesApi {
    @Override
    public AgeRange getMoreFrequentAgeRange(LocalDateTime from, LocalDateTime to) {
        throw new NotImplementedException("Not implemented yet");

    }

    @Override
    public List<Purchase> getPurchases(Integer userId, BigDecimal total, GetPurchasesPageableParameter pageable) {
        throw new NotImplementedException("Not implemented yet");
    }
}
