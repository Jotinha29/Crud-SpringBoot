package com.example.demo.api.dto;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ListarCategoriaDTOMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = CategoriaDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "nome", type = String.class)
                                }
                        )
                }
        )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class CategoriaDTO implements Serializable {

    @Id
    private Long id;
    private String nome;
}
