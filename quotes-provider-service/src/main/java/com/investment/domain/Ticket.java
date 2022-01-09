package com.investment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_sequence_generator")
    @SequenceGenerator(name="ticket_sequence_generator", sequenceName = "ticket_sequence_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String ticket;

    private String company;

    private String description;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "ticket_quote")
    private Set<TicketQuote> ticketQuotes;
}
