package com.karvan.entity.user;

import com.karvan.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    private long RoleID;


    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;


}
