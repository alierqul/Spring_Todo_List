package com.alierqul.todolist.repository.entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.alierqul.todolist.repository.entity.configure.UniqeUsername;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity implements UserDetails {

  private static final long serialVersionUID = 9066911572038657025L;
  
    @Id
    @GeneratedValue
    private long id;
    
    @UniqeUsername
    @NotNull(message="{todo.constraint.username.NotNull.message}")
    @Size(min = 4, max=255 )
    
    private String username;
    
    @NotNull(message="{todo.constraint.name.NotNull.message}")
    @Size(min = 4, max=255 )
    
	private String name;
    
    @NotNull(message="{todo.constraint.password.NotNull.message}")
    @Size(min = 8, max=255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message="{todo.constrain.password.Pattern.message}")
    //@JsonIgnore //Json oluştuturken bunu pass geç. görmezdeb gel
    
	private String password;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<TodoEntity> todos = new HashSet<TodoEntity>();
  private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return AuthorityUtils.createAuthorityList("Role_user");
    }

    

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
     
      return true;
    }
	
	
}
