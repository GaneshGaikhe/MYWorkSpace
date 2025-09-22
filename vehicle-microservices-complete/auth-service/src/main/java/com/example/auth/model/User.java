package com.example.auth.model;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import javax.persistence.*; import java.util.HashSet; import java.util.Set;
@Entity @Getter @Setter @NoArgsConstructor @Table(name="users") public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String username;
    @Column(nullable=false) private String password;
    @ElementCollection(fetch=FetchType.EAGER) @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING) private Set<Role> roles = new HashSet<>();
}
