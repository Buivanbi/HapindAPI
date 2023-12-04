package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class WorkDTO {
	private Integer id;
	@NotEmpty(message="Work Name can't be blank")
	private String name;
	
	@NotNull(message = "User must be selected")
	private Set<User> users = new HashSet<User>(0);

	public WorkDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkDTO(@NotEmpty(message = "Work Name can't be blank") String name,
			@NotNull(message = "User must be selected") Set<User> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
