package com.springboot.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email_id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    @NotBlank
    @Size(min = 3, max = 120)
    private String password;

    @Column(name = "email_id")
    @Email
    @Size(min = 3, max = 50)
    private String email;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
              )
    private Set<Role> roles = new HashSet<>();


    @ToString.Exclude
    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.PERSIST},
                orphanRemoval = true)
    private Set<Product> products;


    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "user_address",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

}
