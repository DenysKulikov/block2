package com.solvd.laba.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.parsers.MyAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    @XmlAttribute(name = "id")
    @JsonProperty("id")
    private Long id;
    private BigDecimal amount;
    /*@JsonDeserialize(using = SecondAdapter.class)*/
    @XmlJavaTypeAdapter(MyAdapter.class)
    private Date paymentDate;

    public Payment() {
    }

    public Payment(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

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
}
