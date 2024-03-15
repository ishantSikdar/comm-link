package com.ctrlcreate.commlink.model.user;

import com.ctrlcreate.commlink.constant.DBConstants;
import com.ctrlcreate.commlink.dto.others.Coordinates;
import com.ctrlcreate.commlink.model.user.pojo.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Builder
@Document(collection = DBConstants.USERS)
public class User implements UserDetails {

    @Id
    private String id;
    private String email;
    private String type;
    private String name;
    private String username;
    private String password;
    private String pwdHash;
    private Coordinates location;
    private Profile profile;
    private Instant lastLoginAt;
    private Instant createdAt;
    private Instant updatedAt;

    @JsonIgnore
    @Transient
    private Double distanceFromUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
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
