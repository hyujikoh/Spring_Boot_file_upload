package com.example.file_upload_system.app.member.entity;

import com.example.file_upload_system.app.base.AppConfig;
import com.example.file_upload_system.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String profileImg;

    public void removeProfileImgOnStorage() {
        if (profileImg == null || profileImg.trim().length() == 0) return;

        String profileImgPath = getProfileImgPath();
        new File(profileImgPath).delete();
    }

    private String getProfileImgPath() {
        return AppConfig.GET_FILE_DIR_PATH + "/" + profileImg;
    }

    public String getProfileImgUrl() {
        if ( profileImg == null ) return null;

        return "/gen-file/" + profileImg;
    }
}