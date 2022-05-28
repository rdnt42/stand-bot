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
@Table(name = "STANDS")
public class Stand {

    @Id
    @Column(name = "stand_id")
    private Long id;

    @Column(unique=true)
    private String name;

    @Column(name = "stand_status_id", updatable = false, insertable = false)
    private String standStatusId;

    @ManyToOne
    private EquipmentState standStatus;

    @Column(name = "metrics_status_id", updatable = false, insertable = false)
    private String metricsStatusId;

    @ManyToOne
    private EquipmentState metricsStatus;

    @Column(name = "dependent_session_status_id", updatable = false, insertable = false)
    private String dependentSessionStatusId;

    @ManyToOne
    private EquipmentState dependentSessionStatus;

    @Column(name = "independent_session_status_id", updatable = false, insertable = false)
    private String independentSessionStatusId;

    @ManyToOne
    private EquipmentState independentSessionStatus;
}
