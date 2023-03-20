/**
 * 
 */
package com.demo.nisum.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * @author cisco505
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8906714344796586633L;
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String name;
	
	
	@Column(nullable = false)
	private String email;
	
	private String username;
	
	@CreatedDate
	@Column( updatable = false)
	private LocalDateTime created;
	@LastModifiedBy
	private LocalDateTime modified;
	private Boolean isActive;
	@Column(nullable = false)
	private String password;
	private String token;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<RoleEntity> roles;
	
	private LocalDateTime lastLogin;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PhoneEntity> phones;
}