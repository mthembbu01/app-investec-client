package com.tstcore.appinvestecclient.entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "client",schema = "db_investec")
@Entity(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name="id", updatable = false, columnDefinition = "bigint")
    private Long id;

    @NotNull
    @Column(name="first_name", nullable = false, columnDefinition = "varchar(30)")
    private String fullName;

    @NotNull
    @Column(name="last_name", nullable = false, columnDefinition = "varchar(30)")
    private String lastName;

    @NotNull
    @Column(name="mobile_number",nullable = false, unique = true, columnDefinition = "varchar(12)")
    private String mobileNumber;

    @NotNull
    @Column(name="idnumber", nullable = false, unique = true, updatable = false, columnDefinition = "varchar(13)")
    @Pattern(regexp = "^[0-9]{13}$", message = "Must contain digits only")
    @Range(min = 1, max = 13, message = "Must contain exactly 13 digits")
    private String idNumber;

    @OneToOne
    @JoinColumn(name = "address_id")
    private PhysicalAddress address;
}
