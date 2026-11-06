package com.ecommerce.repository;

import com.ecommerce.model.payment.GiftCard;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGiftCardRepository implements GiftCardRepository {
    private final Map<String, GiftCard> cardsByCode = new ConcurrentHashMap<>();

    @Override
    public void save(GiftCard giftCard) {
        if (giftCard != null) {
            cardsByCode.put(giftCard.getCardCode().toUpperCase(), giftCard);
        }
    }

    @Override
    public Optional<GiftCard> findByCode(String code) {
        if (code == null) return Optional.empty();
        return Optional.ofNullable(cardsByCode.get(code.trim().toUpperCase()));
    }

    @Override
    public boolean existsByCode(String code) {
        if (code == null) return false;
        return cardsByCode.containsKey(code.trim().toUpperCase());
    }
}
