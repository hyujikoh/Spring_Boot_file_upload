package com.example.file_upload_system.app.member.entity;

import com.example.file_upload_system.app.base.AppConfig;
import com.example.file_upload_system.app.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore // json 형식 출력값 나오면 안되는 구문이기 때문에 처리
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

    public Member(long id) {
        super(id);
    }
}