package com.example.SpringBoot.repository;

import com.example.SpringBoot.dao.QuoteDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface QuotesRepository extends JpaRepository<QuoteDAO, String> {

    @Modifying
    @Transactional
    public void deleteByCreationDateLessThan(Date closeTime);

    @Query(value = "SELECT * FROM Quotes q WHERE q.isin = ?1 AND q.creation_date BETWEEN ?2 and ?3 ORDER BY q.creation_date DESC", nativeQuery = true)
    List<QuoteDAO> findRelatedQuotesBetweenTimeNative(String isin, Date openTime, Date endTime);

}