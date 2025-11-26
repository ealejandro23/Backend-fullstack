package Proyecto_EFA.demo.dto;



import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;



@Data

@NoArgsConstructor

@AllArgsConstructor

public class ProductoCreationDTO {



    private String nombre;

    private String descripcion;

    private Double precio;

    private Double precioOriginal; 

    private Integer stock;

    private String codigo;

    private String image;

    private Boolean oferta;

    private String categoria; 

    private String tipo;

    private String talla;

    private String colores;

    private String materiales;

}
