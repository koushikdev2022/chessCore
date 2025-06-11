package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iksen.chessCore.model.PaymentMethod;


public interface PaymentMethodRepo  extends JpaRepository<PaymentMethod, Integer> {
        @Query("SELECT DISTINCT pm FROM PaymentMethod pm " +
            "JOIN pm.paymentCountryMaps pcm " +
            "WHERE pcm.country.id = :countryId AND pcm.country.status = 1")
        List<PaymentMethod> findByCountryId(@Param("countryId") Integer countryId);
}
