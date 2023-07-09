package com.investment.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
