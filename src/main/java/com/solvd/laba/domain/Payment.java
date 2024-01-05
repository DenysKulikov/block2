package com.solvd.laba.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.laba.parsers.MyAdapter;
import com.solvd.laba.parsers.SecondAdapter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Payment {
    @XmlAttribute(name = "id")
    private Long id;
    private BigDecimal amount;
    /*@JsonDeserialize(using = SecondAdapter.class)
    @XmlJavaTypeAdapter(MyAdapter.class)*/
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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount) && Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, paymentDate);
    }
}
