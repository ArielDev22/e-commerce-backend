package com.ariel.dev22.e_commerce_backend.favorite;

import com.ariel.dev22.e_commerce_backend.favorite.item.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "favorites")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id.favorite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FavoriteItem> items = new HashSet<>();

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user;
}
