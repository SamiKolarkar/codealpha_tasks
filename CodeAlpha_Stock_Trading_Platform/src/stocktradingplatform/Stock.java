/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stocktradingplatform;

/**
 *
 * @author sami
 */
public class Stock {
    private String sSymbol;
    private String sName;
    private Double price;

    public String getsSymbol() {
        return sSymbol;
    }

    public void setsSymbol(String sSymbol) {
        this.sSymbol = sSymbol;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}
