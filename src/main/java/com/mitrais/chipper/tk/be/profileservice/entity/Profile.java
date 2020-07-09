package com.mitrais.chipper.tk.be.profileservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mitrais.chipper.tk.be.profileservice.common.Gender;
import com.mitrais.chipper.tk.be.profileservice.entity.common.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "modifiedBy", "modifiedDate" }, allowGetters = true)
@ApiModel(description = "All details about Profile. ")
@SQLDelete(sql = "UPDATE profiles SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class Profile extends Auditable<String> {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq_gen")
    @SequenceGenerator(name = "profile_id_seq_gen", sequenceName = "profile_id_seq", allocationSize = 1)
    @ApiModelProperty(notes = "Profile DB id")
    private Long id;

    @NotNull
    private Long user;

    @NotEmpty
    @ApiModelProperty(notes = "Profile full name")
    @Size(min = 1, max = 50)
    private String fullName;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(notes = "Profile birth of date")
    private LocalDate dob;

    @NotNull
    @Column(length = 1)
    @ApiModelProperty(notes = "Profile gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ApiModelProperty(notes = "Profile city")
    private String city;

    @ApiModelProperty(notes = "Profile about me")
    @Column(length = 200)
    @Size(max = 200)
    private String aboutMe;

    @ApiModelProperty(notes = "Profile interest")
    @Column(length = 200)
    @Size(max = 200)
    private String interest;

    @ApiModelProperty(notes = "Photo profile url")
    @Column(length = 200)
    @Size(max = 200)
    private String photoProfileUrl;

    @NotNull
    protected boolean deleted;
}
