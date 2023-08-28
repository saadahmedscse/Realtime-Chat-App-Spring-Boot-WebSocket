package com.saadahmedsoft.sparkconvo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private long id;
    private String name;
    private String email;
    private String photo;
    private String gender;
    private String createdAt;
    private String updatedAt;
}
