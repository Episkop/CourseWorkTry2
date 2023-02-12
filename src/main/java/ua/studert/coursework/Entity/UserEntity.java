package ua.studert.coursework.Entity;

import lombok.*;
import ua.studert.coursework.Enum.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProfitEntity> profits = new ArrayList<>();

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<SpendingEntity> expensesList = new ArrayList<>();

    public UserEntity(String username, String password, Role role, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }
}
