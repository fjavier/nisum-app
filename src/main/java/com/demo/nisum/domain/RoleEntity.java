package com.demo.nisum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity implements GrantedAuthority {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 7518018047824971012L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name="role_name")
	    private String roleName;

	    @Column(name="description")
	    private String description;

		@Override
		public String getAuthority() {
			return this.roleName;
		}

	
}
