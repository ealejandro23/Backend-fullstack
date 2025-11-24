package Proyecto_EFA.demo.dto;

import lombok.Data;

@Data
public class ItemVentaRequest {
    private Integer productoId;    // Cambiado de Long a Integer
    private Integer cantidad;
    private Double precio;
    
    public ItemVentaRequest() {}
    
    public ItemVentaRequest(Integer productoId, Integer cantidad, Double precio) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}