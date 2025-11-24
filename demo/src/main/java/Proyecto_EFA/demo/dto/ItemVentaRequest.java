package Proyecto_EFA.demo.dto;

import lombok.Data;

@Data
public class ItemVentaRequest {
    private Integer productoId;
    private Integer cantidad;
    private Double precioUnitario;  // ✅ CAMBIADO de "precio" a "precioUnitario"
    private Double subtotal;        // ✅ AGREGADO subtotal
    
    public ItemVentaRequest() {}
    
    public ItemVentaRequest(Integer productoId, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
}
