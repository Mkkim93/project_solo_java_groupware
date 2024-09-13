package com.group.domain.club.entity;

import com.group.domain.club.enums.ClubActionYN;
import com.group.domain.club.enums.ClubAdminYN;
import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "club")
public class Club {

    public Club() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "club_title")
    private String clubTitle;

    @Column(name = "club_content")
    private String clubContent;

    @Column(name = "club_limit")
    private Integer clubLimit;

    @Column(name = "club_actionyn")
    @Enumerated(EnumType.ORDINAL)
    private ClubActionYN clubActionYN;

    @Column(name = "club_adminyn")
    @Enumerated(EnumType.ORDINAL)
    private ClubAdminYN clubAdminYN;

    @OneToMany(mappedBy = "club")
    private Set<Emp_Club> empClub;
}
