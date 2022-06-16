package com.summer.dev.standbot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:38
 */
@Setter
@Getter
@Entity
@Table(name = "STATUSES")
public class Status {
    @Id
    @Column(name = "status_id")
    private Long id;

    private String name;
}
