package com.vclues.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import com.vclues.core.app.Constant;
import com.vclues.core.enums.UserType;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name ="user_type")
	private Integer type = 0;

	private String password;
	
    @Column(unique=true, updatable = false, nullable = false)
    private String email;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "activated", columnDefinition = "SMALLINT")
    private boolean activated;

    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "reset_password_key")
    private String resetPasswordKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "email", foreignKey = @ForeignKey(name="none", value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "authority"), foreignKey = @ForeignKey(name="none", value = ConstraintMode.NO_CONSTRAINT))
    private Set<Authority> authorities;
    
	private String phone;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;    
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="parent_id", updatable=false, foreignKey = @ForeignKey(name="none", value = ConstraintMode.NO_CONSTRAINT))
	private User parentUser;
	
	@Column(name = "permissions", columnDefinition = "BIGINT")
	private Integer permissions = 0;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name = "account_type")
	private String accountType;
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public User() {}
    
    public User(String email, String password, boolean activated) {
    	this.email = email;
    	this.password = password;
    	this.activated = activated;    
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public Set<Authority> getAuthorities() {
    	if(authorities == null) {
    		authorities = new HashSet<Authority>();
    	}
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
    	if(email == null) {
    		return super.hashCode();
    	}
        return email.hashCode();
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
