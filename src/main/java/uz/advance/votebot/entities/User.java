package uz.advance.votebot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.advance.votebot.utils.TableName;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = TableName.USERS)
@AllArgsConstructor
@NoArgsConstructor
public class User extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
}
