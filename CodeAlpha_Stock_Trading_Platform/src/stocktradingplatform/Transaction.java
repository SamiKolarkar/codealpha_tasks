/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stocktradingplatform;

import java.time.LocalDateTime;

/**
 *
 * @author sami
 */
public class Transaction {
    private Integer tId;
    private Integer uId;
    private String tType;
    private Integer tQuantity;
    private Double tTotalValue;
    private Double tPricePerShare;
    private LocalDateTime ldt;

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public Integer gettQuantity() {
        return tQuantity;
    }

    public void settQuantity(Integer tQuantity) {
        this.tQuantity = tQuantity;
    }

    public Double gettTotalValue() {
        return tTotalValue;
    }

    public void settTotalValue(Double tTotalValue) {
        this.tTotalValue = tTotalValue;
    }

    public Double gettPricePerShare() {
        return tPricePerShare;
    }

    public void settPricePerShare(Double tPricePerShare) {
        this.tPricePerShare = tPricePerShare;
    }
    
    
    
    public Integer gettId() {
        return tId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }
    
}
