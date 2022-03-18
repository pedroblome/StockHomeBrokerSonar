package com.pedroblome.stocks.model;

import javax.persistence.*;

import com.pedroblome.stocks.repository.StockRepository;

import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;
    @Column(name = "stockSymbol")
    private String stockSymbol;
    @Column(name = "stockName")
    private String stockName;
    @Column(name = "askMin")
    private BigDecimal askMin;
    @Column(name = "askMax")
    private BigDecimal askMax;
    @Column(name = "bidMin")
    private BigDecimal bidMin;
    @Column(name = "bidMax")
    private BigDecimal bidMax;
    @Column(name = "createdOn")
    private Timestamp createdOn;
    @Column(name = "updatedOn")
    private Timestamp updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getAskMin() {
        return askMin;
    }

    public void setAskMin(BigDecimal askMin) {
        this.askMin = askMin;
    }

    public BigDecimal getAskMax() {
        return askMax;
    }

    public void setAskMax(BigDecimal askMax) {
        this.askMax = askMax;
    }

    public BigDecimal getBidMin() {
        return bidMin;
    }

    public void setBidMin(BigDecimal bidMin) {
        this.bidMin = bidMin;
    }

    public BigDecimal getBidMax() {
        return bidMax;
    }

    public void setBidMax(BigDecimal bidMax) {
        this.bidMax = bidMax;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Stock() {
        this.createdOn = Timestamp.valueOf(LocalDateTime.now());
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result();
    }

    private int result() {
        return 0;
    }

}
