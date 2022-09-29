package com.tstcore.appinvestecclient.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "physical_address",schema = "db_investec")
@Entity(name = "physical_address")
@DiscriminatorValue("physical_address")
public class PhysicalAddress extends Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "street_address", columnDefinition = "varchar(32)")
    private String streetAddress;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private Client client;

}
