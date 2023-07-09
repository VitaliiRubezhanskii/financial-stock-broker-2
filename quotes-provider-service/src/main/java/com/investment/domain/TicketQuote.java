package com.investment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_quote")
@Getter
@Setter
public class TicketQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "quote_sequence_generator")
    @SequenceGenerator(name="ticket_quote_sequence_generator", sequenceName = "quote_sequence_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private LocalDateTime date;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Long volume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

}
