package com.hack.hackathon.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String logoUrl;

    private Boolean approved;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}