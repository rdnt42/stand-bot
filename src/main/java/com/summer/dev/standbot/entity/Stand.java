package com.summer.dev.standbot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:36
 */
@Setter
@Getter
@Entity
@Table(name = "STAND")
public class Stand {

    @Id
    private Long id;

    private String name;

    @Column(name = "status_id", updatable = false, insertable = false)
    private String statusId;

    @ManyToOne
    private StandStatus status;
}
