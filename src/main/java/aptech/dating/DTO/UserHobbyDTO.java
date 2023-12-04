package aptech.dating.DTO;

import aptech.dating.model.Hobby;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserHobbyDTO {
	private Integer id;
	@NotNull(message = "Hobby must be selected")
	private Hobby hobby;
	
	@NotNull(message = "User must be selected")
	private User user;

	public UserHobbyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserHobbyDTO(@NotNull(message = "Hobby must be selected") Hobby hobby,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.hobby = hobby;
		this.user = user;
	}

	public Hobby getHobby() {
		return hobby;
	}

	public void setHobby(Hobby hobby) {
		this.hobby = hobby;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
