package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  User account response DTO
 * */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountResponse {
    private String username;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;
    public UserAccountResponse(UserAccount userAccount) {
        this.username = userAccount.getUsername();
        this.created = userAccount.getCreated();
        this.updated = userAccount.getUpdated();
    }
}
