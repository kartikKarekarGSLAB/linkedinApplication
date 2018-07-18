package com.gslab.linkedin.LINKEDINDEMO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="user_account")
public class UserAccount {
	
	@GenericGenerator(
            name = "userAccountSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "userAccountSequence"),
                    @Parameter(name = "initial_value", value = "10001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="userAccountSequenceGenerator")
	private int id;
	@Column(name="username",length=20,nullable=false)
	private String username;
	@Column(name="password",length=20,nullable=false)
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		this.password = password;
	}
}
