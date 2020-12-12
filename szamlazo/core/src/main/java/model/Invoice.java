package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Invoice {
    private String id;
    private String customerId;
    private LocalDateTime date;
    private Map<String, Integer> items; //String -> Item's barcode, Integer -> quantity of items

    public Invoice(@NotNull String customerID, @NotNull Map<String, Integer> items) {
        this.customerId = customerID;
        this.items = items;
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public Invoice(){
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public Map<String, Integer> getItems() {
        return items;
    }

    public String getCustomerId() {
        return customerId;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                ", items=" + items +
                '}';
    }
}
