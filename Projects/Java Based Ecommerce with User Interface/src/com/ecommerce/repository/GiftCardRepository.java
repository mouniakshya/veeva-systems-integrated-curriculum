package com.ecommerce.repository;

import com.ecommerce.model.payment.GiftCard;

import java.util.Optional;

public interface GiftCardRepository {
    void save(GiftCard giftCard);
    Optional<GiftCard> findByCode(String code);
    boolean existsByCode(String code);
}
