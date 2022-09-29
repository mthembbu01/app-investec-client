package com.tstcore.appinvestecclient.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "address",schema = "db_investec")
@Entity(name = "address")
@DiscriminatorColumn(name = "address_type")
public abstract class Address {

    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Column(name = "id", updatable = false, columnDefinition = "bigint")
    private long id;

    @Column(name = "line_one",columnDefinition = "varchar(32)")
    private String lineOne;

    @Column(name = "line_two",columnDefinition = "varchar(32)")
    private String lineTwo;

    @Column(name = "suburb",columnDefinition = "varchar(20)")
    private String suburb;

    @Column(name = "city",columnDefinition = "varchar(20)")
    private String city;

    @Column(name = "area_code",columnDefinition = "int(4)")
    private String areaCode;

}
