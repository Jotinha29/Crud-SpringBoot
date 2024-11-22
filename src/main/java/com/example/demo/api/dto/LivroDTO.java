package com.example.demo.api.dto;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ListarLivroDTOMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = LivroDTO.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "titulo", type = String.class),
                                        @ColumnResult(name = "isbn", type = String.class),
                                        @ColumnResult(name = "autorId", type = Long.class),
                                        @ColumnResult(name = "categoriaId", type = Long.class),
                                        @ColumnResult(name = "nomeAutor", type = String.class),
                                        @ColumnResult(name = "nomeCategoria", type = String.class)
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
public class LivroDTO implements Serializable {

    @Id
    private Long id;
    private String titulo;
    private String isbn;
    private Long autorId;
    private Long categoriaId;
    private String nomeAutor;
    private String nomeCategoria;
}
