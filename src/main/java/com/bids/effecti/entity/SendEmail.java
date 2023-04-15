package com.bids.effecti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "SendEmail")
public class SendEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_sending")
    private Boolean isSending;
    @Column(name = "is_return")
    private Boolean isReturn;
    @Column(name = "guid")
    private Long guid;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employer", referencedColumnName = "ID")
    private Employer employer;
    @Column(name = "url")
    private String url;
}
