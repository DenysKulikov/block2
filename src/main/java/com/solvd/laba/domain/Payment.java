package com.solvd.laba.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.laba.parsers.MyAdapter;
import com.solvd.laba.parsers.SecondAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.sql.Date;

public class Payment {
    private Long id;
    private BigDecimal amount;
    @JsonDeserialize(using = SecondAdapter.class)
    @XmlJavaTypeAdapter(MyAdapter.class)
    private Date paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
