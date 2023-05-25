package com.example.quanlysach.Model;

import com.example.quanlysach.validator.annotation.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Trường này không được để trống!")
    @Column(name="title")
    private String title;
    @NotBlank(message = "Trường này không được để trống!")
    @Column(name="author")
    private String author;


    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 10000, message = "Giá sản phẩm không được bé hơn 10000")
    @Max(value = 999999999, message = "Giá sản phẩm không được quá 999999999")
    @Column(name="price")
    private Double price;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;
}
