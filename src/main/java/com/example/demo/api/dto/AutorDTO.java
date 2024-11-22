package com.example.demo.api.dto;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ListarAutorDTOMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = AutorDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "nome", type = String.class),
                                        @ColumnResult(name = "nacionalidade", type = String.class)
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
public class AutorDTO implements Serializable {

    @Id
    private Long id;
    private String nome;
    private String nacionalidade;
}
