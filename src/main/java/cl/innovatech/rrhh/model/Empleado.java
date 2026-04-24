package cl.innovatech.rrhh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Email(message = "Formato de email inválido")
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "El cargo es obligatorio")
    @Column(nullable = false)
    private String cargo; //

    @Min(value = 0, message = "Las horas asignadas no pueden ser negativas")
    @Max(value = 45, message = "Un empleado no puede exceder las 45 horas semanales")
    @Column(name = "horas_asignadas", nullable = false)
    private Integer horasAsignadas;

    @Column(nullable = false)
    private Integer capacidadMaxima = 45;
}