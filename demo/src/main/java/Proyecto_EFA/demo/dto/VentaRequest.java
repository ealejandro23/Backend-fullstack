package Proyecto_EFA.demo.dto;

import java.util.List;
import lombok.Data;

@Data
public class VentaRequest {
    private Integer usuarioId;        // Cambiado de Long a Integer
    private Integer estadoId;         // Cambiado de Long a Integer
    private Integer metodoPagoId;     // Cambiado de Long a Integer
    private Integer metodoEnvioId;    // Cambiado de Long a Integer
    private List<ItemVentaRequest> items;
    
    public VentaRequest() {}
    
    public VentaRequest(Integer usuarioId, Integer estadoId, Integer metodoPagoId, 
                       Integer metodoEnvioId, List<ItemVentaRequest> items) {
        this.usuarioId = usuarioId;
        this.estadoId = estadoId;
        this.metodoPagoId = metodoPagoId;
        this.metodoEnvioId = metodoEnvioId;
        this.items = items;
    }
}