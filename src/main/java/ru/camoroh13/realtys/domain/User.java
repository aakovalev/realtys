package ru.camoroh13.realtys.domain;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	@Id
	@Column(name = "userId")
	@GeneratedValue
	private Integer id;
    
	@Column(name="username", unique = true)
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="enabled", nullable=false, columnDefinition="BOOLEAN")
	private boolean enabled;

	@Column(name="isAdmin", nullable=false, columnDefinition="BOOLEAN")
	private boolean admin;

    @Lob
	@Column(name = "firstname")
	private String firstname;

    @Lob
	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;

    public User() {
        this.username = "";
        this.password = "";
        this.enabled = false;
        this.admin = false;
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.telephone = "";        
    }

    public User(String username, String password, boolean enabled, boolean isAdmin, String firstname, String lastname, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.admin = isAdmin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        if (!isEnabled()) {
            auths.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
        } else {
            auths.add(new GrantedAuthorityImpl("ROLE_USER"));
            if (isAdmin()) auths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }
		return auths;
	}

	public String toString() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
		this.password = encoder.encodePassword(password, null);
	}

    public void setOldPassword(String password) {
        this.password = password;
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

}
